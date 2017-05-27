package com.mk.coffee.controller.rest;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.model.CustomConfig;
import com.mk.coffee.model.MemberCustomConfig;
import com.mk.coffee.requestbody.RequestCustomConfig;
import com.mk.coffee.service.CustomConfigService;
import com.mk.coffee.service.MemberCustomConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
@Api("自定义调制接口")
@RestController
public class CustomConfigController {
    @Autowired
    private CustomConfigService customConfigService;

    @Autowired
    private MemberCustomConfigService memberCustomConfigService;


    @GetMapping("/getCustomConfigListByRecommend")
    @ApiOperation(value = "得到系统推荐调制", httpMethod = "GET")
    public RestResult<List<CustomConfig>> getCustomConfigListByRecommend() {
        return RestResultGenerator.genSuccessResult(customConfigService.getCustomConfigListByRecommend());
    }


    @PostMapping("/addCustomConfig")
    @ApiOperation(value = "添加调制配方", httpMethod = "POST")
    public RestResult<Boolean> addCustomConfig(@RequestBody CustomConfig customConfig) {
        return RestResultGenerator.genSuccessResult(customConfigService.addCustomConfig(customConfig));
    }


    @DeleteMapping("/deleteCustomConfigById")
    @ApiOperation(value = "删除调制配方", httpMethod = "DELETE")
    public RestResult<Boolean> deleteCustomConfigById(@RequestParam("customConfigId") int customConfigId) {
        return RestResultGenerator.genSuccessResult(customConfigService.deleteCustomConfigById(customConfigId));
    }


    @PutMapping("/updateCustomConfigById")
    @ApiOperation(value = "更新调制配方", httpMethod = "PUT")
    public RestResult<Boolean> updateCustomConfig(@RequestBody CustomConfig customConfig) {
        return RestResultGenerator.genSuccessResult(customConfigService.updateCustomConfig(customConfig));
    }

    @GetMapping("/getMyMemberCustomConfigs")
    @ApiOperation(value = "得到我的调制配方列表", httpMethod = "GET")
    public RestResult<List<MemberCustomConfig>> getMyMemberCustomConfigs(@RequestParam("memberId") long memberId) {
        return RestResultGenerator.genSuccessResult(memberCustomConfigService.getMyMemberCustomConfig(memberId));
    }

    @PostMapping("/saveMemberCustomConfig")
    @ApiOperation(value = "保存到我的调制配方", notes = "如果CustomConfig的id不为空，则保存是现在的调制配方，" +
            "为空，则会先新添加再保存", httpMethod = "POST")
    public RestResult<Boolean> saveMemberCustomConfig(@RequestBody RequestCustomConfig requestCustomConfig) {
        if (requestCustomConfig.customConfig.getId() != null) {
            return RestResultGenerator.genSuccessResult(memberCustomConfigService.
                    saveMemberCustomConfig(requestCustomConfig.memberId, requestCustomConfig.customConfig.getId()));
        } else {
            return RestResultGenerator.genSuccessResult(memberCustomConfigService.
                    saveMemberCustomConfig(requestCustomConfig.memberId, requestCustomConfig.customConfig));
        }
    }

    @DeleteMapping("/deleteMemberCustomConfig")
    @ApiOperation(value = "移除我的调制配方", notes = "如果CustomConfig的id不为空，则保存是现在的调制配方，" +
            "为空，则会先新添加再保存", httpMethod = "DELETE")
    public RestResult<Boolean> deleteMemberCustomConfig(@RequestParam("memberId") long memberId,
                                                        @RequestParam("customConfigId") int customConfigId) {
        return RestResultGenerator.genSuccessResult(memberCustomConfigService.deleteMemberCustomConfig(memberId, customConfigId));
    }

    @GetMapping("/getProductConfig")
    @ApiOperation("根据产品id得到产品配方")
    public RestResult<CustomConfig> getProductConfig(@RequestParam("productId") int productId) {
        return RestResultGenerator.genSuccessResult(customConfigService.getProductConfigByProductId(productId));
    }


}
