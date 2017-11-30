package com.qianmi.demo.common.consts;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Properties;

public final class Constants {

    //用于fastjson转出来的json序列中文乱码的问题
    public static final String PRODUCES = "application/json;charset=UTF-8";

    private Constants() { }

    /**
     * 配置的属性变量.
     */
    private static Properties configProperties;

    public static void loadConfigProperties(final Properties props) {
        configProperties = props;
    }

    public static String getProperty(final String key) {
        return Constants.configProperties.getProperty(key);
    }

    public static String getProperty(final String key,Object... args) {
        String template = Constants.configProperties.getProperty(key);
        String result = "";
        if(StringUtils.isNotBlank(template)){
            for (int i = 0; i < args.length; i++) {
                String o = args[i] == null?"":args[i].toString();
                template.replaceAll("{"+i+"}", o);
            }
        }
        return result;
    }

    public static Properties getProperties() {
        return configProperties;
    }

    public static Number getNumberValue(final String key) {
        String value = Constants.configProperties.getProperty(key);
        if (NumberUtils.isNumber(value)) {
            return NumberUtils.createNumber(value);
        }
        return null;
    }

    public static Number getNumberValue(final String key, final Number defaultValue) {
        String value = Constants.configProperties.getProperty(key);
        if (NumberUtils.isNumber(value)) {
            return NumberUtils.createNumber(value);
        }
        return defaultValue;
    }

}
