package com.mk.coffee.service;

import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.model.CoffeeMachine;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/2 0002.
 */
@Service
public interface CoffeeMachineService extends IBaseService<CoffeeMachine> {
    //通过定位，半径得到咖啡机列表
    ListResult<CoffeeMachine> getCoffeeMachinesByLonLat(double longitude, double latitude, float radius) throws ApiException;

    //通过id
    CoffeeMachine getCoffeeMachine(int id);
}
