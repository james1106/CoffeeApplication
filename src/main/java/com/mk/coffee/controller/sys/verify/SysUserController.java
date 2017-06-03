package com.mk.coffee.controller.sys.verify;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.model.SysUserRole;
import com.mk.coffee.requestbody.RequestAddSysUser;
import com.mk.coffee.service.SysUserRoleService;
import com.mk.coffee.service.SysUserService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    SysUserRoleService userRoleService;

    @ApiOperation("得到用户Item")
    @GetMapping("/item")
    public RestResult<SysUser> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(userService.getItem(id));
    }


    @ApiOperation("更新用户")
    @PutMapping("/update")
    @Transactional
    public RestResult<Boolean> updateItem(@RequestBody SysUser sysUser) {
        SysUserRole sysUserRole = new SysUserRole(sysUser.getSysUserRoleId(), sysUser.getUserId(),
                sysUser.getSysRole().getRoleId(), null);
        userRoleService.updateItem(sysUserRole);
        return RestResultGenerator.genSuccessResult(userService.updateItem(sysUser));
    }


    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    @Transactional
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        SysUser sysUser = userService.getItem(id);
        return RestResultGenerator.genSuccessResult(userRoleService.deleteItem(sysUser.getSysUserRoleId())
                && userService.deleteItem(id));
    }


    @ApiOperation("添加用户")
    @PostMapping("/add")
    @Transactional
    public RestResult<Boolean> addItem(@RequestBody RequestAddSysUser requestAddSysUser) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(requestAddSysUser.username);
        sysUser.setStatus(0);
        sysUser.setPassword(requestAddSysUser.password);
        sysUser.setMobile(requestAddSysUser.mobile);
        sysUser.setEmail(requestAddSysUser.email);
        if (userService.existSysUserByMobile(requestAddSysUser.username)) {
            throw AppException.getException(ErrorCode.Mobile_Already_Exist);
        }
        userService.addItem(sysUser);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getUserId());
        sysUserRole.setRoleId(requestAddSysUser.sysRoleId);
        sysUserRole.setCreateDate(new Date());
        return RestResultGenerator.genSuccessResult(userRoleService.addItem(sysUserRole));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到用户列表")
    public RestResult<ListResult<SysUser>>
    getUserPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                 @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<SysUser> list = userService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<SysUser> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }


    @GetMapping("/search")
    @ApiOperation("根据手机号码，用户名，角色名搜索分页得到用户列表")
    public RestResult<ListResult<SysUser>>
    searchUserPages(@RequestParam(name = "keyword", required = false) String keyword,
                    @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                    @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<SysUser> list = userService.searchSysUser(keyword);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<SysUser> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }

    @GetMapping("/all")
    @ApiOperation("得到所有的用户列表")
    public RestResult<List<SysUser>> getUserList() {

        List<SysUser> list = userService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return RestResultGenerator.genSuccessResult(list);
    }

}
