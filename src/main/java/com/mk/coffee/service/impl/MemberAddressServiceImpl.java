package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.AddressMapper;
import com.mk.coffee.mapper.MemberAddressMapper;
import com.mk.coffee.model.Address;
import com.mk.coffee.model.MemberAddress;
import com.mk.coffee.model.MemberAddressExample;
import com.mk.coffee.service.MemberAddressService;
import com.mk.coffee.utils.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@Service
public class MemberAddressServiceImpl implements MemberAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private MemberAddressMapper memberAddressMapper;

    @Override
    public List<MemberAddress> getList() {
        return memberAddressMapper.selectByExample(null);
    }

    @Override
    public MemberAddress getItem(int id) {
        return memberAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public boolean updateItem(MemberAddress memberAddress) {
        boolean updateFlag;
        Address address = memberAddress.getAddress();
        updateFlag = addressMapper.updateByPrimaryKeySelective(address) > 0;

        memberAddress.setAddressId(address.getId());
        return updateFlag && memberAddressMapper.updateByPrimaryKeySelective(memberAddress) > 0;
    }

    @Override
    public boolean deleteItem(int id) {

        return memberAddressMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    @Transactional
    public boolean addItem(MemberAddress memberAddress) {
        boolean addFlag;
        Address address = memberAddress.getAddress();
        address.setCreateDate(new Date());
        addFlag = addressMapper.insert(address) > 0;

        memberAddress.setAddressId(address.getId());
        return addFlag && memberAddressMapper.insertSelective(memberAddress) > 0;
    }

    @Override
    public List<MemberAddress> getMemberAddressByMemberId(long memberId) {
        List<MemberAddress> list = memberAddressMapper.getMemberAddressByMemberId(memberId);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return list;
    }

    @Override
    @Transactional
    public boolean selectedMemberAddress(long memberId, int memberAddressId) {
        boolean clearFlag;
        //重置选中
        MemberAddressExample example = new MemberAddressExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setIsSelected(false);
        clearFlag = memberAddressMapper.updateByExampleSelective(memberAddress, example) > 0;

        //选中
        MemberAddress memberAddress1 = new MemberAddress();
        memberAddress1.setId(memberAddressId);
        memberAddress1.setIsSelected(true);
        return clearFlag && memberAddressMapper.updateByPrimaryKeySelective(memberAddress1) > 0;
    }

    @Override
    public MemberAddress getSelectedMemberAddress(long memberId) {
        MemberAddress memberAddress = memberAddressMapper.getSelectedMemberAddress(memberId);
        if (memberAddress == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return memberAddress;
    }
}
