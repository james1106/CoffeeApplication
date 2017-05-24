package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.SysUserRoleMapper;
import com.mk.coffee.model.SysUserRole;
import com.mk.coffee.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> getList() {
        return sysUserRoleMapper.getUserRoleList();
    }

    @Override
    public SysUserRole getItem(int id) {
        return sysUserRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(SysUserRole sysUserRole) {
        return sysUserRoleMapper.updateByPrimaryKey(sysUserRole) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return sysUserRoleMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole) > 0;
    }
}
