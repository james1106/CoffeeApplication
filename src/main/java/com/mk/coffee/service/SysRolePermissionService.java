package com.mk.coffee.service;

import com.mk.coffee.model.SysRolePermission;

import java.util.List;

/**
 * 角色添加权限服务
 * Created by Administrator on 2017/6/5 0005.
 */
public interface SysRolePermissionService extends IBaseService<SysRolePermission> {

    /**
     * 通过角色id得到该角色的所有权限
     *
     * @return
     */
    List<SysRolePermission> getSysRolePermissionListByRoleId(int roleId);

    /**
     * 通过角色id批量添加权限
     *
     * @param roleId
     * @param ids
     * @return
     */
    boolean addRolePermissionsByRoleId(int roleId, int[] ids);


}
