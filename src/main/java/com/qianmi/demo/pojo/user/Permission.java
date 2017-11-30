package com.qianmi.demo.pojo.user;

import com.qianmi.demo.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Permission extends BaseEntity {

    /**
     * 权限编号
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String name;
}
