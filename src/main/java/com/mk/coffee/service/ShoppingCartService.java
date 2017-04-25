package com.mk.coffee.service;

import com.mk.coffee.common.ListResult;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.ShoppingCart;
import com.mk.coffee.model.ShoppingCartTotal;
import com.mk.coffee.requestbody.RequestCreateOrder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7 0007.
 */
public interface ShoppingCartService {

    //设置商品到购物车,设置num为0,则删除订单纪录
    boolean setProductToShoppingCart(long memberId, int productId, int num) throws AppException;

    //加入购物车
    boolean addProductToShoppingCart(long memberId, int productId, int num) throws AppException;

    //得到订单列表
    List<ShoppingCart> getShoppingCart(long memberId) throws AppException;

    //得到购物车订单列表通过Ids
    List<ShoppingCart> getShoppingCartByIds(long memberId, int[] ids);

    //计算总金额通过购物车商品ID数组，微信卡券信息
    ShoppingCartTotal getShoppingCartTotalByIdsOrWXCard(RequestCreateOrder createOrder);

    //清除订单
    boolean cleanShoppingCart(long memberId) throws AppException;

    //得到购物车总数量
    int getProductCount(long memberId);

}
