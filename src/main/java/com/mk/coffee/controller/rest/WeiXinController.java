package com.mk.coffee.controller.rest;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.conf.weixin.WechatMpProperties;
import com.mk.coffee.entity.WxCard;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.WXCard;
import com.mk.coffee.service.WXInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxCardApiSignature;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
@RestController
@Api("微信相关接口")
public class WeiXinController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WXInfoService wxInfoService;

    @Autowired
    private WechatMpProperties wechatMpProperties;


    @GetMapping("mp/getOpenId")
    @ApiOperation(value = "通过code得到OpenID相关的信息", httpMethod = "GET")
    public RestResult<WxMpOAuth2AccessToken> getOpenIdByCode(@RequestParam("code") String code) throws IOException {
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            wxMpService.getAccessToken(true);
            return RestResultGenerator.genSuccessResult(wxMpOAuth2AccessToken);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Get_AccessToken_Fail.getCode(), e.getMessage());
        }

    }

    @GetMapping("/getAccessToken")
    @ApiOperation(value = "得到AccessToken", httpMethod = "GET")
    public RestResult<String> getAccessToken() {
        try {
            return RestResultGenerator.genSuccessResult(wxMpService.getAccessToken());
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Get_AccessToken_Fail.getCode(), e.getMessage());
        }
    }

    @PostMapping("/refreshAccessToken")
    @ApiOperation(value = "刷新AccessToken", httpMethod = "POST")
    public RestResult<String> refreshAccessToken() {
        try {
            return RestResultGenerator.genSuccessResult(wxMpService.getAccessToken(true));
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Get_AccessToken_Fail.getCode(), e.getMessage());
        }
    }


    @ApiOperation(value = "得到微信jsapi配置信息", notes = "签名用的url必须是调用JS接口页面的完整URL。", httpMethod = "GET")
    @GetMapping("/getConfigInfo")
    public RestResult<WxJsapiSignature> getConfigInfo(@RequestParam("url") String url) {
        try {
            return RestResultGenerator.genSuccessResult(wxMpService.createJsapiSignature(url));
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.WX_Config_Info_Error.getCode(), e);
        }
    }

    @PostMapping("/refreshJsapiTicket")
    @ApiOperation(value = "刷新JsapiTicket", notes = "getConfigInfo获取失败，调用这个接口刷新一次", httpMethod = "POST")
    public RestResult<String> refreshJsapiTicket() {
        try {
            return RestResultGenerator.genSuccessResult(wxMpService.getJsapiTicket(true));
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.WX_Refresh_Fail.getCode(), e);
        }
    }


    @GetMapping("/mp/getWxCardApiSignature")
    @ApiOperation(value = "得到调用卡券api时所需要的签名信息", notes = "得到卡券api时所需要的签名信息,用于调用微信SDK wx.chooseCard等")
    public RestResult<WxCardApiSignature> getCardApiSignature() {
        try {
            WxCardApiSignature wxCardApiSignature = wxMpService.getCardService().createCardApiSignature(wechatMpProperties.getAppId());
            return RestResultGenerator.genSuccessResult(wxCardApiSignature);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new AppException(e.getError().getErrorCode() + "", e.getMessage());
        }

    }


    @GetMapping("/getWXCardInfo")
    @ApiOperation(value = "得到微信卡券信息", notes = "用来给前端动态计算优惠金额，(cardType:卡券类型（CASH：代金券，DISCOUNT：折扣券），title:卡券标题，discount：代金额,reduceCost:打折数)")
    public RestResult<WXCard> getWXCardInfo(@RequestParam("cardId") String cardId, @RequestParam("encryptCode") String encryptCode) {
        return RestResultGenerator.genSuccessResult(wxInfoService.getWXCart(cardId, encryptCode));
    }

    @PostMapping("/mp/getCardApiTicket")
    @ApiOperation(value = "获得卡券api_ticket", notes = "获得时会检查卡券apiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干")
    public RestResult<String> getCardApiTicket() {
        try {
            return RestResultGenerator.genSuccessResult(wxMpService.getCardService().getCardApiTicket(true));
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new AppException(e.getError().getErrorCode() + "", e.getMessage());
        }
    }

    @GetMapping("/getWXAuthorizationUrl")
    @ApiOperation(value = "得到要授权的URL地址", notes = "通过静默方式得到授权的URL")
    public RestResult<String> getWXAuthorizationUrl(@RequestParam(value = "url") String url) {
        return RestResultGenerator.genSuccessResult(wxInfoService.getAuthorizationUrl(url));

    }

    @GetMapping("/getCardList")
    @ApiOperation("获取用户已领取卡券接口")
    public RestResult<List<WxCard>> getCardList(@RequestParam("memberId") long memberId) {
        try {
            return RestResultGenerator.genSuccessResult(wxInfoService.getCardList(memberId));
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Get_WX_Card_Fail.getCode(), e);
        }
    }

    @GetMapping("/createWxCardExt")
    @ApiOperation("生成CardExt")
    public RestResult<WxCardApiSignature> createWxCardExt(@RequestParam("cardId") String cardId) {

        try {
            WxCardApiSignature wxCardApiSignature = wxMpService.getCardService().createCardApiSignature(cardId);
            return RestResultGenerator.genSuccessResult(wxCardApiSignature);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new AppException(e.getError().getErrorCode() + "", e.getMessage());
        }

    }


}
