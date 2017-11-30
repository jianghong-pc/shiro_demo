package com.qianmi.demo.common.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

public final class PropertiesHandler {
    /**
     * 异常码和异常描述对应的属性文件
     */
    private static Properties properties;

    private PropertiesHandler() {
    }

    /**
     * 根据异常码获取异常描述
     *
     * @return String 异常码
     */
    public static String getErrMsg(String errId) {
        String errMsg = properties.getProperty(errId);

        if (StringUtils.isBlank(errMsg)) {
            errMsg = errId;
        }

        return errMsg;
    }

    /**
     * 加载异常码对应的属性文件
     *
     * @param props 属性文件对象
     */
    public static void loadErrorCodes(Properties props) {
        properties = props;
    }

    /**
     * 获取属性
     *
     * @param propKey
     * @return
     */
    public static String getProperty(String propKey) {
        return properties.getProperty(propKey);
    }
}
