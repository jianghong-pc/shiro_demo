package com.qianmi.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * TODO
 * <p>Filename: com.qianmi.demo.config.MvcConfiguration.java</p>
 * <p>Date: 2017-12-01 18:29.</p>
 *
 * @author <a href="mailto:jianghong@qianmi.com">of837-姜洪</a>
 * @since v0.0.1
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PrincipalMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
}
