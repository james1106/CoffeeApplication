package com.mk.coffee.controller.sys.verify;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.SysPermission;
import com.mk.coffee.model.SysRolePermission;
import com.mk.coffee.requestbody.RequestAddRolePermission;
import com.mk.coffee.service.SysRolePermissionService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */
@Api("后台管理角色权限接口")
@RestController
@RequestMapping(value = "/sys/rolePermission")
public class SysRolePermissionController {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @ApiOperation("得到角色所有的权限")
    @GetMapping("/getListByRoleId")
    public RestResult<List<SysRolePermission>> getSysRolePermissionListByRoleId(@RequestParam("roleId") int roleId) {
        return RestResultGenerator.genSuccessResult(sysRolePermissionService.getSysRolePermissionListByRoleId(roleId));
    }

    @ApiOperation("批量添加角色权限")
    @PostMapping("/addByRoleId")
    public RestResult<Boolean> addRolePermissionsByRoleId(@RequestBody RequestAddRolePermission requestAddRolePermission) {
        return RestResultGenerator.genSuccessResult(sysRolePermissionService.
                addRolePermissionsByRoleId(requestAddRolePermission.roleId, requestAddRolePermission.ids));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到角色权限列表")
    public RestResult<ListResult<SysRolePermission>>
    getSysRolePermissionListByPage(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<SysRolePermission> list = sysRolePermissionService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<SysRolePermission> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }

}
