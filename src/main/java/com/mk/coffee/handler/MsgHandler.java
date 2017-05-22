package com.mk.coffee.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mk.coffee.builder.TextBuilder;
import com.mk.coffee.model.WxKeyword;
import com.mk.coffee.model.WxMessage;
import com.mk.coffee.service.WXKeywordService;
import com.mk.coffee.service.WXMessageService;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author Binary Wang
 */
@Component
public class MsgHandler extends AbstractHandler {
    @Autowired
    private WXMessageService wxMessageService;

    @Autowired
    private WXKeywordService wxKeywordService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
            //TODO 可以选择将消息保存到本地
            //匹配关键字
            boolean isExt = false;
            List<WxKeyword> allWxKeywords = wxKeywordService.getList();
            for (WxKeyword keyword : allWxKeywords) {
                if (wxMessage.getContent().contains(keyword.getKeyword())) {
                    isExt = true;
                    break;
                }
            }
            if (isExt) {
                //保存消息
                WxMessage localWxMessage = new WxMessage();
                localWxMessage.setCreateDate(new Date(wxMessage.getCreateTime()));
                localWxMessage.setContent(wxMessage.getContent());
                localWxMessage.setMsgType(wxMessage.getMsgType());
                localWxMessage.setFromUser(wxMessage.getFromUser());
                localWxMessage.setToUser(wxMessage.getToUser());
                wxMessageService.addItem(localWxMessage);
            }

        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                    && weixinService.getKefuService().kfOnlineList()
                    .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                        .fromUser(wxMessage.getToUser())
                        .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        //TODO 组装回复消息
        String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);

        /*return new TextBuilder().build(content, wxMessage, weixinService);*/
        return null;

    }

}
