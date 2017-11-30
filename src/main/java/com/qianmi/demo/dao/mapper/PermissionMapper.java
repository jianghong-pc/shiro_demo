package com.qianmi.demo.dao.mapper;

import com.qianmi.demo.pojo.user.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户权限操作，插入，删除，启/禁用
 */
@Repository
public interface PermissionMapper {

    /**
     * 根据用户角色编号批量查询用户权限
     *
     * @param roleIds 角色编号集合
     * @return 权限列表
     */
    List<Permission> listByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
