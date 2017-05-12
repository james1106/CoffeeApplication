package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Ebean;
import com.mk.coffee.model.EbeanRecord;
import com.mk.coffee.service.EBeanRecordService;
import com.mk.coffee.service.EBeanServie;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("E豆充值纪录后台接口")
@RestController
@RequestMapping(value = "/sys/ebeanRecord")
public class SysEBeanRecordController {
    @Autowired
    private EBeanRecordService ebeanRecordService;

    @ApiOperation("得到E豆充值纪录Item")
    @GetMapping("/item")
    public RestResult<EbeanRecord> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(ebeanRecordService.getItem(id));
    }


    @ApiOperation("更新E豆充值纪录")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody EbeanRecord ebeanRecord) {
        return RestResultGenerator.genSuccessResult(ebeanRecordService.updateItem(ebeanRecord));
    }


    @ApiOperation("删除E豆充值纪录")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(ebeanRecordService.deleteItem(id));
    }


    @ApiOperation("添加E豆充值纪录Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody EbeanRecord ebeanRecord) {
        return RestResultGenerator.genSuccessResult(ebeanRecordService.addItem(ebeanRecord));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到E豆充值纪录列表")
    public RestResult<ListResult<EbeanRecord>> getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                               @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<EbeanRecord> list = ebeanRecordService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<EbeanRecord> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }
}
