package com.mk.coffee.service;

import com.mk.coffee.model.MemberAddress;

import java.util.List;

/**
 * 收货地址服务
 * Created by Administrator on 2017/6/7 0007.
 */
public interface MemberAddressService extends IBaseService<MemberAddress> {


    /**
     * 得到用户的收货地址列表
     */
    List<MemberAddress> getMemberAddressByMemberId(long memberId);


    /**
     * 设置常用地址
     */
    boolean selectedMemberAddress(long memberId, int memberAddressId);


    /**
     * 得到常用地址
     */
    MemberAddress getSelectedMemberAddress(long memberId);
}
