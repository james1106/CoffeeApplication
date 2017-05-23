package com.mk.coffee;

import com.mk.coffee.model.Members;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.task.AsyncTask;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/6 0006.
 */
public class ConversionCodeTest extends BaseTestService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProductConversionCodeService service;

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void test() {

        try {
            asyncTask.doTask1(111111111);
            Thread.sleep(1000 * 90);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
