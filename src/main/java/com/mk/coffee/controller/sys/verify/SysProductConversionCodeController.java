package com.mk.coffee.controller.sys.verify;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.ProductConversionCode;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@RestController
@Api("后台兑换码接口")
@RequestMapping(value = "/sys/productConversionCode")
public class SysProductConversionCodeController {
    @Autowired
    private ProductConversionCodeService productConversionCodeService;

    @ApiOperation("得到兑换码Item")
    @GetMapping("/item")
    public RestResult<ProductConversionCode> getItem(@RequestParam("id") int id) {
        SecurityUtils.getSubject().checkPermission("sys:conversionCode:view");
        return RestResultGenerator.genSuccessResult(productConversionCodeService.getItem(id));
    }


    @ApiOperation("更新兑换码")
    @PutMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody ProductConversionCode productConversionCode) {
        SecurityUtils.getSubject().checkPermission("sys:conversionCode:update");
        return RestResultGenerator.genSuccessResult(productConversionCodeService.updateItem(productConversionCode));
    }

    @ApiOperation("删除兑换码")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        SecurityUtils.getSubject().checkPermission("sys:conversionCode:delete");
        return RestResultGenerator.genSuccessResult(productConversionCodeService.deleteItem(id));
    }


    @ApiOperation("添加兑换码Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody ProductConversionCode productConversionCode) {
        SecurityUtils.getSubject().checkPermission("sys:conversionCode:create");
        productConversionCode.setCreateDate(new Date());
        return RestResultGenerator.genSuccessResult(productConversionCodeService.addItem(productConversionCode));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到兑换码列表")
    public RestResult<ListResult<ProductConversionCode>>
    getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                    @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        SecurityUtils.getSubject().checkPermission("sys:conversionCode:view");
        PageHelper.startPage(page, size);
        List<ProductConversionCode> list = productConversionCodeService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<ProductConversionCode> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }

    @GetMapping("/search")
    @ApiOperation("搜索分页得到兑换码列表")
    public RestResult<ListResult<ProductConversionCode>>
    searchProductConversionCode(@RequestParam(name = "keyword", required = false) String keyword,
                                @RequestParam(name = "conversionState", required = false) Integer conversionState,
                                @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        SecurityUtils.getSubject().checkPermission("sys:conversionCode:view");
        PageHelper.startPage(page, size);
        List<ProductConversionCode> list = productConversionCodeService.searchProductConversionCode(keyword, conversionState);
        PageInfo<ProductConversionCode> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }
}
