package com.mk.coffee.controller.rest;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.model.ShoppingCart;
import com.mk.coffee.model.ShoppingCartTotal;
import com.mk.coffee.requestbody.RequestCreateOrder;
import com.mk.coffee.requestbody.RequestIds;
import com.mk.coffee.service.ShoppingCartService;
import com.mk.coffee.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7 0007.
 */
@Api("购物车接口")
@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CommonUtils commonUtils;

    @ApiOperation(value = "设置购物车", notes = "设置购物车,memberId, productId（商品ID）, num（设置个数，不是累加）设置num为0,则删除订单纪录")
    @PostMapping("/setShoppingCart/{memberId}/{productId}")
    public RestResult<Boolean> setProductToShoppingCart(@PathVariable("memberId") long memberId,
                                                        @PathVariable("productId") int productId,
                                                        @RequestParam("num") int num) {
        return RestResultGenerator.genSuccessResult(shoppingCartService.setProductToShoppingCart(memberId, productId, num));
    }


    @ApiOperation(value = "加入购物车", notes = "加入购物车,memberId, productId（商品ID）, num（累加）")
    @PostMapping("/addShoppingCart/{memberId}/{productId}")
    public RestResult<Boolean> addProductToShoppingCart(@PathVariable("memberId") long memberId,
                                                        @PathVariable("productId") int productId,
                                                        @RequestParam("num") int num) {
        return RestResultGenerator.genSuccessResult(shoppingCartService.addProductToShoppingCart(memberId, productId, num));
    }


    @ApiOperation(value = "获取购物车商品列表", notes = "根据memberId(会员ID) page(页数) size(每页大小)")
    @GetMapping("/shoppingCart/{memberId}")
    public RestResult<List<ShoppingCart>> getShoppingCart(@PathVariable("memberId") long memberId) {
        return RestResultGenerator.genSuccessResult(shoppingCartService.getShoppingCart(memberId));
    }

    @ApiOperation(value = "清除购物车商品", notes = "根据memberId(会员ID)清除订单，返回成功失败")
    @PostMapping("/shoppingCart/{memberId}")
    public RestResult<Boolean> cleanShoppingCart(@PathVariable("memberId") long memberId) {
        return RestResultGenerator.genSuccessResult(shoppingCartService.cleanShoppingCart(memberId));
    }


    @ApiOperation(value = "得到购物车总数量", notes = "根据memberId得到所有的购物商品", httpMethod = "GET")
    @GetMapping("/shoppingCartProductCount")
    public RestResult<Integer> getProductCount(@RequestParam("memberId") long memberId) {
        return RestResultGenerator.genSuccessResult(shoppingCartService.getProductCount(memberId));
    }

    @ApiOperation(value = "清除购物车商品", notes = "根据购物车item ids清除选中的购物商品", httpMethod = "POST")
    @PostMapping("/shoppingCarts/clearByIds")
    public RestResult<Boolean> clearShoppingCartIds(@RequestBody RequestIds requestIds) {
        return RestResultGenerator.genSuccessResult(commonUtils.cleanShoppingCartByIds(requestIds.ids));
    }


    @ApiOperation(value = "计算购物车总金额", notes = "根据成员id,shoppingCartsItemIds([1,2,3,4...id],不传默认为全部)," +
            "cardId，encryptCode（可选，使用微信卡券则必须同时传入）", httpMethod = "POST")
    @PostMapping("/computeShoppingCartTotalByIdsOrWXCard")
    public RestResult<ShoppingCartTotal> computeShoppingCartTotalByIdsOrWXCard(@RequestBody RequestCreateOrder createOrder) {
        return RestResultGenerator.genSuccessResult(shoppingCartService.getShoppingCartTotalByIdsOrWXCard(createOrder));
    }
}
