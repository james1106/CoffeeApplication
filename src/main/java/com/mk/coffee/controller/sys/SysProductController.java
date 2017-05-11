package com.mk.coffee.controller.sys;

import com.mk.coffee.common.*;
import com.mk.coffee.model.Product;
import com.mk.coffee.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@RestController
@Api("后台商品接口")
@RequestMapping(value = "/sys/product")
public class SysProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation("得到商品Item")
    @GetMapping("/item")
    public RestResult<Product> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(productService.getItem(id));
    }


    @ApiOperation("更新商品")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody Product product) {
        return RestResultGenerator.genSuccessResult(productService.updateItem(product));
    }


    @ApiOperation("删除商品")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id")int id) {
        return RestResultGenerator.genSuccessResult(productService.deleteItem(id));
    }


    @ApiOperation("添加商品Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody Product product) {
        return RestResultGenerator.genSuccessResult(productService.addItem(product));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到商品列表")
    public RestResult<ListResult<Product>> getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                           @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return RestResultGenerator.genSuccessResult(productService.getListResultPages(page, size));
    }

}
