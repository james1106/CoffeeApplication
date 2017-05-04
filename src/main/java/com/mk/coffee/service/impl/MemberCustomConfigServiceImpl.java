package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.CustomConfigMapper;
import com.mk.coffee.mapper.MemberCustomConfigMapper;
import com.mk.coffee.model.CustomConfig;
import com.mk.coffee.model.MemberCustomConfig;
import com.mk.coffee.model.MemberCustomConfigExample;
import com.mk.coffee.service.MemberCustomConfigService;
import com.mk.coffee.utils.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
@Service
public class MemberCustomConfigServiceImpl implements MemberCustomConfigService {
    @Autowired
    private MemberCustomConfigMapper memberCustomConfigMapper;

    @Autowired
    private CustomConfigMapper customConfigMapper;

    @Override
    public boolean saveMemberCustomConfig(long memberId, int customConfigId) {
        CustomConfig customConfig = customConfigMapper.selectByPrimaryKey(customConfigId);
        if (customConfig == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        MemberCustomConfig memberCustomConfig = new MemberCustomConfig();
        memberCustomConfig.setMemberId(memberId);
        memberCustomConfig.setCreateDate(new Date());
        memberCustomConfig.setCustomConfigId(customConfigId);
        return memberCustomConfigMapper.insert(memberCustomConfig) > 0;
    }

    @Transactional
    @Override
    public boolean saveMemberCustomConfig(long memberId, CustomConfig customConfig) {
        if (customConfig == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        customConfigMapper.insert(customConfig);
        MemberCustomConfig memberCustomConfig = new MemberCustomConfig();
        memberCustomConfig.setMemberId(memberId);
        memberCustomConfig.setCreateDate(new Date());
        memberCustomConfig.setCustomConfigId(customConfig.getId());
        return memberCustomConfigMapper.insert(memberCustomConfig) > 0;
    }

    @Override
    public boolean deleteMemberCustomConfig(long memberId, int customConfigId) {
        MemberCustomConfigExample example=new MemberCustomConfigExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andCustomConfigIdEqualTo(customConfigId);
        return memberCustomConfigMapper.deleteByExample(example)>0;
    }

    @Override
    public List<MemberCustomConfig> getMyMemberCustomConfig(long memberId) {
        List<MemberCustomConfig> list = memberCustomConfigMapper.getMyMemberCustomConfig(memberId);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return list;
    }
}
