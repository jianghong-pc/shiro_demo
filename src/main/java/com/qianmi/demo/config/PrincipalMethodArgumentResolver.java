package com.qianmi.demo.config;

import com.qianmi.demo.pojo.user.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * TODO
 * <p>Filename: com.qianmi.demo.config.PrincipalMethodArgumentResolver.java</p>
 * <p>Date: 2017-12-01 18:04.</p>
 *
 * @author <a href="mailto:jianghong@qianmi.com">of837-姜洪</a>
 * @since v0.0.1
 */
public class PrincipalMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        return User.class.isAssignableFrom(paramType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            throw new RuntimeException("user is null!");
        }
        return user;
    }
}
