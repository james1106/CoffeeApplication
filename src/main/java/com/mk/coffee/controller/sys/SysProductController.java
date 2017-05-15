package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.*;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.MembersExample;
import com.mk.coffee.model.Product;
import com.mk.coffee.model.ProductExample;
import com.mk.coffee.requestbody.RequestUpdateProduct;
import com.mk.coffee.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    @PutMapping("/updateById")
    public RestResult<Boolean> updateItemById(@RequestBody RequestUpdateProduct updateProduct) {
        if (updateProduct.productId!=updateProduct.product.getId()&&productService.getItem(updateProduct.productId) != null) {
            throw AppException.getException(ErrorCode.Product_Already_Exist);
        }

        return RestResultGenerator.genSuccessResult(productService.updateItem(updateProduct.productId, updateProduct.product));
    }

    @ApiOperation("更新商品")
    @PutMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody Product product) {
        return RestResultGenerator.genSuccessResult(productService.updateItem(product));
    }


    @ApiOperation("删除商品")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(productService.deleteItem(id));
    }


    @ApiOperation("添加商品Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody Product product) {
        if (productService.getItem(product.getId()) != null) {
            throw AppException.getException(ErrorCode.Product_Already_Exist);
        }
        product.setCreateDate(new Date());
        return RestResultGenerator.genSuccessResult(productService.addItem(product));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到商品列表")
    public RestResult<ListResult<Product>> getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                           @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return RestResultGenerator.genSuccessResult(productService.getListResultPages(page, size));
    }

    @GetMapping("/searchProductByKeyword")
    @ApiOperation(value = "搜索商品")
    public RestResult<ListResult<Product>> searchProductByKeyword(@RequestParam("keyword") String keyword,
                                                                  @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                                  @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        ProductExample example = null;
        if (keyword != null && !keyword.equals("")) {
            example = new ProductExample();
            example.or().andNameLike("%" + keyword + "%");
        }
        List<Product> list = productService.selectByExample(example);
        if (list == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<Product> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }


}
