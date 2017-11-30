package com.qianmi.demo.pojo;


import lombok.Getter;
import org.apache.shiro.SecurityUtils;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 3786346180520124693L;

    @Getter
    private String chainMasterId;

    public BaseEntity (){
        this.chainMasterId = (String)SecurityUtils.getSubject().getPrincipal();
    }

}