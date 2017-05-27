package com.mk.coffee.service;

import com.mk.coffee.model.Ebean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public interface EBeanServie extends IBaseService<Ebean> {
    Ebean getEbeanByMemberId(long memberId);

    List<Ebean> searchEbean(String keyword);


}
