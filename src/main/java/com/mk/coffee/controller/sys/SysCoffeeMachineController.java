package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Activity;
import com.mk.coffee.model.CoffeeMachine;
import com.mk.coffee.model.Product;
import com.mk.coffee.service.CoffeeMachineService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@Api("咖啡机后台接口")
@RestController
@RequestMapping("/sys/coffeeMachine")
public class SysCoffeeMachineController {
    @Autowired
    private CoffeeMachineService coffeeMachineService;

    @ApiOperation("得到咖啡机Item")
    @GetMapping("/item")
    public RestResult<CoffeeMachine> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(coffeeMachineService.getItem(id));
    }


    @ApiOperation("更新咖啡机")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody CoffeeMachine coffeeMachine) {
        return RestResultGenerator.genSuccessResult(coffeeMachineService.updateItem(coffeeMachine));
    }


    @ApiOperation("删除咖啡机")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(coffeeMachineService.deleteItem(id));
    }


    @ApiOperation("添加咖啡机Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody CoffeeMachine coffeeMachine) {
        return RestResultGenerator.genSuccessResult(coffeeMachineService.addItem(coffeeMachine));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到咖啡机列表")
    public RestResult<ListResult<CoffeeMachine>> getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                                 @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<CoffeeMachine> list = coffeeMachineService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<CoffeeMachine> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }
}
