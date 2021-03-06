package com.mk.coffee.controller.rest;

import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.model.ProductConversionCode;
import com.mk.coffee.requestbody.RequestConversionCode;
import com.mk.coffee.service.OrderDetailsService;
import com.mk.coffee.service.ProductConversionCodeService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
@Api("兑换码接口")
@RestController
public class ProductConversionCodeController {
    @Autowired
    private ProductConversionCodeService productConversionCodeService;


    //根据订单id和购物车条目id获取兑换码
    @GetMapping("/ProductConversionCode")
    @ApiOperation(value = "得到兑换码列表", notes = "根据订单id和购物车条目id获取兑换码", httpMethod = "GET")
    public RestResult<List<ProductConversionCode>> getProductConversionCodeByOrderDetails(@RequestParam("orderDetailsId") String orderDetailsId,
                                                                                          @RequestParam("shoppingCartItemId") int shoppingCartItemId) {
        return RestResultGenerator.genSuccessResult(productConversionCodeService.getProductConversionCodeByOrderDetails(orderDetailsId, shoppingCartItemId));
    }

    //根据订单id和购物车条目id和兑换码状态获取兑换码
    @GetMapping("/ProductConversionCodesByConversionState")
    @ApiOperation(value = "根据订单id和购物车条目id和兑换码状态得到兑换码列表",
            notes = "根据订单id和购物车条目id和兑换码状态(0：未领取，1：已领取，2：待领取，3：领取失败)获取兑换码", httpMethod = "GET")
    public RestResult<List<ProductConversionCode>> getProductConversionCodeByOrderDetailsAndState(@RequestParam("orderDetailsId") String orderDetailsId,
                                                                                                  @RequestParam("shoppingCartItemId") int shoppingCartItemId,
                                                                                                  @RequestParam("isConversionState") int isConversionState) {
        return RestResultGenerator.genSuccessResult(productConversionCodeService.getProductConversionCodeByOrderDetailsAndState(orderDetailsId, shoppingCartItemId, isConversionState));
    }

    @PostMapping("/getMembersByProductConversionCode")
    @ApiOperation(value = "得到兑换码的会员信息", notes = "根据订单orderNum,productId得到会员信息", httpMethod = "POST")
    public RestResult<Members> getMembersByProductConversionCode(@RequestBody RequestConversionCode conversionCode) {
        return RestResultGenerator.genSuccessResult(productConversionCodeService.getMembersByProductConversionCode(conversionCode.orderNum, conversionCode.productId));
    }

    @GetMapping("/getProductConversionCodeByMemberIdAndConversionState")
    @ApiOperation(value = "根据会员ID和领取的状态得到兑换码信息", notes = "conversionState:（0：未领取，1：已领取，2：待领取，3：领取失败) 页数page默认为1，size默认为10", httpMethod = "GET")
    public RestResult<List<ProductConversionCode>> getProductConversionCodeByMemberIdAndConversionState(@RequestParam("memberId") long memberId,
                                                                                                        @RequestParam("conversionState") int conversionState) {
        return RestResultGenerator.genSuccessResult(productConversionCodeService.getProductConversionCodeByMemberIdAndConversionState(memberId, conversionState));
    }


    @GetMapping("/getAllProductConversionCodeByMemberId")
    @ApiOperation(value = "根据会员ID得到所有的兑换码信息", notes = "conversionState:（0：未领取，1：已领取，2：待领取，3：领取失败) 页数page默认为1，size默认为10", httpMethod = "GET")
    public RestResult<List<ProductConversionCode>> getAllProductConversionCodeByMemberId(@RequestParam("memberId") long memberId) {
        return RestResultGenerator.genSuccessResult(productConversionCodeService.getAllProductConversionCodeByMemberId(memberId));
    }


    @PostMapping("/giveProductConversionCode")
    @ApiOperation(value = "赠送咖啡兑换码", notes = "实现请TA喝功能", httpMethod = "POST")
    public RestResult<Boolean> giveProductConversionCode(@RequestParam("memberId") long memberId, @RequestParam("productConversionId") int productConversionId) {
        return RestResultGenerator.genSuccessResult(productConversionCodeService.giveProductConversionCode(memberId, productConversionId));
    }

    @GetMapping("/getProductConversionCodeById")
    @ApiOperation(value = "根据id得到兑换码的信息", notes = "根据id兑换码信息", httpMethod = "GET")
    public RestResult<ProductConversionCode> getProductConversionCodeById(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(productConversionCodeService.getProductConversionCodeById(id));
    }

}
