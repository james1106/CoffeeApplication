package com.mk.coffee.controller.rest;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * Created by Administrator on 2017/3/3
 */
@Api("文件上传接口")
@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload/member/{memberId}")
    @ApiOperation(value = "上传头像", notes = "根据令牌，会员ID上传会员头像")
    public RestResult<String> uploadHeadPortrait(
            @PathVariable("memberId") long memberId,
            @RequestParam("file") MultipartFile file) {
        return RestResultGenerator.genSuccessResult(fileUploadService.uploadHeadPortrait(memberId, file));
    }

    @ApiOperation(value = "上传商品图片", notes = "根据令牌，商品ID上传商品图片")
    @PostMapping("/upload/product/{productId}")
    public RestResult<String> uploadproductPicture(
            @PathVariable("productId") int productId,
            @RequestParam("file") MultipartFile file) throws AppException {
        return RestResultGenerator.genSuccessResult(fileUploadService.uploadproductPicture(productId, file));
    }

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @PostMapping("/uploadPicture")
    public RestResult<String> uploadPicture(@RequestParam("file") MultipartFile file) {
        return RestResultGenerator.genSuccessResult(fileUploadService.uploadPicture(file));
    }
}
