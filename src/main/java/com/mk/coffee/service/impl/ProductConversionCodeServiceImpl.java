package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.ProductConversionCodeMapper;
import com.mk.coffee.model.*;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.VerifyUtils;
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
    public ProductConversionCode createProductConversionCodeByMemberId(long memberId, int productId) {
        ProductConversionCode conversionCode = new ProductConversionCode();
        conversionCode.setCreateDate(new Date());
        conversionCode.setUpdateDate(new Date());
        conversionCode.setConversionCode(VerifyUtils.getEightRandom());//随机生成8位数字为兑换码
        conversionCode.setOrderNum(VerifyUtils.getFiveRandom());
        conversionCode.setMemberId(memberId);
        conversionCode.setConversionState(0);//未领取
        conversionCode.setProductId(productId);
        productConversionCodeMapper.insert(conversionCode);//保存
        return getProductConversionCodeByConversionCode(conversionCode);

    }

    @Override
    @Transactional
    public boolean createProductConversionCodeService(OrderDetails orderDetails) {
        List<ShoppingCart> shoppingCarts = orderDetails.getShoppingCarts();
        ProductConversionCodeExample example = new ProductConversionCodeExample();
        example.createCriteria().andOrderDetailsIdEqualTo(orderDetails.getId());
        List<ProductConversionCode> productConversionCodes = productConversionCodeMapper.selectByExample(example);
        if (!EmptyUtils.isEmpty(productConversionCodes)) {
            throw AppException.getException(ErrorCode.Conversion_Code_Exist.getCode());
        }
        try {
            for (ShoppingCart shoppingCart : shoppingCarts) {
                //循环生成所有的兑换码
                for (int i = 0; i < shoppingCart.getNum(); i++) {
                    ProductConversionCode conversionCode = new ProductConversionCode();
                    conversionCode.setCreateDate(new Date());
                    conversionCode.setUpdateDate(new Date());
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
        example.setOrderByClause("update_date DESC");
        example.createCriteria().andShoppingCartIdEqualTo(shoppingCartItemId).andOrderDetailsIdEqualTo(orderDetailsId);
        List<ProductConversionCode> productConversionCodes = productConversionCodeMapper.selectByExample(example);
        if (EmptyUtils.isEmpty(productConversionCodes)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return productConversionCodes;
    }

    @Override
    public List<ProductConversionCode> getProductConversionCodeByOrderDetailsAndState(String orderDetailsId, int shoppingCartItemId, int isConversionState) {
        ProductConversionCodeExample example = new ProductConversionCodeExample();
        example.setOrderByClause("update_date DESC");
        example.createCriteria()
                .andShoppingCartIdEqualTo(shoppingCartItemId)
                .andOrderDetailsIdEqualTo(orderDetailsId)
                .andConversionStateEqualTo(isConversionState);
        List<ProductConversionCode> productConversionCodes = productConversionCodeMapper.selectByExample(example);
        if (EmptyUtils.isEmpty(productConversionCodes)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return productConversionCodes;
    }

    @Override
    public ProductConversionCode getProductConversionCodeById(int id) {
        return productConversionCodeMapper.getProductConversionCodeById(id);
    }

    @Override
    public ProductConversionCode getProductConversionCodeByConversionCode(ProductConversionCode conversionCode) {
        return productConversionCodeMapper.getProductConversionCodeByConversionCode(conversionCode);
    }

    @Override
    public boolean updateProductConversionCodeById(int id, String vmc, int conversionState) {
        return productConversionCodeMapper.updateConversionStateById(id, vmc, conversionState) > 0;
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
    public List<ProductConversionCode> getProductConversionCodeByMemberIdAndConversionState(long memberId, int conversionState) {
        //查询结果
        List<ProductConversionCode> list = productConversionCodeMapper.getProductConversionCodeByMemberIdAndConversionState(memberId, conversionState);
        if (list == null || list.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return list;
    }

    @Override
    public List<ProductConversionCode> getAllProductConversionCodeByMemberId(long memberId) {
        //查询结果
        List<ProductConversionCode> list = productConversionCodeMapper.getAllProductConversionCodeByMemberId(memberId);
        if (list == null || list.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return list;
    }

    @Override
    public boolean giveProductConversionCode(long memberId, int productConversionId) {
        ProductConversionCode productConversionCode = productConversionCodeMapper.selectByPrimaryKey(productConversionId);
        if (memberId == productConversionCode.getMemberId()) {
            throw AppException.getException(ErrorCode.Member_Identical);
        }
        productConversionCode.setMemberId(memberId);
        return productConversionCodeMapper.updateByPrimaryKey(productConversionCode) > 0;
    }

    @Override
    public List<ProductConversionCode> searchProductConversionCode(String keyword, Integer conversionState) {
        List<ProductConversionCode> list = productConversionCodeMapper.searchProductConversionCode(keyword, conversionState);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return list;
    }

    /*@Override
    public List<ProductConversionCode> searchProductConversionCode(String keyword) {
        ProductConversionCodeExample example = null;
        if (!EmptyUtils.isEmpty(keyword)) {
            example = new ProductConversionCodeExample();
            if (keyword.equals("未领取")) {
                example.or().andConversionStateEqualTo(0);
            } else if (keyword.equals("领取中")) {
                example.or().andConversionStateEqualTo(2);
            } else if (keyword.equals("已领取")) {
                example.or().andConversionStateEqualTo(1);
            } else if (keyword.equals("领取失败")) {
                example.or().andConversionStateEqualTo(3);
            } else {
                example.or().andMemberIdEqualTo(Long.parseLong(keyword));
                example.or().andProductIdEqualTo((int) Long.parseLong(keyword));
            }
        }
        List<ProductConversionCode> list = productConversionCodeMapper.selectByExample(example);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }

        return list;
    }*/

    @Override
    public List<ProductConversionCode> getList() {
        //查询结果
        List<ProductConversionCode> list = productConversionCodeMapper.getAllProductConversionCode();
        if (list == null || list.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return list;
    }

    @Override
    public ProductConversionCode getItem(int id) {
        return productConversionCodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(ProductConversionCode productConversionCode) {
        return productConversionCodeMapper.updateByPrimaryKeySelective(productConversionCode) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return productConversionCodeMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(ProductConversionCode productConversionCode) {
        return productConversionCodeMapper.insertSelective(productConversionCode) > 0;
    }
}
