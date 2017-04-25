package com.mk.coffee.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.WXCard;
import com.mk.coffee.service.WXInfoService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpCardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/24 0024.
 */
@Service
public class WXInfoServiceImpl implements WXInfoService {
    @Autowired
    private WxMpService wxMpService;

    @Override
    public WXCard getWXCart(String cardId, String encryptCode) {
        WXCard wxCard = new WXCard();
        String code;
        try {
            code = wxMpService.getCardService().decryptCardCode(encryptCode);
            wxCard.setCode(code);
            WxMpCardResult wxMpCardResult = wxMpService.getCardService().queryCardCode(cardId, code, true);
            //微信卡券不可用
            if (!wxMpCardResult.getErrorMsg().equals("ok")) {
                throw AppException.getException(wxMpCardResult.getErrorCode(), wxMpCardResult.getErrorMsg());
            }
            //占用
            //wxMpService.getCardService().markCardCode(code, cardId, members.getOpenId(), true);
            String cardDetail = wxMpService.getCardService().getCardDetail(cardId);

            JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject) jsonParser.parse(cardDetail);
            if (jo.get("errcode").getAsInt() == 0) {
                JsonObject card = jo.getAsJsonObject("card");
                if (card.get("card_type").getAsString().equals("CASH")) { //代金券
                    wxCard.setCardType("CASH");
                    JsonObject cashJsonObject = card.getAsJsonObject("cash");
                    JsonObject baseInfoJsonObject = cashJsonObject.getAsJsonObject("base_info");
                    wxCard.setTitle(baseInfoJsonObject.get("title").getAsString());
                    float reductCost = (float) (cashJsonObject.get("reduce_cost").getAsFloat() * 0.01);
                    wxCard.setReduceCost(reductCost);
                } else if (card.get("card_type").getAsString().equals("DISCOUNT")) {//折扣券
                    wxCard.setCardType("DISCOUNT");
                    JsonObject discountJsonObject = card.getAsJsonObject("discount");
                    JsonObject baseInfoJsonObject = discountJsonObject.getAsJsonObject("base_info");
                    wxCard.setTitle(baseInfoJsonObject.get("title").getAsString());
                    float discount = (float) ((100 - discountJsonObject.get("discount").getAsInt()) * 0.01);
                    wxCard.setDiscount(discount);
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new AppException(e.getError().getErrorCode() + "", e.getMessage());
        }
        return wxCard;
    }

    @Override
    public String getAuthorizationUrl(String redirectUri) {
        return wxMpService.oauth2buildAuthorizationUrl(redirectUri, WxConsts.OAUTH2_SCOPE_BASE, null);
    }
}
