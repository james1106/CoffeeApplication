package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Activity;
import com.mk.coffee.model.SysRole;
import com.mk.coffee.service.SysRoleService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@Api("后台管理角色接口")
@RestController
@RequestMapping(value = "/sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("得到角色Item")
    @GetMapping("/item")
    public RestResult<SysRole> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(sysRoleService.getItem(id));
    }


    @ApiOperation("更新角色")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody SysRole sysRole) {
        return RestResultGenerator.genSuccessResult(sysRoleService.updateItem(sysRole));
    }


    @ApiOperation("删除角色")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(sysRoleService.deleteItem(id));
    }


    @ApiOperation("添加角色Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody SysRole sysRole) {
        return RestResultGenerator.genSuccessResult(sysRoleService.addItem(sysRole));
    }

    @GetMapping("/all")
    @ApiOperation("得到所有角色列表")
    public RestResult<List<SysRole>> getList() {
        List<SysRole> list = sysRoleService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return RestResultGenerator.genSuccessResult(list);
    }

    @GetMapping("/list")
    @ApiOperation("分页得到角色列表")
    public RestResult<ListResult<SysRole>> getListPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<SysRole> list = sysRoleService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<SysRole> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }
}
