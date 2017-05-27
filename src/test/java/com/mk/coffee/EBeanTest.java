package com.mk.coffee;

import com.mk.coffee.model.Ebean;
import com.mk.coffee.model.EbeanRecord;
import com.mk.coffee.service.EBeanServie;
import com.mk.coffee.utils.CommonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
public class EBeanTest extends BaseTestService {
    @Autowired
    CommonUtils commonUtils;
    @Autowired
    EBeanServie eBeanServie;
    @Test
    public void test() {
        Ebean ebean=eBeanServie.getEbeanByMemberId(100);
        EbeanRecord ebeanRecord=new EbeanRecord();
        ebeanRecord.seteNum(1000);
        ebeanRecord.setGivingNum(100);
        ebeanRecord.setTotalNum(1100);
        commonUtils.updateEbean(ebeanRecord,ebean);
    }
}
