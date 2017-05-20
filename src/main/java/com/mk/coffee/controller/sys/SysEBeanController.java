package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Activity;
import com.mk.coffee.model.Ebean;
import com.mk.coffee.service.EBeanServie;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.VerifyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@Api("台台管理E豆接口")
@RestController
@RequestMapping(value = "/sys/ebean")
public class SysEBeanController {
    @Autowired
    private EBeanServie ebeanService;

    @ApiOperation("得到E豆Item")
    @GetMapping("/item")
    public RestResult<Ebean> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(ebeanService.getItem(id));
    }

    @ApiOperation("根据memberId得到e豆")
    @GetMapping("/getEBeanByMemberId")
    public RestResult<Ebean> getEBeanByMemberId(@RequestParam("id") long memberId) {
        return RestResultGenerator.genSuccessResult(ebeanService.getEbeanByMemberId(memberId));
    }


    @ApiOperation("更新E豆")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody Ebean ebean) {
        return RestResultGenerator.genSuccessResult(ebeanService.updateItem(ebean));
    }


    @ApiOperation("删除E豆")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(ebeanService.deleteItem(id));
    }


    @ApiOperation("添加E豆Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody Ebean ebean) {
        return RestResultGenerator.genSuccessResult(ebeanService.addItem(ebean));
    }


    @GetMapping("/list")
    @ApiOperation("分页得到E豆列表")
    public RestResult<ListResult<Ebean>> getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                         @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<Ebean> list = ebeanService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<Ebean> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }

    @GetMapping("/search")
    @ApiOperation("根据会员ID，手机号码搜索分页得到E豆列表")
    public RestResult<ListResult<Ebean>>
    serachProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                       @RequestParam(name = "keyword", required = false) String keyword) {
        List<Ebean> list = null;
        PageHelper.startPage(page, size);
        if (!EmptyUtils.isEmpty(keyword) && !VerifyUtils.isDigit(keyword)) {
            throw AppException.getException(ErrorCode.Keyword_Illegal_Number);
        }
        list = ebeanService.searchEbean(keyword);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<Ebean> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }


}
