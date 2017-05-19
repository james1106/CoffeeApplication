package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.conf.coffees.MakeCoffeesProperties;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.ProductMapper;
import com.mk.coffee.model.*;
import com.mk.coffee.requestbody.RequestCooperativePartnerMakeCoffee;
import com.mk.coffee.service.CooperativePartnerProductService;
import com.mk.coffee.service.CooperativePartnerService;
import com.mk.coffee.service.MakeCoffeesService;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.task.AsyncTask;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.JsonUtils;
import com.mk.coffee.utils.MD5Util;
import com.mk.coffee.utils.ParamUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
@Service
public class MakeCoffeesServiceImpl implements MakeCoffeesService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MakeCoffeesProperties makeCoffeesProperties;

    @Autowired
    private ProductConversionCodeService productConversionCodeService;

    @Autowired
    private CooperativePartnerService cooperativePartnerService;
    @Autowired
    private CooperativePartnerProductService cooperativePartnerProductService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AsyncTask asyncTask;


    @Override
    @Transactional
    public boolean makeCoffees(ProductConversionCode productConversionCode, String vmc) throws IOException {
        CustomConfig customConfig = new CustomConfig();
        customConfig.setCoffee(2);
        customConfig.setMilk(3);
        customConfig.setSugar(3);
        return makeCoffeesByCustomConfigure(productConversionCode, vmc, customConfig);
    }

    @Override
    public boolean makeCoffeesByCustomConfigure(ProductConversionCode productConversionCode, String vmc, CustomConfig customConfig) {
        //获取商品
        Product product = null;
        if (productConversionCode.getProduct() != null) {
            product = productConversionCode.getProduct();
        } else {
            product = productMapper.selectByPrimaryKey(productConversionCode.getProductId());
        }
        if (product == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        if ((customConfig.getMilk() < 0 || customConfig.getMilk() > 5)
                || (customConfig.getCoffee() < 0 || customConfig.getCoffee() > 5)
                || (customConfig.getSugar() < 0 || customConfig.getSugar() > 5)) {
            throw AppException.getException(ErrorCode.ILLEGAL_PARAMS.getCode());
        }

        MakeCoffeesParam makeCoffeesParam = new MakeCoffeesParam();
        makeCoffeesParam.setID(productConversionCode.getOrderNum());
        makeCoffeesParam.setVMC(vmc);
        makeCoffeesParam.setPTYPE("FASTCODE");
        makeCoffeesParam.setPID(productConversionCode.getProductId() + "");
        makeCoffeesParam.setFASTCODE(customConfig.getCoffee() + "" + customConfig.getMilk() + "" + customConfig.getSugar());//自定义调制配方参数
        makeCoffeesParam.setUSERNAME(makeCoffeesProperties.getName());
        //ID=00005&USERNAME=user&PASSWORD=password&VMC=33333&PTYPE=FASTCODE&PID=810&FASTCODE=223
        StringBuffer mac = new StringBuffer();
        mac
                .append("ID=").append(makeCoffeesParam.getID()).append("&")
                .append("USERNAME=").append(makeCoffeesParam.getUSERNAME()).append("&")
                .append("PASSWORD=").append(makeCoffeesProperties.getPassword()).append("&")
                .append("VMC=").append(makeCoffeesParam.getVMC()).append("&")
                .append("PTYPE=").append(makeCoffeesParam.getPTYPE()).append("&")
                .append("PID=").append(makeCoffeesParam.getPID()).append("&")
                .append("FASTCODE=").append(makeCoffeesParam.getFASTCODE());

        makeCoffeesParam.setMAC(MD5Util.md5Encode(mac.toString(), null));

        String param = ParamUtils.getParam(makeCoffeesParam);
        logger.info(param);
        try {
            String body = wxMpService.get(makeCoffeesProperties.getUrl(), param);
            MakeCoffeeStatus status = JsonUtils.fromJsonObject(body, MakeCoffeeStatus.class);
            //制作成功
            if (status != null) {
                if (status.code == 200 && status.status.equals("success")) {
                    //更新销量
                    product.setSales(product.getSales() + 1);
                    product.setId(product.getId());
                    productMapper.updateByProductId(product);
                    //将一个定时任务5分钟的任务用于判断领取失败的结果
                    asyncTask.doTask(productConversionCode, vmc);
                    //设置为待领取,纪录咖啡机ID
                    return productConversionCodeService.updateProductConversionCodeById(productConversionCode.getId(), vmc, 2);
                } else {
                    throw AppException.getException(ErrorCode.Make_Coffees_Fail.getCode());
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Make_Coffees_Fail.getCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Update_ProductConversionCode_State_Fail.getCode(), e.getMessage());
        }
        return false;
    }

    @Transactional
    @Override
    public boolean makeCoffeesByCooperativePartnerProduct(RequestCooperativePartnerMakeCoffee requestCooperativePartnerMakeCoffee) {
        CooperativePartner cooperativePartner = cooperativePartnerService.getItem(requestCooperativePartnerMakeCoffee.cooperativePartnerId);
        //公司不存在
        if (cooperativePartner == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        //密码不正确
        if (EmptyUtils.isEmpty(requestCooperativePartnerMakeCoffee.password)
                || !cooperativePartner.getPassword().equals(requestCooperativePartnerMakeCoffee.password)) {
            throw AppException.getException(ErrorCode.Password_Illegal);
        }

        ProductConversionCode productConversionCode = productConversionCodeService
                .createProductConversionCodeByMemberId(requestCooperativePartnerMakeCoffee.memberId,
                        requestCooperativePartnerMakeCoffee.productId);

        //插入一条公司人员喝咖啡纪录
        CooperativePartnerProduct cooperativePartnerProduct = new CooperativePartnerProduct();
        cooperativePartnerProduct.setMemberId(requestCooperativePartnerMakeCoffee.memberId);
        cooperativePartnerProduct.setProductId(requestCooperativePartnerMakeCoffee.productId);

        cooperativePartnerProduct.setCooperativePartnerId(requestCooperativePartnerMakeCoffee.cooperativePartnerId);
        cooperativePartnerProduct.setProductConversionId(productConversionCode.getId());
        cooperativePartnerProduct.setCreateDate(new Date());
        cooperativePartnerProductService.addItem(cooperativePartnerProduct);
        return makeCoffeesByCustomConfigure(productConversionCode, requestCooperativePartnerMakeCoffee.vmc, requestCooperativePartnerMakeCoffee.customConfig);

    }


}
