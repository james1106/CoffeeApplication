package com.mk.coffee.service;

import com.mk.coffee.model.SysUserRole;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public interface SysUserRoleService extends IBaseService<SysUserRole> {

    /**
     * 判断该用户是否已属于该角色
     * @param userId
     * @param roleId
     * @return
     */
    boolean existSysUserRole(int userId, int roleId);
}
