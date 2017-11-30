package com.qianmi.demo.pojo.user;

import com.qianmi.demo.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role extends BaseEntity {

    /**
     * 角色编号
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

}
