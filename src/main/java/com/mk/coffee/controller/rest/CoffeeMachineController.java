package com.mk.coffee.controller.rest;

import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.model.CoffeeMachine;
import com.mk.coffee.service.CoffeeMachineService;
import com.taobao.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/3/2 0002.
 */
@Api("咖啡机接口")
@RestController
public class CoffeeMachineController {

    @Autowired
    private CoffeeMachineService coffeeMachineService;

    @ApiOperation(value = "得到咖啡机列表", notes = "根据经纬度和半径(千米)得到附近的咖啡机列表")
    @GetMapping("/coffeemachine")
    public RestResult<ListResult<CoffeeMachine>> getCoffeeMachinesByLonLat(@RequestParam("longitude") double longitude,
                                                                           @RequestParam("latitude") double latitude,
                                                                           @RequestParam("radius") float radius) throws ApiException {
        return RestResultGenerator.genSuccessResult(coffeeMachineService.getCoffeeMachinesByLonLat(longitude, latitude, radius));
    }
}
