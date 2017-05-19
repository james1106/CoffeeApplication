package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.model.Product;
import com.mk.coffee.service.OrderDetailsService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@RestController
@Api("后台订单接口")
@RequestMapping(value = "/sys/orders")
public class SysOrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @ApiOperation("得到订单列表")
    @GetMapping("/list")
    public RestResult<ListResult<OrderDetails>> getList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<OrderDetails> list = orderDetailsService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<OrderDetails> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }


    @ApiOperation("得到订单Item")
    @GetMapping("/item")
    public RestResult<OrderDetails> getItem(@RequestParam("id") String id) {
        return RestResultGenerator.genSuccessResult(orderDetailsService.getItem(id));
    }


    @ApiOperation("更新订单")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody OrderDetails orderDetails) {
        return RestResultGenerator.genSuccessResult(orderDetailsService.updateItem(orderDetails));
    }


    @ApiOperation("删除订单")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") String id) {
        return RestResultGenerator.genSuccessResult(orderDetailsService.deleteItem(id));
    }


    @ApiOperation("添加订单")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody OrderDetails orderDetails) {
        return RestResultGenerator.genSuccessResult(orderDetailsService.addItem(orderDetails));
    }


    @ApiOperation("搜索订单")
    @GetMapping("/search")
    public RestResult<ListResult<OrderDetails>>
    searchOrderDetails(@RequestParam(name = "keyword", required = false) String keyword,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        PageHelper.startPage(page, size);
        List<OrderDetails> list = orderDetailsService.searchOrderDetails(keyword);
        PageInfo<OrderDetails> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }
}
