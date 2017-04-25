package com.mk.coffee.controller.rest;

import com.mk.coffee.common.*;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Product;
import com.mk.coffee.service.ProductService;
import com.mk.coffee.utils.VerifyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Administrator on 2017/2/24 0024.
 */
@Api(value = "商品接口")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(notes = "得到商品列表", value = "得到商品列表", httpMethod = "GET")
    @GetMapping("/products")
    public RestResult<ListResult<Product>> getProducts(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) throws AppException {
        return RestResultGenerator.genSuccessResult(productService.getListResultPages(page, size));
    }


    @ApiOperation(value = "通过排序得到商品列表", httpMethod = "GET", notes = "通过传入指定字段值升/降排序【create_date(新品),sales(销量),price(价格)】，(ASC)升序,(DESC)降序")
    @GetMapping("/products/{orderProperty}")
    public RestResult<ListResult<Product>> getProductListOrderPage(
            @PathVariable("orderProperty") String orderProperty,
            @RequestParam(name = "order", required = false, defaultValue = "DESC") String orderType,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) throws AppException {
        OrderProperty orderProperty1 = VerifyUtils.verificationOrderProperty(orderProperty);
        OrderType orderType1 = VerifyUtils.verificationOrderType(orderType);
        return RestResultGenerator.genSuccessResult(productService.getProductListOrderPage(orderProperty1, orderType1, page, size));

    }
}
