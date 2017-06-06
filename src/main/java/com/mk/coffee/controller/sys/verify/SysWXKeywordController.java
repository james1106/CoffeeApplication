package com.mk.coffee.controller.sys.verify;

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
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public RestResult<List<WxKeyword>> getList() {
        SecurityUtils.getSubject().checkPermission("sys:wxKeyword:view");
        List<WxKeyword> list = wxKeywordService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return RestResultGenerator.genSuccessResult(list);
    }


    @GetMapping("/item")
    @ApiOperation("得到关键字")
    public RestResult<WxKeyword> getItem(@RequestParam("id") int id) {
        SecurityUtils.getSubject().checkPermission("sys:wxKeyword:view");
        return RestResultGenerator.genSuccessResult(wxKeywordService.getItem(id));
    }


    @PutMapping("/update")
    @ApiOperation("更新关键字")
    public RestResult<Boolean> updateItem(@RequestBody WxKeyword wxKeyword) {
        SecurityUtils.getSubject().checkPermission("sys:wxKeyword:update");
        return RestResultGenerator.genSuccessResult(wxKeywordService.updateItem(wxKeyword));
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除关键字")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        SecurityUtils.getSubject().checkPermission("sys:wxKeyword:delete");
        return RestResultGenerator.genSuccessResult(wxKeywordService.deleteItem(id));
    }


    @PostMapping("/add")
    @ApiOperation("添加关键字")
    public RestResult<Boolean> addItem(@RequestBody WxKeyword wxKeyword) {
        SecurityUtils.getSubject().checkPermission("sys:wxKeyword:create");
        wxKeyword.setCreateDate(new Date());
        return RestResultGenerator.genSuccessResult(wxKeywordService.addItem(wxKeyword));
    }


    @GetMapping("/getWxKeywordByKeyword")
    @ApiOperation("根据关键字内容搜索关键字")
    public RestResult<List<WxKeyword>> getWxKeywordByKeyword(@RequestParam("keyword") String keyword) {
        SecurityUtils.getSubject().checkPermission("sys:wxKeyword:view");
        return RestResultGenerator.genSuccessResult(wxKeywordService.getWxKeywordByKeyword(keyword));
    }
}
