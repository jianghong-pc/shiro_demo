package com.qianmi.demo.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroDemoException extends Exception {

	private static final long serialVersionUID = -6202759933628287239L;

    private static final String DEFAULT_ERROR_CODE = "-10000";

    private static Logger logger = LoggerFactory.getLogger(ShiroDemoException.class);

    private String errorCode;

    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 默认构造，一般用于获取动态方法堆栈
     */
    public ShiroDemoException() {
        super();
        logger.info("操作执行异常", this);
    }

    public ShiroDemoException(Throwable e){
        super(e);
        logger.info("操作执行异常", this);
    }

    /**
     * 根据异常信息构造异常
     * @param message
     */
    public ShiroDemoException(String message) {
        super(message);
        this.errorMsg = message;
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    /**
     * 根据异常编号构造异常
     * @param errorCode
     */
    public ShiroDemoException(int errorCode) {
        super(PropertiesHandler.getErrMsg(String.valueOf(errorCode)));
        this.errorCode = String.valueOf(errorCode);
        this.errorMsg = PropertiesHandler.getErrMsg(this.errorCode);
        
    }

    /**
     * 屏蔽异常堆栈
     */
    public Throwable fillInStackTrace() {
        return null;
    }
}
