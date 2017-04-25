package com.mk.coffee.controller.rest;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.model.Activity;
import com.mk.coffee.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
@Api("活动接口")
@RestController
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/activityList")
    @ApiOperation(value = "得到活动列表", notes = "得到正在进行的活动列表")
    public RestResult<List<Activity>> getActivityList() {
        return RestResultGenerator.genSuccessResult(activityService.getActivityList());
    }

}
