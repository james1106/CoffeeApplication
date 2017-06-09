package com.mk.coffee.utils;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.conf.dayu.DaYuProperties;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.VerificationCode;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 阿里大于工具类
 * Created by Administrator on 2017/3/2 0002.
 */
@Component
public class DaYuUtils {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DaYuProperties daYuProperties;

    public boolean sendSmsCode(VerificationCode verificationCode) {
        TaobaoClient client = new DefaultTaobaoClient(daYuProperties.getUrl(), daYuProperties.getAppkey(),
                daYuProperties.getSecret());
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend(verificationCode.getPhone());
        req.setSmsType("normal");
        req.setSmsFreeSignName(daYuProperties.getSmsFreeSignName());
        //{code:"sss",date:""}
        StringBuffer sb = new StringBuffer();
        sb.append("{code:").append("'").append(verificationCode.getCode()).append("'").append(",")
                .append("date:").append("'").append(verificationCode.getValidityMinute()).append("'").append("}");
        req.setSmsParamString(sb.toString());
        req.setRecNum(verificationCode.getPhone());
        req.setSmsTemplateCode(daYuProperties.getSmsTemplateCode());
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            logger.info(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Send_Sms_Fail.getCode());
        }
        return true;
    }
}
