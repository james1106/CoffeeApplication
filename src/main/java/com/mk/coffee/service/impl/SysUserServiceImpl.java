package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.SysUserMapper;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.model.SysUserKey;
import com.mk.coffee.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8 0008.
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser loginAdmin(String userName, String password) {
        return sysUserMapper.loginAdmin(userName, password);
    }

    @Override
    public boolean updateById(SysUser user) {
        return sysUserMapper.updateByPrimaryKey(user) > 0;
    }

    @Override
    public List<SysUser> getList() {
        return sysUserMapper.selectByExample(null);
    }

    @Override
    public SysUser getItem(int id) {
        SysUserKey sysUserKey = new SysUser();
        sysUserKey.setUserId(id);
        return sysUserMapper.selectByOrPrimaryKey(sysUserKey);
    }

    @Override
    public boolean updateItem(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKey(sysUser) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        SysUserKey sysUserKey = new SysUser();
        sysUserKey.setUserId(id);
        return sysUserMapper.deleteByOrPrimaryKey(sysUserKey) > 0;
    }

    @Override
    public boolean addItem(SysUser sysUser) {
        return sysUserMapper.insert(sysUser) > 0;
    }
}
