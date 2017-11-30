package com.qianmi.demo.pojo.brand;

import com.qianmi.demo.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Brand extends BaseEntity {

    private static final long serialVersionUID = -8029875489712443626L;

    private String chainMasterId;

    private Integer id;

    private String name;
    
}