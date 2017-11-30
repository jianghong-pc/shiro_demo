package com.qianmi.demo.dao.mapper;

import com.qianmi.demo.pojo.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 基础用户信息操作，插入，删除，启/禁用
 */
@Repository
public interface UserMapper {

    /**
     * 根据用户名查询基础用户信息
     *
     * @author chenyunjie
     * @param loginName 用户名
     * @return 用户信息
     */
    User getUserByName(@Param("loginName") String loginName);

}
