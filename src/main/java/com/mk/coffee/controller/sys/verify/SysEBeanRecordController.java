package com.mk.coffee.controller.sys.verify;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.EbeanRecord;
import com.mk.coffee.service.EBeanRecordService;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.VerifyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("后台E豆充值纪录接口")
@RestController
@RequestMapping(value = "/sys/ebeanRecord")
public class SysEBeanRecordController {
    @Autowired
    private EBeanRecordService ebeanRecordService;

    @ApiOperation("得到E豆充值纪录Item")
    @GetMapping("/item")
    public RestResult<EbeanRecord> getItem(@RequestParam("id") long id) {
        SecurityUtils.getSubject().checkPermission("sys:eBeanRecord:view");
        return RestResultGenerator.genSuccessResult(ebeanRecordService.getItem(id));
    }


    @ApiOperation("更新E豆充值纪录")
    @PostMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody EbeanRecord ebeanRecord) {
        SecurityUtils.getSubject().checkPermission("sys:eBeanRecord:update");
        return RestResultGenerator.genSuccessResult(ebeanRecordService.updateItem(ebeanRecord));
    }


    @ApiOperation("删除E豆充值纪录")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") long id) {
        SecurityUtils.getSubject().checkPermission("sys:eBeanRecord:delete");
        return RestResultGenerator.genSuccessResult(ebeanRecordService.deleteItem(id));
    }


    @ApiOperation("添加E豆充值纪录Item")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody EbeanRecord ebeanRecord) {
        SecurityUtils.getSubject().checkPermission("sys:eBeanRecord:create");
        return RestResultGenerator.genSuccessResult(ebeanRecordService.addItem(ebeanRecord));
    }

    @GetMapping("/list")
    @ApiOperation("分页得到E豆充值纪录列表")
    public RestResult<ListResult<EbeanRecord>>
    getProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                    @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        SecurityUtils.getSubject().checkPermission("sys:eBeanRecord:view");
        PageHelper.startPage(page, size);
        List<EbeanRecord> list = ebeanRecordService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<EbeanRecord> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }

    @GetMapping("/search")
    @ApiOperation("根据会员ID，手机号码关键字和支付状态搜索分页得到E豆充值纪录列表")
    public RestResult<ListResult<EbeanRecord>>
    serachProductPages(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                       @RequestParam(name = "keyword", required = false) String keyword,
                       @RequestParam(value = "payState", required = false) Integer payState) {
        SecurityUtils.getSubject().checkPermission("sys:eBeanRecord:view");
        List<EbeanRecord> list = null;
        PageHelper.startPage(page, size);
        if (!EmptyUtils.isEmpty(keyword) && !VerifyUtils.isDigit(keyword)) {
            throw AppException.getException(ErrorCode.Keyword_Illegal_Number);
        }

        list = ebeanRecordService.searchEbeanRecord(keyword, payState);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<EbeanRecord> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()));
    }


}
