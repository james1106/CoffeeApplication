package com.mk.coffee;

import com.mk.coffee.model.Ebean;
import com.mk.coffee.model.EbeanRecord;
import com.mk.coffee.service.EBeanServie;
import com.mk.coffee.service.SysUserService;
import com.mk.coffee.utils.CommonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
public class EBeanTest extends BaseTestService {
    @Autowired
    SysUserService sysUserService;
    @Test
    public void test() {
        sysUserService.loginAdmin("15088138597", "admin");
    }
}
