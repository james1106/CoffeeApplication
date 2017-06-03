package com.mk.coffee.controller.sys.verify;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.CustomConfig;
import com.mk.coffee.service.CustomConfigService;
import com.mk.coffee.utils.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
@Api("后台个性调制接口")
@RestController
@RequestMapping("/sys/customConfigs")
public class SysCustomConfigController {
    @Autowired
    private CustomConfigService customConfigService;

    @ApiOperation("得到所有的个性调制列表")
    @GetMapping("/all")
    public RestResult<List<CustomConfig>> getList() {
        List<CustomConfig> list = customConfigService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return RestResultGenerator.genSuccessResult(list);
    }


    @ApiOperation("分页得到个性调制列表")
    @GetMapping("/list")
    public RestResult<ListResult<CustomConfig>> getList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<CustomConfig> list = customConfigService.getList();
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        PageInfo<CustomConfig> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()
        ));
    }


    @ApiOperation("搜索分页得到个性调制列表")
    @GetMapping("/search")
    public RestResult<ListResult<CustomConfig>> search(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                       @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                       @RequestParam(name = "keyword", required = false) String keyword) {
        PageHelper.startPage(page, size);
        List<CustomConfig> list = customConfigService.searchCustomConfig(keyword);
        PageInfo<CustomConfig> info = new PageInfo<>(list);
        return RestResultGenerator.genSuccessResult(new ListResult<>(info.getList(), info.getTotal(), info.getPages()
        ));
    }


    @ApiOperation("得到个性调制item")
    @GetMapping("/item")
    public RestResult<CustomConfig> getItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(customConfigService.getItem(id));
    }

    @ApiOperation("更新个性调制")
    @PutMapping("/update")
    public RestResult<Boolean> updateItem(@RequestBody CustomConfig customConfig) {
        return RestResultGenerator.genSuccessResult(customConfigService.updateItem(customConfig));
    }


    @ApiOperation("删除个性调制")
    @DeleteMapping("/delete")
    public RestResult<Boolean> deleteItem(@RequestParam("id") int id) {
        return RestResultGenerator.genSuccessResult(customConfigService.deleteItem(id));
    }

    @ApiOperation("添加个性调制")
    @PostMapping("/add")
    public RestResult<Boolean> addItem(@RequestBody CustomConfig customConfig) {
        return RestResultGenerator.genSuccessResult(customConfigService.addItem(customConfig));
    }
}
