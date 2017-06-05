package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.SysRolePermissionMapper;
import com.mk.coffee.model.SysRoleExample;
import com.mk.coffee.model.SysRolePermission;
import com.mk.coffee.model.SysRolePermissionExample;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.service.SysRolePermissionService;
import com.mk.coffee.utils.EmptyUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */
@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<SysRolePermission> getList() {
        return sysRolePermissionMapper.selectByExample(null);
    }

    @Override
    public SysRolePermission getItem(int id) {
        return sysRolePermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(SysRolePermission sysRolePermission) {
        return sysRolePermissionMapper.updateByPrimaryKeySelective(sysRolePermission) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return sysRolePermissionMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(SysRolePermission sysRolePermission) {
        return sysRolePermissionMapper.insertSelective(sysRolePermission) > 0;
    }

    @Override
    public List<SysRolePermission> getSysRolePermissionListByRoleId(int roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<SysRolePermission> list = sysRolePermissionMapper.selectByExample(example);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return list;
    }

    @Override
    @Transactional
    public boolean addRolePermissionsByRoleId(int roleId, int[] ids) {
        boolean addRolePermission = false;
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        sysRolePermissionMapper.deleteByExample(example);//清除旧纪录
        for (int i = 0; i < ids.length; i++) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setPermissionId(ids[i]);
            sysRolePermission.setRoleId(roleId);
            sysRolePermission.setCreateDate(new Date());
            sysRolePermission.setUpdateDate(new Date());
            sysRolePermission.setCreateId(sysUser.getUserId());
            sysRolePermission.setUpdateId(sysUser.getUpdateId());
            addRolePermission = sysRolePermissionMapper.insertSelective(sysRolePermission) > 0;
        }
        return addRolePermission;
    }
}
