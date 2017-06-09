package com.mk.coffee.controller.merchant;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.service.OrderDetailsService;
import com.mk.coffee.utils.CalendarUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.ORB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
@Api("商户管理订单接口")
@RestController
@RequestMapping("/merchant")
public class SysUserOrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @ApiOperation(value = "得到未完成配送的商户订单列表",
            notes = "得到未完成配送的商户订单列表,state状态（0:未处理，1：已完成，2：已接收，3：正在配送，4：待确认）")
    @GetMapping("/getMerchantOrders")
    public RestResult<List<OrderDetails>> getMerchantOrder(@RequestParam("userId") int userId,
                                                           @RequestParam("coffeeMachineId") int coffeeMachineId) {
        return RestResultGenerator.genSuccessResult(orderDetailsService.getMerchantOrder(userId, coffeeMachineId));
    }


    @ApiOperation(value = "得到商户订单列表",
            notes = "根据completed(不传默认全都状态)，timestamp:传入要查询当天的时间戳，" +
                    "如：要查询今天订单，只需传今天内的某一时间点时间戳即可，其他时间同理,不传默认全都")
    @GetMapping("/getMerchantOrderByStateAndTimestamp")
    public RestResult<List<OrderDetails>>
    getMerchantOrderByStateAndTimestamp(@RequestParam("userId") int userId,
                                        @RequestParam("coffeeMachineId") int coffeeMachineId,
                                        @RequestParam(value = "completed", required = false)
                                                Boolean state,
                                        @RequestParam(value = "timestamp", required = false) Long timestamp) {
        return RestResultGenerator.genSuccessResult(orderDetailsService.
                getMerchantOrderByStateAndTimestamp(userId, coffeeMachineId, state, timestamp));
    }


    @ApiOperation("商户接收订单")
    @PostMapping("/receiveMerchantOrder")
    public RestResult<Boolean> receiveMerchantOrder(@RequestParam("orderId") String orderId) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(orderId);
        orderDetails.setState(2);
        return RestResultGenerator.genSuccessResult(orderDetailsService.updateItem(orderDetails));
    }


}
