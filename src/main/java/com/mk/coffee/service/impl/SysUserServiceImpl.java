package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.SysUserMapper;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/8 0008.
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser loginAdmin(String userName, String password) {
        return sysUserMapper.loginAdmin(userName,password);
    }

    @Override
    public boolean updateById(SysUser user) {
        return sysUserMapper.updateByPrimaryKey(user)>0;
    }
}
