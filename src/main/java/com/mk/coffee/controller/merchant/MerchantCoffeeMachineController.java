package com.mk.coffee.controller.merchant;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.mapper.CoffeeMachineMapper;
import com.mk.coffee.model.CoffeeMachine;
import com.mk.coffee.model.CoffeeMachineExample;
import com.mk.coffee.requestbody.RequestCoffeeMachine;
import com.mk.coffee.service.CoffeeMachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
@Api("商户咖啡机设备接口")
@RestController
@RequestMapping("/merchant")
public class MerchantCoffeeMachineController {

    @Autowired
    private CoffeeMachineService coffeeMachineService;

    @ApiOperation("设置是否外卖、运营时间")
    @PutMapping("/updateCoffeeMachine")
    public RestResult<Boolean> updateCoffeeMachine(@RequestBody RequestCoffeeMachine requestCoffeeMachine) {
        return RestResultGenerator.genSuccessResult(coffeeMachineService.updateMerchantCoffeeMachine(requestCoffeeMachine.code,
                requestCoffeeMachine.isTakeoutStatus,requestCoffeeMachine.startTime,requestCoffeeMachine.endTime));
    }
}
