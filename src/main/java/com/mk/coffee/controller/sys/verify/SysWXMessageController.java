package com.mk.coffee.controller.sys.verify;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.WxMessage;
import com.mk.coffee.service.WXMessageService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
@RestController
@Api("后台管理微信消息接口")
@RequestMapping(value = "/sys/wxMessage")
public class SysWXMessageController {
    @Autowired
    private WXMessageService wxMessageService;

    @GetMapping("/search")
    @ApiOperation("搜索微信消息")
    public RestResult<ListResult<WxMessage>>
    searchList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
               @RequestParam(name = "size", required = false, defaultValue = "10") int size,
               @RequestParam(name = "keyword", required = false) String keyword) {
        SecurityUtils.getSubject().checkPermission("sys:wxMessage:view");
        PageHelper.startPage(page, size);
        List<WxMessage> list = wxMessageService.searchList(keyword);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<WxMessage> pageInfo = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages()));
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信消息纪录")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        SecurityUtils.getSubject().checkPermission("sys:wxMessage:delete");
        return RestResultGenerator.genSuccessResult(wxMessageService.deleteItem(id));
    }

}
