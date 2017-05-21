package com.mk.coffee.service;

import com.mk.coffee.model.EbeanRecord;

import java.util.List;

/**
 * e豆充值纪录
 * Created by Administrator on 2017/5/7 0007.
 */
public interface EBeanRecordService {
    List<EbeanRecord> getList();


    EbeanRecord getItem(long id);


    boolean updateItem(EbeanRecord ebeanRecord);


    boolean deleteItem(long id);


    boolean addItem(EbeanRecord ebeanRecord);

    //搜索e豆充值纪录
    List<EbeanRecord> searchEbeanRecord(String keyword, Integer payState);
}
