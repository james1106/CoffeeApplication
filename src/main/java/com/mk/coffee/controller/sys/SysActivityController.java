package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Activity;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.model.Product;
import com.mk.coffee.service.ActivityService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@Api("活动后台管理接口")
@RestController
@RequestMapping(value = "/sys/activity")
public class SysActivityController {
    @Autowired
    private ActivityService activityService;

    @ApiOperation("得到活动Item")
    @GetMapping("/item")
    public RestResult<Activity> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(activityService.getItem(id));
    }


    @ApiOperation("更新商品")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody Activity activity) {
        return RestResultGenerator.genSuccessResult(activityService.updateItem(activity));
    }


    @ApiOperation("删除Activity")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(activityService.deleteItem(id));
    }


    @ApiOperation("添加活动Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody Activity activity) {
        return RestResultGenerator.genSuccessResult(activityService.addItem(activity));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到活动列表")
    public RestResult<ListResult<Activity>> getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<Activity> list = activityService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<Activity> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }
}
