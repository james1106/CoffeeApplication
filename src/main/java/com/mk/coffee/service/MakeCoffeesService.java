package com.mk.coffee.service;

import com.mk.coffee.model.ProductConversionCode;

import java.io.IOException;

/**
 * 制作咖啡服务
 * Created by Administrator on 2017/3/13 0013.
 */
public interface MakeCoffeesService {

    //制作咖啡
    boolean makeCoffees(ProductConversionCode productConversionCode, String vmc) throws IOException;

}
