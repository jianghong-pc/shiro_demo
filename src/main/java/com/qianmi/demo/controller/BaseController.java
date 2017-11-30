package com.qianmi.demo.controller;

import com.qianmi.demo.common.exception.ShiroDemoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller基类，放置用到的公共资源
 * 比如数据格式转换公共方法、操作日志记录的方法
 */
@Scope("request")
public class BaseController {

    protected final transient Logger logger = LoggerFactory.getLogger("app");

    /**
     * The action execution was successful.
     */
    public static final String SUCCESS = "success";

    /**
     * The action execution was a fail.
     */
    public static final String FAIL = "fail";

    /**
     * The Remote execution was a error
     */
    public static final String ERROR = "error";

    /**
     * 成功的Status Code
     */
    private static final int RESCODE_OK = 200;
    /**
     * 失败的Status Code
     */
    private static final int RESCODE_FAIL = 201;

    /**
     * Jquery DataTable Data
     *
     * @param totalCount 总数
     * @param dataList 数据
     * @return map
     */
    protected Map<String, Object> dataTableJson(int totalCount, List<?> dataList) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("iTotalDisplayRecords", totalCount);
        data.put("iTotalRecords", totalCount);
        data.put("aaData", dataList == null ? Collections.EMPTY_LIST : dataList);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("result", SUCCESS);
        map.put("data", data);
        return map;
    }

    /**
     * 描述：获取失败结果
     *
     * @param msg
     * @return
     */
    protected Map<String, Object> getFailResult(String msg) {
        return getResult(false, RESCODE_FAIL, msg, Collections.EMPTY_MAP);
    }

    /**
     * 描述：获取返回结果
     *
     * @param isOk
     * @param resCode
     * @param errorMsg
     * @param obj
     * @return
     */
    protected Map<String, Object> getResult(boolean isOk, int resCode, String errorMsg, Object obj) {
        return createJson(isOk, resCode, errorMsg, obj);
    }

    /**
     * 描述：组装json格式返回结果
     *
     * @param isOk
     * @param resCode
     * @param errorMsg
     * @param obj
     * @return
     */
    protected Map<String, Object> createJson(boolean isOk, int resCode, String errorMsg, Object obj) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("result", isOk ? "ok" : "fail");
        jsonMap.put("rescode", resCode);
        jsonMap.put("msg", errorMsg);
        jsonMap.put("data", obj);
        return jsonMap;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        logger.error("{}失败", new Object[]{ex});

        String msg =  "系统错误";
        if(ex instanceof ShiroDemoException){
            msg = ((ShiroDemoException) ex).getErrorMsg();
        }else if( ex instanceof BindException){
            msg = ((BindException) ex).getFieldError().getDefaultMessage();
        }
        //如果是ajax请求返回json结果，普通的就返回至error.jsp
        return isAjax(request) ? new ModelAndView(new MappingJackson2JsonView(), getFailResult(msg))
                : new ModelAndView("error", "msg", msg);
    }

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}