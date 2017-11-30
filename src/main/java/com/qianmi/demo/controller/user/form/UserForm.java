package com.qianmi.demo.controller.user.form;

import com.qianmi.demo.common.util.Pagination;
import com.qianmi.demo.pojo.user.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserForm extends Pagination {

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    public User toUser(){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}