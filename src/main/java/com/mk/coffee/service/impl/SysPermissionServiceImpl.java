package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.SysPermissionMapper;
import com.mk.coffee.model.SysPermission;
import com.mk.coffee.service.SysPermissionService;
import com.mk.coffee.utils.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> getSysPermissionsByRoleId(int roleId) {
        List<SysPermission> sysPermissions = sysPermissionMapper.getSysPermissionsByRoleId(roleId);
        if (EmptyUtils.isEmpty(sysPermissions)) {
            throw new AppException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return sysPermissions;
    }

    @Override
    public List<SysPermission> getSysPermissionsByUserId(int userId) {
        List<SysPermission> sysPermissions = sysPermissionMapper.getSysPermissionsByUserId(userId);
        if (EmptyUtils.isEmpty(sysPermissions)) {
            throw new AppException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return sysPermissions;
    }

    @Override
    public List<SysPermission> getList() {
        return sysPermissionMapper.selectByExample(null);
    }

    @Override
    public SysPermission getItem(int id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(SysPermission sysPermission) {
        return sysPermissionMapper.updateByPrimaryKeySelective(sysPermission) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return sysPermissionMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(SysPermission sysPermission) {
        return sysPermissionMapper.insert(sysPermission) > 0;
    }
}
