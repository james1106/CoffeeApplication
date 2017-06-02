package com.mk.coffee.service;

import com.mk.coffee.model.SysUser;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8 0008.
 */
public interface SysUserService extends IBaseService<SysUser> {
    SysUser loginAdmin(String username, String password);

    boolean updateById(SysUser user);

    List<SysUser> searchSysUser(String keyword);

    long count();

}
