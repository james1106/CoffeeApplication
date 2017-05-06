package com.mk.coffee;

import com.mk.coffee.service.ProductConversionCodeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/6 0006.
 */
public class ConversionCodeTest extends BaseTestService {
    @Autowired
    private ProductConversionCodeService service;

    @Test
    public void test() {
        service.updateProductConversionState("57892","701");
    }
}
