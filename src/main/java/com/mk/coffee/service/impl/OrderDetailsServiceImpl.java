package com.mk.coffee.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.*;
import com.mk.coffee.model.*;
import com.mk.coffee.requestbody.RequestCreateTakeOutOrder;
import com.mk.coffee.service.EBeanServie;
import com.mk.coffee.service.OrderDetailsService;
import com.mk.coffee.service.ShoppingCartService;
import com.mk.coffee.service.WXInfoService;
import com.mk.coffee.utils.CalendarUtil;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.CommonUtils;
import com.mk.coffee.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    private CoffeeMachineMapper coffeeMachineMapper;

    @Autowired
    private AddressMapper addressMapper;

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
        //commonUtils.cleanShoppingCart(memberId);
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
        //commonUtils.cleanShoppingCartByIds(ids);
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
    @Transactional
    public OrderDetails createTakeOutOrder(RequestCreateTakeOutOrder takeOutOrder) {
        OrderDetails orderDetails;
        CoffeeMachine coffeeMachine = coffeeMachineMapper.selectByPrimaryKey(takeOutOrder.coffeeMachineId);
        Address address = addressMapper.selectByPrimaryKey(takeOutOrder.addressId);
        if (coffeeMachine == null || address == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }

        if (EmptyUtils.isEmpty(takeOutOrder.encryptCode) || EmptyUtils.isEmpty(takeOutOrder.cardId)) {
            orderDetails = order(takeOutOrder.memberId, takeOutOrder.shoppingCartsItemIds, takeOutOrder.eNum);
        } else {
            orderDetails = orderUseEncryptCode(takeOutOrder.memberId, takeOutOrder.cardId,
                    takeOutOrder.encryptCode, takeOutOrder.shoppingCartsItemIds, takeOutOrder.eNum);
        }

        if (coffeeMachine.getUserId() == null) {
            throw AppException.getException(ErrorCode.Coffees_Machine_NOT_SYS_USER);
        }
        orderDetails.setCreateDate(new Date());
        orderDetails.setCoffeeMachineId(takeOutOrder.coffeeMachineId);
        orderDetails.setUserId(coffeeMachine.getUserId());
        orderDetails.setState(0);
        //联系人、地址
        orderDetails.setContacts(address.getContacts());
        orderDetails.setPhone(address.getPhone());
        orderDetails.setLongitude(address.getLongitude());
        orderDetails.setLatitude(address.getLatitude());
        orderDetails.setAddress(address.getAddress());
        //更新
        orderDetailsMapper.updateByPrimaryKeySelective(orderDetails);
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
    public List<OrderDetails> getMerchantOrder(int userId, int coffeeMachineId) {
        List<OrderDetails> list = orderDetailsMapper.getMerchantOrder(userId, coffeeMachineId);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
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
        return orderDetailsMapper.updateByPrimaryKeySelective(orderDetails) > 0;
    }

    @Override
    public boolean deleteItem(String id) {
        return orderDetailsMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(OrderDetails orderDetails) {
        return orderDetailsMapper.insert(orderDetails) > 0;
    }

    @Override
    public List<OrderDetails> searchOrderDetails(String keyword) {
        OrderDetailsExample example = null;
        if (keyword != null && !keyword.equals("")) {
            example = new OrderDetailsExample();
            if (keyword.equals("未支付")) {
                example.or().andPayStateEqualTo(0);
            } else if (keyword.equals("已支付")) {
                example.or().andPayStateEqualTo(1);
            } else if (keyword.equals("支付失败")) {
                example.or().andPayStateEqualTo(2);
            } else if (keyword.equals("e豆")) {
                example.or().andBeanIsNotNull().andBeanNotEqualTo(0);
            } else if (keyword.equals("微信卡券")) {
                example.or().andWxCardCodeIsNotNull();
            } else {
                example.or().andIdEqualTo(keyword);
                example.or().andMembersIdEqualTo(Long.parseLong(keyword));
            }
        }

        List<OrderDetails> list = orderDetailsMapper.selectByExampleWithBLOBs(example);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        commonUtils.convertShoppingCart(list);
        return list;
    }

    @Override
    public List<OrderDetails> getMerchantOrderByStateAndTimestamp(int userId, int coffeeMachineId,
                                                                 Boolean completed, Long timeStamp) {
        Date startTimestamp = null;
        Date endTimestamp = null;
        if (timeStamp != null) {
            startTimestamp = CalendarUtil.zeroFromHour(timeStamp).getTime();
            endTimestamp = CalendarUtil.endFromHour(timeStamp).getTime();
        }
        List<OrderDetails> list = orderDetailsMapper
                .getMerchantOrderByStateAndTimestamp(userId, coffeeMachineId, completed, startTimestamp, endTimestamp);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return list;
    }
}
