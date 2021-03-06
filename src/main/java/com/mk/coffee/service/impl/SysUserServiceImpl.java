package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.SysUserMapper;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.model.SysUserExample;
import com.mk.coffee.model.SysUserKey;
import com.mk.coffee.service.SysUserService;
import com.mk.coffee.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return sysUserMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public List<SysUser> searchSysUser(String keyword) {
        return sysUserMapper.searchSysUser(keyword);
    }

    @Override
    public long count() {
        return sysUserMapper.countByExample(null);
    }

    @Override
    public boolean existSysUserByMobile(String mobile) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andMobileEqualTo(mobile);
        return sysUserMapper.selectByExample(example) != null;
    }

    @Override
    public List<SysUser> getList() {
        return sysUserMapper.getSysUsers();
    }

    @Override
    public SysUser getItem(int id) {

        return sysUserMapper.getSysUserById(id);
    }

    @Override
    public boolean updateItem(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKeySelective(sysUser) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return sysUserMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(SysUser sysUser) {
        sysUser.setCreateDate(new Date());
        sysUser.setUpdateDate(new Date());
        //验证字段
        sysUser.setSalt(MD5Util.md5Encode(sysUser.getMobile() + sysUser.getPassword(), null));
        return sysUserMapper.insert(sysUser) > 0;
    }
}
