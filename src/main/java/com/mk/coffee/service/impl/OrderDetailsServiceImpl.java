package com.mk.coffee.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.EbeanMapper;
import com.mk.coffee.mapper.OrderDetailsMapper;
import com.mk.coffee.mapper.ShoppingCartMapper;
import com.mk.coffee.model.*;
import com.mk.coffee.service.EBeanServie;
import com.mk.coffee.service.OrderDetailsService;
import com.mk.coffee.service.ShoppingCartService;
import com.mk.coffee.service.WXInfoService;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.CommonUtils;
import com.mk.coffee.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 下单
 * Created by Administrator on 2017/3/8 0008.
 */
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private WXInfoService wxInfoService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private EBeanServie eBeanServie;

    @Override
    @Transactional
    public OrderDetails order(long memberId, int eNum) {
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.getShoppingCartByMemberId(memberId);
        Ebean ebean = eBeanServie.getEbeanByMemberId(memberId);
        if (ebean != null && ebean.getTotalNum() < eNum) {
            throw AppException.getException(ErrorCode.EBean_Insufficient);
        }
        if (shoppingCarts == null || shoppingCarts.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }

        OrderDetails orderDetails = commonUtils.createOrder(shoppingCarts, memberId, eNum);
        commonUtils.recodeOrderDetails(orderDetails, shoppingCarts);
        commonUtils.cleanShoppingCart(memberId);
        return orderDetails;
    }

    @Override
    @Transactional
    public OrderDetails order(long memberId, int[] ids, int eNum) {
        List<ShoppingCart> shoppingCarts = shoppingCartService.getShoppingCartByIds(memberId, ids);
        if (shoppingCarts == null || shoppingCarts.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        Ebean ebean = eBeanServie.getEbeanByMemberId(memberId);
        if (ebean != null && ebean.getTotalNum() < eNum) {
            throw AppException.getException(ErrorCode.EBean_Insufficient);
        }

        OrderDetails orderDetails = commonUtils.createOrder(shoppingCarts, memberId, eNum);
        commonUtils.recodeOrderDetails(orderDetails, shoppingCarts);
        commonUtils.cleanShoppingCartByIds(ids);
        return orderDetails;
    }

    @Override
    @Transactional
    public OrderDetails orderUseEncryptCode(long memberId, String cardId, String encryptCode, int eNum) {
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.getShoppingCartByMemberId(memberId);
        if (shoppingCarts == null || shoppingCarts.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }

        Ebean ebean = eBeanServie.getEbeanByMemberId(memberId);
        if (ebean != null && ebean.getTotalNum() < eNum) {
            throw AppException.getException(ErrorCode.EBean_Insufficient);
        }

        WXCard wxCard = wxInfoService.getWXCart(cardId, encryptCode);
        OrderDetails orderDetails = commonUtils.createOrder(shoppingCarts, memberId, 0);
        orderDetails.setWxCardCode(wxCard.getCode());
        //计算减去优惠券的金额
        commonUtils.computeDiscountMoney(wxCard, orderDetails);
        //减去e豆
        orderDetails.setBean(eNum);
        float eMoney = (float) (eNum * 0.1);
        orderDetails.setDiscountMoney(orderDetails.getDiscountMoney() - eMoney);
        //插入数据库
        commonUtils.recodeOrderDetails(orderDetails, shoppingCarts);
        return orderDetails;
    }

    @Transactional
    @Override
    public OrderDetails orderUseEncryptCode(long memberId, String cardId, String encryptCode, int[] ids, int eNum) {
        List<ShoppingCart> shoppingCarts = shoppingCartService.getShoppingCartByIds(memberId, ids);
        if (shoppingCarts == null || shoppingCarts.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        Ebean ebean = eBeanServie.getEbeanByMemberId(memberId);
        if (ebean != null && ebean.getTotalNum() < eNum) {
            throw AppException.getException(ErrorCode.EBean_Insufficient);
        }

        WXCard wxCard = wxInfoService.getWXCart(cardId, encryptCode);
        OrderDetails orderDetails = commonUtils.createOrder(shoppingCarts, memberId, 0);
        orderDetails.setWxCardCode(wxCard.getCode());
        //计算减去优惠券的金额
        commonUtils.computeDiscountMoney(wxCard, orderDetails);
        //减去e豆
        orderDetails.setBean(eNum);
        float eMoney = (float) (eNum * 0.1);
        orderDetails.setDiscountMoney(orderDetails.getDiscountMoney() - eMoney);
        //插入数据库
        commonUtils.recodeOrderDetails(orderDetails, shoppingCarts);
        return orderDetails;
    }


    @Override
    public OrderDetails getOrderDetail(String id) {
        OrderDetails orderDetails = orderDetailsMapper.selectByPrimaryKey(id);
        if (orderDetails == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }

        List<ShoppingCart> shoppingCarts = JsonUtils.fromJsonArray(orderDetails.getOrderDetails(), new TypeReference<List<ShoppingCart>>() {
        });
        orderDetails.setShoppingCarts(shoppingCarts);
        orderDetails.setOrderDetails(null);
        return orderDetails;
    }

    @Override
    public List<OrderDetails> getOrderDetails(long memberId, int payState) {

        List<OrderDetails> list = orderDetailsMapper.selectOrderDetailsByMemberIdAndPayState(memberId, payState);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        commonUtils.convertShoppingCart(list);
        return list;
    }

    @Override
    public boolean deleteOrder(String orderId) {
        return orderDetailsMapper.deleteByPrimaryKey(orderId) > 0;

    }

    @Override
    public boolean updatePayState(OrderDetails orderDetails) {
        return orderDetailsMapper.updateByPrimaryKey(orderDetails) > 0;
    }

    @Override
    public boolean updateOrderDetailsByWxCardCode(String wxCardCode) {
        return orderDetailsMapper.updateOrderDetailsByWxCardCode(wxCardCode) > 0;
    }

    @Override
    public List<OrderDetails> getList() {
        List<OrderDetails> list = orderDetailsMapper.selectOrderDetails();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        commonUtils.convertShoppingCart(list);
        return list;
    }

    @Override
    public OrderDetails getItem(String id) {
        return getOrderDetail(id);
    }

    @Override
    public boolean updateItem(OrderDetails orderDetails) {
        return orderDetailsMapper.updateByPrimaryKey(orderDetails) > 0;
    }

    @Override
    public boolean deleteItem(String id) {
        return orderDetailsMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(OrderDetails orderDetails) {
        return orderDetailsMapper.insert(orderDetails) > 0;
    }
}
