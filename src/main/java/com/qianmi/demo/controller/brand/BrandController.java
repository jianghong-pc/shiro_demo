package com.qianmi.demo.controller.brand;

import com.qianmi.demo.common.consts.Constants;
import com.qianmi.demo.common.exception.ShiroDemoException;
import com.qianmi.demo.common.util.Pagination;
import com.qianmi.demo.common.util.PaginationSupport;
import com.qianmi.demo.common.util.RequestUtil;
import com.qianmi.demo.controller.BaseController;
import com.qianmi.demo.controller.brand.form.BrandForm;
import com.qianmi.demo.pojo.brand.Brand;
import com.qianmi.demo.service.BrandService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/brand")
public class BrandController extends BaseController {

    @Resource
    private BrandService brandService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() throws ShiroDemoException {
        BrandForm brandForm = new BrandForm();
        brandForm.setId(1222);
        brandForm.setName("sssss");
        brandForm.toBrand();
        logger.info("Action.User={}", SecurityUtils.getSubject().getPrincipal());
        return "brand/brand-index";
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = {Constants.PRODUCES})
    @ResponseBody
    public Map<String, Object> query(BrandForm brandForm, HttpServletRequest request) throws ShiroDemoException {

        logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), brandForm.toString());
        Pagination page = new Pagination(request, brandForm.getiDisplayStart(), brandForm.getiDisplayLength());
        PaginationSupport<Brand> ps = brandService.query(brandForm.toBrand(), page);
        return dataTableJson(ps.getTotalCount(), ps.getItems());
    }

}
