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
}
