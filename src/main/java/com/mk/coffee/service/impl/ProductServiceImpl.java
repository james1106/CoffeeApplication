package com.mk.coffee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.OrderProperty;
import com.mk.coffee.common.OrderType;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.LocalAuthMapper;
import com.mk.coffee.mapper.ProductMapper;
import com.mk.coffee.model.LocalAuth;
import com.mk.coffee.model.Product;
import com.mk.coffee.model.ProductExample;
import com.mk.coffee.service.ProductService;
import com.mk.coffee.utils.VerifyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24 0024.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private LocalAuthMapper localAuthMapper;


    public ListResult<Product> getListResultPages(int page, int size) throws AppException {

        //分页处理
        PageHelper.startPage(page, size);

        //查询结果
        List<Product> list = productMapper.selectByExample(null);
        if (list == null || list.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        //获取分页信息
        PageInfo<Product> info = new PageInfo<>(list);

        return new ListResult<>(list, info.getTotal(), info.getPages());
    }

    public ListResult<Product> getProductListOrderPage(OrderProperty orderProperty, OrderType orderType, int page, int size) throws AppException {
        /*//验证Token
        LocalAuth localAuth = localAuthMapper.selectLocalAuthByToken(token);
        VerifyUtils.verificationToken(token, localAuth);*/

        //分页处理
        PageHelper.startPage(page, size);

        //查询结果
        ProductExample example = new ProductExample();
        //排序
        StringBuffer sb = new StringBuffer();
        sb.append(orderProperty.getProperty()).append(" ").append(orderType.getValue());
        example.setOrderByClause(sb.toString());
        List<Product> list = productMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        //获取分页信息
        PageInfo<Product> info = new PageInfo<>(list);
        return new ListResult<>(list, info.getTotal(), info.getPages());
    }

    @Override
    public List<Product> selectByExample(ProductExample example) {
        return productMapper.selectByExample(example);
    }

    @Override
    public List<Product> getList() {
        return productMapper.selectByExample(null);
    }

    @Override
    public Product getItem(int id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(Product product) {
        return productMapper.updateByPrimaryKeySelective(product) > 0;
    }

    @Override
    public boolean updateItem(int id, Product product) {
        return productMapper.updateProductById(product,id) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return productMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(Product product) {
        return productMapper.insert(product) > 0;
    }
}
