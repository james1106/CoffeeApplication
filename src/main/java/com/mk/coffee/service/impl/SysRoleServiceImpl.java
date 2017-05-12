package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.SysRoleMapper;
import com.mk.coffee.model.SysRole;
import com.mk.coffee.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getSysRoles(int userId) {
        return sysRoleMapper.getSysRoles(userId);
    }

    @Override
    public List<SysRole> getList() {
        return sysRoleMapper.selectByExample(null);
    }

    @Override
    public SysRole getItem(int id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKey(sysRole) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return sysRoleMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole) > 0;
    }
}
