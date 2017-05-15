package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.SysLogMapper;
import com.mk.coffee.model.SysLog;
import com.mk.coffee.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public List<SysLog> getList() {
        return sysLogMapper.selectByExample(null);
    }

    @Override
    public SysLog getItem(int id) {
        return sysLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(SysLog sysLog) {
        return sysLogMapper.updateByPrimaryKey(sysLog) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return sysLogMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(SysLog sysLog) {
        return sysLogMapper.insert(sysLog) > 0;
    }
}
