package com.qianmi.demo.config;

import com.qianmi.demo.common.consts.Constants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.Properties;

/**
 * <p>
 *    读取配置文件配置
 * <p/>
 */
@Configuration
public class PropertiesConfiguration implements InitializingBean {

    private static final String ERROR_CODES_LOCATIONS = "classpath*:/application.properties";

    @Bean
    public Properties codesProperties() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setIgnoreResourceNotFound(true);
        bean.setLocalOverride(true);
        bean.setLocations(resolver.getResources(ERROR_CODES_LOCATIONS));
        bean.setSingleton(true);
        bean.afterPropertiesSet();

        return bean.getObject();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Constants.loadConfigProperties(codesProperties());
    }
}
