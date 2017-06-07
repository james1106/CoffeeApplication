package com.mk.coffee.controller.rest;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.model.Address;
import com.mk.coffee.model.MemberAddress;
import com.mk.coffee.requestbody.RequestMemberAddress;
import com.mk.coffee.service.MemberAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@Api("收货地址接口")
@RestController
public class AddressController {

    @Autowired
    private MemberAddressService memberAddressService;

    @ApiOperation("添加会员收货地址")
    @PostMapping("/addMemberAddress")
    public RestResult<Boolean> addMemberAddress(@RequestBody RequestMemberAddress requestMemberAddress) {
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setCreateDate(new Date());
        memberAddress.setMemberId(requestMemberAddress.memberId);
        memberAddress.setAddress(requestMemberAddress.address);

        return RestResultGenerator.genSuccessResult(memberAddressService.addItem(memberAddress));
    }

    @ApiOperation("得到会员收货地址列表")
    @GetMapping("getMemberAddressByMemberId")
    public RestResult<List<MemberAddress>> getMemberAddressByMemberId(@RequestParam("memberId") long memberId) {
        return RestResultGenerator.genSuccessResult(memberAddressService.getMemberAddressByMemberId(memberId));
    }


    @ApiOperation("编辑会员收货地址")
    @PutMapping("/updateMemberAddress")
    public RestResult<Boolean> updateMemberAddress(@RequestBody MemberAddress memberAddress) {
        return RestResultGenerator.genSuccessResult(memberAddressService.updateItem(memberAddress));
    }


    @ApiOperation("设置常用收货地址")
    @PutMapping("/selectedMemberAddress")
    public RestResult<Boolean> selectedMemberAddress(@RequestParam("memberId") long memberId,
                                                     @RequestParam("memberAddressId") int memberAddressId) {
        return RestResultGenerator.genSuccessResult(memberAddressService.selectedMemberAddress(memberId, memberAddressId));
    }

    @ApiOperation("得到会员常用收货地址")
    @GetMapping("/getSelectedMemberAddress")
    public RestResult<MemberAddress> getSelectedMemberAddress(@RequestParam("memberId") long memberId) {
        return RestResultGenerator.genSuccessResult(memberAddressService.getSelectedMemberAddress(memberId));
    }

}
