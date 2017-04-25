package com.mk.coffee.service;

import com.mk.coffee.exception.AppException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件服务
 * Created by Administrator on 2017/3/3 0003.
 */
public interface FileUploadService {
    String uploadHeadPortrait(long memberId, MultipartFile file) throws AppException;

    String uploadproductPicture(int productId, MultipartFile file) throws AppException;
}
