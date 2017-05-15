package com.mk.coffee.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.ActivityMapper;
import com.mk.coffee.mapper.OrderDetailsMapper;
import com.mk.coffee.mapper.ShoppingCartMapper;
import com.mk.coffee.model.*;
import com.mk.coffee.requestbody.RequestCreateOrder;
import com.mk.coffee.service.WXInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 计算
 * Created by Administrator on 2017/4/25 0025.
 */
@Component
public class CommonUtils {


    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private WXInfoService wxInfoService;

    //计算减去优惠券的金额
    public void computeDiscountMoney(WXCard wxCard, OrderDetails orderDetails) {
        if (wxCard.getReduceCost() != 0) {
            if (orderDetails.getMoney() > wxCard.getReduceCost()) {
                orderDetails.setDiscountMoney(orderDetails.getMoney() - wxCard.getReduceCost());
            } else {
                orderDetails.setDiscountMoney(0f);
            }
        } else if (wxCard.getDiscount() != 0) {
            orderDetails.setDiscountMoney(orderDetails.getMoney() * wxCard.getDiscount());
        } else {
            orderDetails.setDiscountMoney(orderDetails.getMoney());
        }
    }

    //计算减去优惠券的金额
    public void computeDiscountMoney(RequestCreateOrder createOrder, ShoppingCartTotal shoppingCartTotal) {
        if (createOrder.encryptCode != null) {
            WXCard wxCard = wxInfoService.getWXCart(createOrder.cardId, createOrder.encryptCode);
            if (wxCard.getReduceCost() != 0) {
                if (shoppingCartTotal.getTotalMoney() > wxCard.getReduceCost()) {
                    shoppingCartTotal.setDeductionMoney(wxCard.getReduceCost());
                    shoppingCartTotal.setDiscountMoney(shoppingCartTotal.getTotalMoney() - wxCard.getReduceCost());
                } else {
                    shoppingCartTotal.setDeductionMoney(wxCard.getReduceCost());
                    shoppingCartTotal.setDiscountMoney(0f);
                }
            } else if (wxCard.getDiscount() != 0) {
                shoppingCartTotal.setDiscountMoney(shoppingCartTotal.getTotalMoney() * wxCard.getDiscount());
                shoppingCartTotal.setDeductionMoney(shoppingCartTotal.getTotalMoney() - shoppingCartTotal.getTotalMoney() * wxCard.getDiscount());
            } else {
                shoppingCartTotal.setDeductionMoney(0);
                shoppingCartTotal.setDiscountMoney(shoppingCartTotal.getTotalMoney());
            }

            //e豆抵的金额
            float eMoney = (float) (createOrder.eNum * 0.1);
            //减免的金额
            shoppingCartTotal.setDeductionMoney(shoppingCartTotal.getDeductionMoney() + eMoney);
            //减免后的金额
            shoppingCartTotal.setDiscountMoney(shoppingCartTotal.getTotalMoney() - shoppingCartTotal.getDeductionMoney());
        }
    }


    //根据购物车商品列表创建订单
    public OrderDetails createOrder(List<ShoppingCart> shoppingCarts, long memberId, int eNum) {
        //计算总金额
        float total = 0;
        //总数量
        int count = 0;
        for (int i = 0; i < shoppingCarts.size(); i++) {
            ShoppingCart shoppingCart = shoppingCarts.get(i);

            total += (shoppingCart.getNum() * shoppingCart.getProduct().getPrice());
            count += shoppingCart.getNum();
        }
        OrderDetails orderDetails = new OrderDetails();
        //订单为四位随机数+时间戳
        orderDetails.setId(VerifyUtils.getFourRandom() + System.currentTimeMillis());
        orderDetails.setCreateDate(new Date());
        orderDetails.setMembersId(memberId);
        orderDetails.setOrderDetails(JsonUtils.toJson(shoppingCarts));
        orderDetails.setBean(eNum);
        float eMoney = (float) (eNum * 0.1);
        orderDetails.setMoney(total - eMoney);
        orderDetails.setDiscountMoney(total - eMoney);
        orderDetails.setTotal(count);
        orderDetails.setPayState(0);
        return orderDetails;
    }


    //插入数据库
    public void recodeOrderDetails(OrderDetails orderDetails, List<ShoppingCart> shoppingCarts) {
        if (orderDetailsMapper.insert(orderDetails) > 0) {
            orderDetails.setShoppingCarts(shoppingCarts);
            //不序列化
            orderDetails.setOrderDetails(null);
        } else {
            throw AppException.getException(ErrorCode.Gen_Order_Fail.getCode());
        }
    }

    public void cleanShoppingCart(long memberId) {
        //清除购物车的商品
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        shoppingCartMapper.deleteByExample(example);
    }

    public void cleanShoppingCartByIds(int[] ids) {
        //清除购物车的商品
        for (int id : ids) {
            shoppingCartMapper.deleteByPrimaryKey(id);
        }
    }


    public float keepTwoDecimal(float f) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.valueOf(df.format(f));
    }


    public EbeanRecord createEbeanRecord(long memberId, EbeanProduct ebeanProduct) {
        //e豆充值纪录
        EbeanRecord ebeanRecord = new EbeanRecord();
        ebeanRecord.setId(System.currentTimeMillis());
        ebeanRecord.seteNum(ebeanProduct.geteNum());
        ebeanRecord.setGivingNum(ebeanProduct.getGivingNum());
        ebeanRecord.setTotalNum(ebeanProduct.geteNum() + ebeanProduct.getGivingNum());
        ebeanRecord.setMoney(ebeanProduct.getMoney());
        ebeanRecord.setPayType(0);
        ebeanRecord.setPayState(0);//未支付
        ebeanRecord.setCreateDate(new Date());
        ebeanRecord.setMemberId(memberId);
        return ebeanRecord;
    }


    public EbeanRecord createEbeanRecordByMoney(long memberId, float money) {
        //e豆充值纪录
        EbeanRecord ebeanRecord = new EbeanRecord();
        ebeanRecord.setId(System.currentTimeMillis());
        ebeanRecord.seteNum((int) (money * 10));
        ebeanRecord.setGivingNum(0);
        ebeanRecord.setTotalNum(ebeanRecord.geteNum());
        ebeanRecord.setMoney(money);
        ebeanRecord.setPayType(0);
        ebeanRecord.setPayState(0);//未支付
        ebeanRecord.setCreateDate(new Date());
        ebeanRecord.setMemberId(memberId);
        return ebeanRecord;
    }

    /**
     * 创建e豆
     */
    public Ebean createEbean(EbeanRecord ebeanRecord) {
        Ebean ebean = new Ebean();
        ebean.setMemberId(ebeanRecord.getMemberId());
        ebean.seteNum(ebeanRecord.geteNum());
        ebean.setGivingNum(ebeanRecord.getGivingNum());
        ebean.setCreateDate(new Date());
        ebean.setUpdateDate(new Date());
        ebean.setTotalNum(ebeanRecord.geteNum() + ebeanRecord.getGivingNum());
        return ebean;
    }

    /**
     * 更新e豆
     */
    public Ebean updateEbean(EbeanRecord ebeanRecord, Ebean ebean) {
        ebean.seteNum(ebeanRecord.geteNum() + ebean.geteNum());
        ebean.setGivingNum(ebeanRecord.getGivingNum() + ebean.getGivingNum());
        ebean.setTotalNum(ebean.geteNum() + ebean.getGivingNum());
        ebean.setUpdateDate(new Date());
        return ebean;
    }

    /**
     * 更新e豆
     */
    public Ebean useEbean(Ebean ebean, int num) {
        if (ebean.getTotalNum() >= num) {
            //赠送的豆多于花费的豆
            if (ebean.getGivingNum() > num) {
                ebean.setGivingNum(ebean.getGivingNum() - num);
                ebean.setTotalNum(ebean.getGivingNum() + ebean.geteNum());
            } else {
                ebean.setGivingNum(0);
                ebean.seteNum(ebean.getTotalNum() - num);
                ebean.setTotalNum(ebean.geteNum());
            }
        } else {
            throw AppException.getException(ErrorCode.EBean_Insufficient);
        }

        return ebean;
    }

    public void convertShoppingCart(List<OrderDetails> list) {
        for (int i = 0; i < list.size(); i++) {
            OrderDetails orderDetails = list.get(i);
            List<ShoppingCart> shoppingCarts = JsonUtils.fromJsonArray(orderDetails.getOrderDetails(), new TypeReference<List<ShoppingCart>>() {
            });
            orderDetails.setShoppingCarts(shoppingCarts);
            orderDetails.setOrderDetails(null);
        }

    }

}
