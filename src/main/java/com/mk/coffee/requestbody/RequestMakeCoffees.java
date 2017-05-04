package com.mk.coffee.requestbody;

import com.mk.coffee.model.CustomConfig;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/3 0003.
 */
public class RequestMakeCoffees {
    public Integer id;
    public String conversionCode;
    public String vmc;
    public CustomConfig customConfig;

}
