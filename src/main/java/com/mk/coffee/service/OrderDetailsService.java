package com.mk.coffee.service;

import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.requestbody.RequestCreateTakeOutOrder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 下订单
 * Created by Administrator on 2017/3/8 0008.
 */
public interface OrderDetailsService {

    //下单
    OrderDetails order(long memberId, int eNum) throws AppException;

    OrderDetails order(long memberId, int[] ids, int eNum);

    //使用微信卡券下单通过加密code
    OrderDetails orderUseEncryptCode(long memberId, String cardId, String encryptCode, int eNum);

    //使用微信卡券下单通过加密code和购物车商品ids
    OrderDetails orderUseEncryptCode(long memberId, String cardId, String encryptCode, int[] ids, int eNum);

    //创建外卖订单
    OrderDetails createTakeOutOrder(RequestCreateTakeOutOrder createTakeOutOrder);

    //得到订单
    OrderDetails getOrderDetail(String id) throws AppException;

    List<OrderDetails> getOrderDetails(long memberId, int payState);


    //得到商户接收到的订单
    List<OrderDetails> getMerchantOrder(int userId, int coffeeMachineId);

    //删除订单
    boolean deleteOrder(String orderId);

    //更新支付状态
    boolean updatePayState(OrderDetails orderDetails);

    //重置使用该张微信卡券的订单的金额
    boolean updateOrderDetailsByWxCardCode(String wxCardCode);

    List<OrderDetails> getList();


    OrderDetails getItem(String id);


    boolean updateItem(OrderDetails orderDetails);


    boolean deleteItem(String id);


    boolean addItem(OrderDetails orderDetails);

    //搜索
    List<OrderDetails> searchOrderDetails(String keyword);

}
