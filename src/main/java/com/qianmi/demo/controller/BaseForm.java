package com.qianmi.demo.controller;

import com.qianmi.demo.common.util.Pagination;
import com.qianmi.demo.pojo.user.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;

@Setter
@Getter
public class BaseForm extends Pagination {

    private static final long serialVersionUID = -4759092006853633661L;

    private String chainMasterId;

    public BaseForm(){
        this.chainMasterId = ((User)SecurityUtils.getSubject().getPrincipal()).getChainMasterId();
    }

}