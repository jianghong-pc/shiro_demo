package com.qianmi.demo.pojo.user;

import com.qianmi.demo.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
public class User extends BaseEntity {

    private static final long serialVersionUID = 6564426284917169889L;

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    private String chainMasterId;

    /**
     * 获取角色名称
     *
     * @param roleList 角色列表
     * @return 角色名称
     */
    public Set<String> getRoleNames(List<Role> roleList){
       return roleList.stream().map(Role::getName).collect(Collectors.toSet());
    }

}
