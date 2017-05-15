package com.mk.coffee.service;

import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.OrderProperty;
import com.mk.coffee.common.OrderType;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Product;
import com.mk.coffee.model.ProductExample;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 商品服务
 */
public interface ProductService extends IBaseService<Product> {

    //得到商品列表
    ListResult<Product> getListResultPages(int page, int size) throws AppException;

    /**
     * 通过排序得到商品列表
     *
     * @param orderProperty 要排序的字段
     * @param orderType     升序/降序
     * @param page          页数
     * @param size          页数大小
     * @return
     */
    ListResult<Product> getProductListOrderPage(OrderProperty orderProperty, OrderType orderType, int page, int size);


    List<Product> selectByExample(ProductExample example);

    boolean updateItem(int id, Product product);


}
