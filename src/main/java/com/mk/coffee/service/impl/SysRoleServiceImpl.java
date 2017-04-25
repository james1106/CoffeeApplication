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
}
