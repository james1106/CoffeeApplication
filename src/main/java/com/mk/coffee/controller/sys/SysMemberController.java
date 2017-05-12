package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.MembersMapper;
import com.mk.coffee.model.Activity;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.MembersExample;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.requestbody.RequestUpdateMember;
import com.mk.coffee.service.MembersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

        Members member = membersMapper.getMemberById(updateMember.members.getId());
        member.setName(updateMember.members.getName());
        member.setPhone(updateMember.members.getPhone());
        member.setIsRegist(updateMember.members.getIsRegist());
        member.setSex(updateMember.members.getSex());
        member.setEmail(updateMember.members.getEmail());
        member.setHeadportraitUrl(updateMember.members.getHeadportraitUrl());
        return RestResultGenerator.genSuccessResult(membersMapper.updateByPrimaryKey(member) > 0);
    }

    @PostMapping("/addMember")
    @ApiOperation(value = "增加成员")
    public RestResult<Boolean> addMember(@RequestBody RequestUpdateMember updateMember) {
        updateMember.members.setId(System.currentTimeMillis());
        updateMember.members.setCreateDate(new Date());
        return RestResultGenerator.genSuccessResult(membersMapper.insert(updateMember.members) > 0);
    }

    @DeleteMapping("/deleteMember")
    @ApiOperation(value = "删除成员")
    public RestResult<Boolean> deleteMember(@RequestBody RequestUpdateMember updateMember) {
        return RestResultGenerator.genSuccessResult(membersMapper.deleteByPrimaryKey(updateMember.members.getId()) > 0);
    }

    @GetMapping("/searchMemberByKeyword")
    @ApiOperation(value = "搜索成员")
    public RestResult<ListResult<Members>>
    searchMemberByKeyword(@RequestParam("keyword") String keyword,
                          @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);


        MembersExample example = null;
        if (keyword == null && keyword.equals("")) {
            example = new MembersExample();
            example.or().andNameLike("%" + keyword + "%");
            example.or().andPhoneLike("%" + keyword + "%");
        }
        List<Members> list = membersMapper.selectByExample(example);
        if (list == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<Members> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }
}
