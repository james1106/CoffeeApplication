package com.mk.coffee;

import com.mk.coffee.model.Members;
import com.mk.coffee.service.ProductConversionCodeService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/6 0006.
 */
public class ConversionCodeTest extends BaseTestService {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private ProductConversionCodeService service;

    @Test
    public void test() {
        Members members = service.getMembersByProductConversionCode("57892", 701);
        logger.info(members.getOpenId());
    }
}
