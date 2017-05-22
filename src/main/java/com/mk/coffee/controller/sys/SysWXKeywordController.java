package com.mk.coffee.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.WxKeyword;
import com.mk.coffee.service.WXKeywordService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
@RestController
@Api("后台管理微信关键字接口")
@RequestMapping(value = "/sys/wxKeyword")
public class SysWXKeywordController {
    @Autowired
    private WXKeywordService wxKeywordService;


    @GetMapping("/list")
    @ApiOperation("得到列表")
    public RestResult<ListResult<WxKeyword>> getList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                     @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<WxKeyword> list = wxKeywordService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<WxKeyword> pageInfo = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages()));
    }


    @GetMapping("/item")
    @ApiOperation("得到关键字")
    public RestResult<WxKeyword> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(wxKeywordService.getItem(id));
    }


    @PutMapping("/update")
    @ApiOperation("更新关键字")
    public RestResult<Boolean> updateItem(@RequestBody WxKeyword wxKeyword) {
        return RestResultGenerator.genSuccessResult(wxKeywordService.updateItem(wxKeyword));
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除关键字")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(wxKeywordService.deleteItem(id));
    }


    @PostMapping("/add")
    @ApiOperation("添加关键字")
    public RestResult<Boolean> addItem(@RequestBody WxKeyword wxKeyword) {
        return RestResultGenerator.genSuccessResult(wxKeywordService.addItem(wxKeyword));
    }


    @GetMapping("/getWxKeywordByKeyword")
    @ApiOperation("根据关键字内容搜索关键字")
    public RestResult<List<WxKeyword>> getWxKeywordByKeyword(@RequestParam("keyword") String keyword) {
        return RestResultGenerator.genSuccessResult(wxKeywordService.getWxKeywordByKeyword(keyword));
    }
}
