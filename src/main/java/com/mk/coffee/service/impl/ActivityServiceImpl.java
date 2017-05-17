package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.ActivityMapper;
import com.mk.coffee.model.Activity;
import com.mk.coffee.service.ActivityService;
import com.mk.coffee.utils.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> getActivityList() {
        List<Activity> activityList = activityMapper.getActivityList();
        if (EmptyUtils.isEmpty(activityList)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return activityList;
    }

    @Override
    public List<Activity> getList() {
        return activityMapper.selectByExample(null);
    }

    @Override
    public Activity getItem(int id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(Activity activity) {
        return activityMapper.updateByPrimaryKeySelective(activity) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return activityMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(Activity activity) {
        return activityMapper.insert(activity) > 0;
    }
}
