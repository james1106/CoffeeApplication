package com.mk.coffee.controller.sys;

import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.mapper.MembersMapper;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.requestbody.RequestUpdateMember;
import com.mk.coffee.service.MembersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@Api("后台会员管理接口")
@RestController
@RequestMapping(value = "sys")
public class SysMemberController {
    @Autowired
    MembersService membersService;

    @Autowired
    private MembersMapper membersMapper;

    @GetMapping("/getMembers")
    @ApiOperation(value = "得到成员列表", httpMethod = "GET")
    public RestResult<ListResult<Members>> getMembersByPage(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        return RestResultGenerator.genSuccessResult(membersService.getListByPage(page, size));
    }

    @PutMapping("/updateMember")
    @ApiOperation(value = "修改会员")
    public RestResult<Boolean> updateMember(@RequestBody RequestUpdateMember updateMember) {
        return RestResultGenerator.genSuccessResult(membersService.updateMember(updateMember.members));
    }

    @PostMapping("/addMember")
    @ApiOperation(value = "增加成员")
    public RestResult<Boolean> addMember(@RequestBody RequestUpdateMember updateMember) {
        return RestResultGenerator.genSuccessResult(membersMapper.insert(updateMember.members) > 0);
    }

    @DeleteMapping("/deleteMember")
    @ApiOperation(value = "增加成员")
    public RestResult<Boolean> deleteMember(@RequestBody RequestUpdateMember updateMember) {
        return RestResultGenerator.genSuccessResult(membersMapper.deleteByPrimaryKey(updateMember.members.getId()) > 0);
    }
}
