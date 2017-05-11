package com.mk.coffee.controller.rest;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.requestbody.RequestCreateOrder;
import com.mk.coffee.service.OrderDetailsService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/3/8
 */
@Api("订单接口")
@RestController
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/order")
    @ApiOperation(value = "生成订单", notes = "根据成员id,shoppingCartsItemIds([1,2,3,4...id],不传默认为全部)," +
            "cardId，encryptCode（可选，使用微信卡券则必须同时传入） 微信卡券生成订单")
    public RestResult<OrderDetails> order(@RequestBody RequestCreateOrder createOrder) {
        //默认不传shoppingCartsItemIds
        if (createOrder.shoppingCartsItemIds == null) {
            if (EmptyUtils.isEmpty(createOrder.encryptCode) || EmptyUtils.isEmpty(createOrder.cardId)) {//没有微信卡券
                return RestResultGenerator.genSuccessResult(orderDetailsService.order(createOrder.memberId, createOrder.eNum));
            } else {//有微信卡券
                return RestResultGenerator.genSuccessResult(orderDetailsService.orderUseEncryptCode(createOrder.memberId, createOrder.cardId, createOrder.encryptCode, createOrder.eNum));
            }
        } else {
            if (EmptyUtils.isEmpty(createOrder.encryptCode) || EmptyUtils.isEmpty(createOrder.cardId)) {
                return RestResultGenerator.genSuccessResult(orderDetailsService.order(createOrder.memberId, createOrder.shoppingCartsItemIds, createOrder.eNum));
            } else {
                return RestResultGenerator.genSuccessResult(orderDetailsService.orderUseEncryptCode(createOrder.memberId, createOrder.cardId,
                        createOrder.encryptCode, createOrder.shoppingCartsItemIds, createOrder.eNum));
            }
        }
    }


    @GetMapping("/order")
    @ApiOperation(value = "得到订单", notes = "根据订单id(orderId)得到订单")
    public RestResult<OrderDetails> getOrder(@RequestParam("orderId") String orderId) {
        return RestResultGenerator.genSuccessResult(orderDetailsService.getOrderDetail(orderId));
    }

    @GetMapping("/orders")
    @ApiOperation(value = "得到订单列表", notes = "根据memberId payState(0：未支付，1：已支付，2：支付失败)得到个人订单列表")
    public RestResult<List<OrderDetails>> getOrder(@RequestParam("memberId") long memberId, @RequestParam("payState") int payState) {
        return RestResultGenerator.genSuccessResult(orderDetailsService.getOrderDetails(memberId, payState));
    }

    @PostMapping("/deleteOrder")
    @ApiOperation(value = "删除订单", notes = "根据订单id(orderId)删除订单,返回成功或失败")
    public RestResult<Boolean> deleteOrder(@RequestParam("orderId") String orderId) {
        return RestResultGenerator.genSuccessResult(orderDetailsService.deleteOrder(orderId));
    }


}
