package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.service.SysUserService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@RestController
@Api("后台管理用户接口")
@RequestMapping(value = "/sys/user")
public class SysUserController {
    @Autowired
    SysUserService userService;

    @ApiOperation("得到用户Item")
    @GetMapping("/item")
    public RestResult<SysUser> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(userService.getItem(id));
    }

    @ApiOperation("更新用户")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody SysUser sysUser) {
        return RestResultGenerator.genSuccessResult(userService.updateItem(sysUser));
    }


    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(userService.deleteItem(id));
    }


    @ApiOperation("添加用户")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody SysUser sysUser) {
        return RestResultGenerator.genSuccessResult(userService.addItem(sysUser));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到用户列表")
    public RestResult<ListResult<SysUser>> getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                           @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<SysUser> list = userService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<SysUser> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }

}
