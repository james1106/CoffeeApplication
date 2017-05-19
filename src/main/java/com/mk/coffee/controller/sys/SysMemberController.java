package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.MembersMapper;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.MembersExample;
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
    public RestResult<Boolean> updateMember(@RequestBody Members members) {
        if(!members.getIsRegist()){
            members.setPhone(null);
        }
        return RestResultGenerator.genSuccessResult(membersMapper.updateByPrimaryKey(members) > 0);
    }

    @PostMapping("/addMember")
    @ApiOperation(value = "增加成员")
    public RestResult<Boolean> addMember(@RequestBody Members members) {
        members.setId(System.currentTimeMillis());
        members.setCreateDate(new Date());
        return RestResultGenerator.genSuccessResult(membersMapper.insert(members) > 0);
    }

    @DeleteMapping("/deleteMember")
    @ApiOperation(value = "删除成员")
    public RestResult<Boolean> deleteMember(@RequestParam("id") long id) {
        return RestResultGenerator.genSuccessResult(membersMapper.deleteByPrimaryKey(id) > 0);
    }

    @GetMapping("/searchMemberByKeyword")
    @ApiOperation(value = "搜索成员")
    public RestResult<ListResult<Members>>
    searchMemberByKeyword(@RequestParam("keyword") String keyword,
                          @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        MembersExample example = null;
        if (keyword != null && !keyword.equals("")) {
            example = new MembersExample();
            if (keyword.equals("会员")) {
                example.or().andIsRegistEqualTo(true);
            } else if (keyword.equals("游客")) {
                example.or().andIsRegistEqualTo(false);
            } else if (keyword.equals("测试")) {
                example.or().andIsTestEqualTo(true);
            } else {
                example.or().andIdEqualTo(Long.parseLong(keyword));
                example.or().andNameLike("%" + keyword + "%");
                example.or().andPhoneLike("%" + keyword + "%");
            }
        }
        List<Members> list = membersMapper.selectByExample(example);
        if (list == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<Members> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }
}
