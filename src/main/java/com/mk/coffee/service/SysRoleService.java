package com.mk.coffee.service;

import com.mk.coffee.model.SysRole;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public interface SysRoleService {

    List<SysRole> getSysRoles(int userId);
}
