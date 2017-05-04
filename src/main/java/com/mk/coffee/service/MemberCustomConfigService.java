package com.mk.coffee.service;

import com.mk.coffee.model.CustomConfig;
import com.mk.coffee.model.MemberCustomConfig;

import java.util.List;

/**
 * 成员-配方表
 * Created by Administrator on 2017/5/4 0004.
 */
public interface MemberCustomConfigService {
    boolean saveMemberCustomConfig(long memberId, int customConfigId);//加入到我的调制

    boolean saveMemberCustomConfig(long memberId, CustomConfig customConfig);//加入到我的调制

    boolean deleteMemberCustomConfig(long memberId, int customConfigId);

    List<MemberCustomConfig> getMyMemberCustomConfig(long memberId);//得到我的调制配方

}
