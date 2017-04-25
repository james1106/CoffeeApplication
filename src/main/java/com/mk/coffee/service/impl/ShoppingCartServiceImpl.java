package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.MembersMapper;
import com.mk.coffee.mapper.ProductMapper;
import com.mk.coffee.mapper.ShoppingCartMapper;
import com.mk.coffee.model.*;
import com.mk.coffee.requestbody.RequestCreateOrder;
import com.mk.coffee.service.ShoppingCartService;
import com.mk.coffee.utils.CollectionUtils;
import com.mk.coffee.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7 0007.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private WXInfoServiceImpl wxInfoService;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    @Transactional
    public boolean setProductToShoppingCart(long memberId, int productId, int num) throws AppException {
        //查询会员信息
        Members member = membersMapper.selectByPrimaryKey(memberId);
        if (member == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        //查询商品
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }

        if (num < 0) {
            throw AppException.getException(ErrorCode.ILLEGAL_PARAMS.getCode());
        }

        //查询是否存在订单
        ShoppingCart shoppingCart = shoppingCartMapper.selectOrderByMemberIdAndProductId(memberId, productId);
        if (num == 0 && shoppingCart == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        if (shoppingCart == null && num > 0) {
            //不存在就添加
            shoppingCart = new ShoppingCart(productId, memberId, num, new Date());
            return shoppingCartMapper.insert(shoppingCart) > 0;
        } else {
            if (num == 0) {
                //传入为0就删除
                return shoppingCartMapper.deleteByPrimaryKey(shoppingCart.getId()) > 0;
            } else {
                //存在就更新
                shoppingCart.setId(shoppingCart.getId());
                shoppingCart.setNum(num);
                shoppingCart.setMemberId(memberId);
                shoppingCart.setProductId(productId);
                shoppingCart.setCreateDate(new Date());
                return shoppingCartMapper.updateByPrimaryKey(shoppingCart) > 0;
            }
        }
    }

    @Override
    public boolean addProductToShoppingCart(long memberId, int productId, int num) throws AppException {
        //查询会员信息
        Members member = membersMapper.selectByPrimaryKey(memberId);
        if (member == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        //查询商品
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }

        //查询是否存在订单
        ShoppingCart shoppingCart = shoppingCartMapper.selectOrderByMemberIdAndProductId(memberId, productId);
        if (shoppingCart == null && num > 0) {
            //不存在就添加
            shoppingCart = new ShoppingCart(productId, memberId, num, new Date());
            return shoppingCartMapper.insert(shoppingCart) > 0;
        }

        if (shoppingCart == null && num <= 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }

        //只剩下一个,删除
        if (shoppingCart.getNum() == 1 && num < 0) {
            return shoppingCartMapper.deleteByPrimaryKey(shoppingCart.getId()) > 0;
        }

        //存在就更新
        shoppingCart.setId(shoppingCart.getId());
        num += shoppingCart.getNum();
        shoppingCart.setNum(num);
        shoppingCart.setMemberId(memberId);
        shoppingCart.setProductId(productId);
        shoppingCart.setCreateDate(new Date());
        return shoppingCartMapper.updateByPrimaryKey(shoppingCart) > 0;
    }

    @Override
    public List<ShoppingCart> getShoppingCart(long memberId) throws AppException {
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.getShoppingCartByMemberId(memberId);
        if (CollectionUtils.isEmpty(shoppingCarts)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return shoppingCarts;
    }

    @Transactional
    @Override
    public List<ShoppingCart> getShoppingCartByIds(long memberId, int[] ids) {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        for (int id : ids) {
            ShoppingCart shoppingCart = shoppingCartMapper.selectByPrimaryKey(id);
            if (shoppingCart != null && shoppingCart.getMemberId() == memberId) {
                Product product = productMapper.selectByPrimaryKey(shoppingCart.getProductId());
                shoppingCart.setProduct(product);
                shoppingCarts.add(shoppingCart);
            }
        }
        if (CollectionUtils.isEmpty(shoppingCarts)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return shoppingCarts;
    }

    @Override
    public ShoppingCartTotal getShoppingCartTotalByIdsOrWXCard(RequestCreateOrder createOrder) {
        ShoppingCartTotal shoppingCartTotal = new ShoppingCartTotal();
        //默认不传shoppingCartsItemIds
        if (createOrder.shoppingCartsItemIds == null || createOrder.shoppingCartsItemIds.length == 0) {
            List<ShoppingCart> shoppingCarts = shoppingCartMapper.getShoppingCartByMemberId(createOrder.memberId);
            if (shoppingCarts == null || shoppingCarts.size() == 0) {
                throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
            }
            float total = 0; //计算总金额
            int count = 0;//总数量
            for (int i = 0; i < shoppingCarts.size(); i++) {
                ShoppingCart shoppingCart = shoppingCarts.get(i);
                total += (shoppingCart.getNum() * shoppingCart.getProduct().getPrice());
                count += shoppingCart.getNum();
            }
            shoppingCartTotal.setTotalMoney(total);
            shoppingCartTotal.setCount(count);
            if (createOrder.encryptCode != null) {//有微信卡券
                commonUtils.computeDiscountMoney(createOrder, shoppingCartTotal);
            }
        } else {
            List<ShoppingCart> shoppingCarts = shoppingCartService.getShoppingCartByIds(createOrder.memberId, createOrder.shoppingCartsItemIds);
            if (shoppingCarts == null || shoppingCarts.size() == 0) {
                throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
            }
            float total = 0; //计算总金额
            int count = 0;//总数量
            for (int i = 0; i < shoppingCarts.size(); i++) {
                ShoppingCart shoppingCart = shoppingCarts.get(i);
                total += (shoppingCart.getNum() * shoppingCart.getProduct().getPrice());
                count += shoppingCart.getNum();
            }
            shoppingCartTotal.setTotalMoney(total);
            shoppingCartTotal.setCount(count);
            if (createOrder.encryptCode != null) {//有微信卡券
                commonUtils.computeDiscountMoney(createOrder, shoppingCartTotal);
            }
        }
        return shoppingCartTotal;
    }

    @Override
    public boolean cleanShoppingCart(long memberId) throws AppException {
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return shoppingCartMapper.deleteByExample(example) > 0;
    }

    @Override
    public int getProductCount(long memberId) {
        int count = 0;
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(shoppingCartList)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        for (ShoppingCart shoppingCart : shoppingCartList) {
            count += shoppingCart.getNum();
        }
        return count;
    }
}
