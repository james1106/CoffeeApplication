package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.LocalAuthMapper;
import com.mk.coffee.mapper.MembersMapper;
import com.mk.coffee.mapper.ProductMapper;
import com.mk.coffee.mapper.RecoveryPictureMapper;
import com.mk.coffee.model.LocalAuth;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.Product;
import com.mk.coffee.model.RecoveryPicture;
import com.mk.coffee.service.FileUploadService;
import com.mk.coffee.utils.QiNiuUtils;
import com.mk.coffee.utils.VerifyUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private QiNiuUtils qiNiuUtils;

    @Autowired
    private MembersMapper membersMapper;
    @Autowired
    private LocalAuthMapper localAuthMapper;
    @Autowired
    private RecoveryPictureMapper recoveryPictureMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public String uploadHeadPortrait(long memberId, MultipartFile file) throws AppException {
       /* //验证Token
        LocalAuth localAuth = localAuthMapper.selectLocalAuthByToken(token);
        VerifyUtils.verificationToken(token, localAuth);*/
        if (file.isEmpty()) {
            throw AppException.getException(ErrorCode.Upload_File_Null.getCode());
        }
        Members member = membersMapper.selectByPrimaryKey(memberId);
        if (member == null) {
            AppException.getException(ErrorCode.Member_Not_Exist.getCode());
        }
        //原本有头像，则回收
        if (member.getHeadportraitUrl() != null && !member.getHeadportraitUrl().trim().equals("")) {
            RecoveryPicture recoveryPicture = new RecoveryPicture();
            recoveryPicture.setMemberId(memberId);
            recoveryPicture.setPictureKey(member.getHeadportraitUrl());
            recoveryPictureMapper.insert(recoveryPicture);
        }
        try {
            String key = qiNiuUtils.updateLoadFile(file.getBytes());
            member.setHeadportraitUrl(key);//设置头像
            membersMapper.updateByPrimaryKey(member);
            return key;
        } catch (IOException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Upload_File_Null.getCode());
        }

    }


    @Override
    @Transactional
    public String uploadproductPicture(int productId, MultipartFile file) throws AppException {
       /* //验证Token
        LocalAuth localAuth = localAuthMapper.selectLocalAuthByToken(token);
        VerifyUtils.verificationToken(token, localAuth);*/
        if (file.isEmpty()) {
            throw AppException.getException(ErrorCode.Upload_File_Null.getCode());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        //没有该商品
        if (product == null) {
            throw AppException.getException(ErrorCode.Product_Not_Exist.getCode());
        }

        //回收商品原来图片
        if (product.getPictureUrl() != null && !product.getPictureUrl().equals("")) {
            RecoveryPicture recoveryPicture = new RecoveryPicture();
            recoveryPicture.setProductId(productId);
            recoveryPicture.setPictureKey(product.getPictureUrl());
            recoveryPictureMapper.insert(recoveryPicture);
        }

        //上传
        try {
            String key = qiNiuUtils.updateLoadFile(file.getBytes());
            product.setPictureUrl(key);
            productMapper.updateByPrimaryKey(product);
            return key;
        } catch (IOException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Upload_File_Null.getCode());
        }
    }

    @Override
    public String uploadPicture(MultipartFile file) throws AppException {
        //上传
        try {
            return qiNiuUtils.updateLoadFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Upload_File_Null.getCode());
        }
    }


}
