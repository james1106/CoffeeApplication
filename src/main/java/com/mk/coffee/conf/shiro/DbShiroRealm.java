package com.mk.coffee.conf.shiro;

import com.mk.coffee.model.SysPermission;
import com.mk.coffee.model.SysRole;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.service.SysPermissionService;
import com.mk.coffee.service.SysRoleService;
import com.mk.coffee.service.SysUserService;
import com.mk.coffee.utils.EmptyUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/7 0007.
 */
public class DbShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限认证方法：MyShiroRealm.doGetAuthorizationInfo()");
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int userId = user.getUserId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //根据用户ID查询角色（role），放入到Authorization里。
        Set<String> roleSet = new HashSet<String>();
        List<SysRole> sysRoles = sysRoleService.getSysRoles(userId);
        if (EmptyUtils.isEmpty(sysRoles)) {
            throw new AuthorizationException("该用户没有所属角色");
        }
        for (SysRole role : sysRoles) {
            roleSet.add(role.getRoleType());

        }
        info.setRoles(roleSet);

        //根据用户ID查询权限（permission），放入到Authorization里。
        List<SysPermission> permissionList = permissionService.getSysPermissionsByUserId(userId);
        Set<String> permissionSet = new HashSet<>();
        for (SysPermission permission : permissionList) {
            permissionSet.add(permission.getName());

        }
        info.setStringPermissions(permissionSet);
        return info;
    }


    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        //查询用户信息
        SysUser user = sysUserService.loginAdmin(username, password);

        //账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 1) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        //登录成功,更新登录时间 last login time
        user.setUpdateDate(new Date());
        sysUserService.updateById(user);
        Logger.getLogger(getClass()).info("身份认证成功，登录用户：" + username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
