package com.mk.coffee.controller.sys.verify;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.model.SysPermission;
import com.mk.coffee.service.SysPermissionService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3 0003.
 */
@RestController
@Api("后台管理权限接口")
@RequestMapping(value = "/sys/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @GetMapping("/list")
    @ApiOperation("分页得到权限列表")
    public RestResult<ListResult<SysPermission>>
    getList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        SecurityUtils.getSubject().checkPermission("sys:permission:view");
        PageHelper.startPage(page, size);
        List<SysPermission> list = sysPermissionService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<SysPermission> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }


    @GetMapping("/all")
    @ApiOperation("得到所有权限")
    public RestResult<List<SysPermission>> getAllPermission() {
        SecurityUtils.getSubject().checkPermission("sys:permission:view");
        List<SysPermission> list = sysPermissionService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return RestResultGenerator.genSuccessResult(list);
    }


    @ApiOperation("得到权限Item")
    @GetMapping("/item")
    public RestResult<SysPermission> getItem(@RequestParam("id") int id) {
        SecurityUtils.getSubject().checkPermission("sys:permission:view");
        return RestResultGenerator.genSuccessResult(sysPermissionService.getItem(id));
    }

    @ApiOperation("更新权限")
    @PutMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody SysPermission sysPermission) {
        SecurityUtils.getSubject().checkPermission("sys:permission:update");
        return RestResultGenerator.genSuccessResult(sysPermissionService.updateItem(sysPermission));
    }


    @ApiOperation("删除权限")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        SecurityUtils.getSubject().checkPermission("sys:permission:delete");
        return RestResultGenerator.genSuccessResult(sysPermissionService.deleteItem(id));
    }


    @ApiOperation("添加权限")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody SysPermission sysPermission) {
        SecurityUtils.getSubject().checkPermission("sys:permission:create");
        sysPermission.setCreateDate(new Date());
        return RestResultGenerator.genSuccessResult(sysPermissionService.addItem(sysPermission));
    }


}
