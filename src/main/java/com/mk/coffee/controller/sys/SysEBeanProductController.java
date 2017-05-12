package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Ebean;
import com.mk.coffee.model.EbeanProduct;
import com.mk.coffee.service.EBeanProductService;
import com.mk.coffee.service.EBeanServie;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@Api("后台E豆商品接口")
@RestController
@RequestMapping(value = "/sys/ebeanProduct")
public class SysEBeanProductController {
    @Autowired
    private EBeanProductService ebeanProductService;

    @ApiOperation("得到E豆商品Item")
    @GetMapping("/item")
    public RestResult<EbeanProduct> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(ebeanProductService.getItem(id));
    }


    @ApiOperation("更新E豆商品")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody EbeanProduct ebeanProduct) {
        return RestResultGenerator.genSuccessResult(ebeanProductService.updateItem(ebeanProduct));
    }


    @ApiOperation("删除E豆商品")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(ebeanProductService.deleteItem(id));
    }


    @ApiOperation("添加E豆商品Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody EbeanProduct ebeanProduct) {
        return RestResultGenerator.genSuccessResult(ebeanProductService.addItem(ebeanProduct));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到E豆商品列表")
    public RestResult<ListResult<EbeanProduct>> getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                         @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<EbeanProduct> list = ebeanProductService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<EbeanProduct> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }
}
