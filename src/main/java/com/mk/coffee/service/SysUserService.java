package com.mk.coffee.service;

import com.mk.coffee.model.SysUser;

/**
 * Created by Administrator on 2017/4/8 0008.
 */
public interface SysUserService {
    SysUser loginAdmin(String userName, String password);

    boolean updateById(SysUser user);
}
