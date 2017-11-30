package com.qianmi.demo.config.shiro;

import com.qianmi.demo.dao.mapper.PermissionMapper;
import com.qianmi.demo.dao.mapper.RoleMapper;
import com.qianmi.demo.dao.mapper.UserMapper;
import com.qianmi.demo.pojo.user.Permission;
import com.qianmi.demo.pojo.user.Role;
import com.qianmi.demo.pojo.user.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  自定义身份认证
 * </p>
 */
public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        //到数据库查是否有此对象
        User sysUser = userMapper.getUserByName(loginName);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        if (sysUser != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            List<Role> roleList = roleMapper.listByAdminUserId(sysUser.getId());
            info.setRoles(sysUser.getRoleNames(roleList));
            //用户的角色对应的所有权限
            List<Permission> permissionList = permissionMapper.listByRoleIds(roleList.stream().map(Role::getId).collect(Collectors.toList()));
            info.addStringPermissions(permissionList.stream().map(Permission::getName).collect(Collectors.toList()));
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        logger.info("验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));

        //查出是否有此用户
        User adminUser = userMapper.getUserByName(token.getUsername());
        if (adminUser != null) {
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(adminUser.getUsername(), adminUser.getPassword(), ByteSource.Util.bytes(adminUser.getUsername()+adminUser.getId()), getName());
        }
        return null;
    }

    public static void main(String[] args) {

        String md5 = new Md5Hash("123456","of21622").toString();//source:密码 salt:用户名+用户编号
        System.err.println(md5);
    }
}