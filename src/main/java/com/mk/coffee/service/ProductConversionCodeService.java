package com.mk.coffee.service;

import com.mk.coffee.common.ListResult;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.model.Product;
import com.mk.coffee.model.ProductConversionCode;

import java.util.List;

/**
 * 商品兑换码
 * Created by Administrator on 2017/3/13 0013.
 */
public interface ProductConversionCodeService {

    //根据成员Id和商品Id生成一个兑换码
    boolean createProductConversionCodeByMemberId(long memberId, int productId);

    //根据订单生成兑换码
    boolean createProductConversionCodeService(OrderDetails orderDetails);

    //根据订单id和购物车条目id获取兑换码
    List<ProductConversionCode> getProductConversionCodeByOrderDetails(String orderDetailsId, int shoppingCartItemId);

    //根据订单id,购物车条目id和兑换状态获取兑换码
    List<ProductConversionCode> getProductConversionCodeByOrderDetailsAndState(String orderDetailsId, int shoppingCartItemId, int isConversionState);

    //根据id获取兑换码
    ProductConversionCode getProductConversionCodeById(int id);

    //根据兑换码获取未兑换码的纪录
    ProductConversionCode getProductConversionCodeByConversionCode(ProductConversionCode conversionCode);


    //更新兑换码状态信息
    boolean updateProductConversionCodeById(int id, int conversionState);

    //通过订单号、商品ID查询会员信息
    Members getMembersByProductConversionCode(String orderNum, int productId);

    //更新待领取的兑换码为已领取
    boolean updateProductConversionState(String orderNum, String productId);

    //更新待领取的兑换码为已领取通过ID
    boolean updateProductConversionStateById(int id);


    //通过会员ID和领取状态得到兑换码
    List<ProductConversionCode> getProductConversionCodeByMemberIdAndConversionState(long memberId, int conversionState);

    //通过会员ID得到兑换码
    List<ProductConversionCode> getAllProductConversionCodeByMemberId(long memberId);


}
