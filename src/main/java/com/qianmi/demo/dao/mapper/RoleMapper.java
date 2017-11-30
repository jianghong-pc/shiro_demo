package com.qianmi.demo.dao.mapper;

import com.qianmi.demo.pojo.user.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户角色操作，插入，删除，启/禁用
 */
@Repository
public interface RoleMapper {

    /**
     * 根据用户编号查询用户角色
     *
     * @param id 用户编号
     * @return 角色列表
     */
    List<Role> listByAdminUserId(@Param("id") Integer id);
}
