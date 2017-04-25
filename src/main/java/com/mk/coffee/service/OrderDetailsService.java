package com.mk.coffee.service;

import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.OrderDetails;

import java.util.List;

/**
 * 下订单
 * Created by Administrator on 2017/3/8 0008.
 */
public interface OrderDetailsService {

    //下单
    OrderDetails order(long memberId) throws AppException;

    OrderDetails order(long memberId,int[] ids);

    //使用微信卡券下单通过加密code
    OrderDetails orderUseEncryptCode(long memberId, String cardId, String encryptCode);

    //使用微信卡券下单通过加密code和购物车商品ids
    OrderDetails orderUseEncryptCode(long memberId, String cardId, String encryptCode,int[] ids);

    //得到订单
    OrderDetails getOrderDetail(String id) throws AppException;

    List<OrderDetails> getOrderDetails(long memberId, int payState);

    //删除订单
    boolean deleteOrder(String orderId);

    //更新支付状态
    boolean updatePayState(OrderDetails orderDetails);

    //重置使用该张微信卡券的订单的金额
    boolean updateOrderDetailsByWxCardCode(String wxCardCode);

}
