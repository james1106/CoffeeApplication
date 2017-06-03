package com.mk.coffee.service;

import com.mk.coffee.model.SysPermission;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public interface SysPermissionService extends IBaseService<SysPermission> {


    List<SysPermission> getSysPermissionsByRoleId(int roleId);

    List<SysPermission> getSysPermissionsByUserId(int userId);
}
