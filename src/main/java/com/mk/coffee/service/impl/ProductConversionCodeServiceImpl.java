package com.mk.coffee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.ProductConversionCodeMapper;
import com.mk.coffee.model.*;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.utils.CollectionUtils;
import com.mk.coffee.utils.JsonUtils;
import com.mk.coffee.utils.VerifyUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
@Service
public class ProductConversionCodeServiceImpl implements ProductConversionCodeService {
    @Autowired
    private ProductConversionCodeMapper productConversionCodeMapper;

    @Override
    @Transactional
    public boolean createProductConversionCodeService(OrderDetails orderDetails) {
        List<ShoppingCart> shoppingCarts = orderDetails.getShoppingCarts();
        ProductConversionCodeExample example = new ProductConversionCodeExample();
        example.createCriteria().andOrderDetailsIdEqualTo(orderDetails.getId());
        List<ProductConversionCode> productConversionCodes = productConversionCodeMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(productConversionCodes)) {
            throw AppException.getException(ErrorCode.Conversion_Code_Exist.getCode());
        }
        try {
            for (ShoppingCart shoppingCart : shoppingCarts) {
                //循环生成所有的兑换码
                for (int i = 0; i < shoppingCart.getNum(); i++) {
                    ProductConversionCode conversionCode = new ProductConversionCode();
                    conversionCode.setCrateDate(new Date());
                    conversionCode.setConversionCode(VerifyUtils.getEightRandom());//随机生成8位数字为兑换码
                    conversionCode.setOrderNum(VerifyUtils.getFiveRandom());
                    conversionCode.setMemberId(orderDetails.getMembersId());
                    conversionCode.setConversionState(0);//未领取
                    conversionCode.setOrderDetailsId(orderDetails.getId());
                    conversionCode.setProductId(shoppingCart.getProduct().getId());
                    conversionCode.setShoppingCartId(shoppingCart.getId());
                    productConversionCodeMapper.insert(conversionCode);//保存
                }
            }
            return true;
        } catch (Exception e) {
            throw AppException.getException(ErrorCode.Create_Conversion_Code_Fail.getCode());
        }
    }

    @Override
    public List<ProductConversionCode> getProductConversionCodeByOrderDetails(String orderDetailsId, int shoppingCartItemId) {
        ProductConversionCodeExample example = new ProductConversionCodeExample();
        example.createCriteria().andShoppingCartIdEqualTo(shoppingCartItemId).andOrderDetailsIdEqualTo(orderDetailsId);
        List<ProductConversionCode> productConversionCodes = productConversionCodeMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(productConversionCodes)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return productConversionCodes;
    }

    @Override
    public List<ProductConversionCode> getProductConversionCodeByOrderDetailsAndState(String orderDetailsId, int shoppingCartItemId, int isConversionState) {
        ProductConversionCodeExample example = new ProductConversionCodeExample();
        example.createCriteria()
                .andShoppingCartIdEqualTo(shoppingCartItemId)
                .andOrderDetailsIdEqualTo(orderDetailsId)
                .andConversionStateEqualTo(isConversionState);
        List<ProductConversionCode> productConversionCodes = productConversionCodeMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(productConversionCodes)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return productConversionCodes;
    }

    @Override
    public ProductConversionCode getProductConversionCodeById(int id) {
        return productConversionCodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProductConversionCode getProductConversionCodeByConversionCode(ProductConversionCode conversionCode) {
        return productConversionCodeMapper.getProductConversionCodeByConversionCode(conversionCode);
    }

    @Override
    public boolean updateProductConversionCodeById(int id, int conversionState) {
        return productConversionCodeMapper.updateConversionStateById(id, conversionState) > 0;
    }


    @Override
    public Members getMembersByProductConversionCode(String orderNum, int productId) {
        Members members = productConversionCodeMapper.getMembersByProductConversionCode(orderNum, productId).getMembers();
        if (members == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return members;
    }

    @Override
    public boolean updateProductConversionState(String orderNum, String productId) {
        return productConversionCodeMapper.updateProductConversionState(orderNum, Integer.valueOf(productId)) > 0;

    }

    @Override
    public boolean updateProductConversionStateById(int id) {

        return productConversionCodeMapper.updateProductConversionStateById(id) > 0;
    }

    @Override
    public ListResult<ProductConversionCode> getProductConversionCodeByMemberIdAndConversionState(long memberId, int conversionState, int page, int size) {

        //分页处理
        PageHelper.startPage(page, size);
        //查询结果
        List<ProductConversionCode> list = productConversionCodeMapper.getProductConversionCodeByMemberIdAndConversionState(memberId, conversionState);
        if (list == null || list.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }

        //获取分页信息
        PageInfo<ProductConversionCode> info = new PageInfo<>(list);
        return new ListResult<>(info.getList(), info.getTotal());
    }

}
