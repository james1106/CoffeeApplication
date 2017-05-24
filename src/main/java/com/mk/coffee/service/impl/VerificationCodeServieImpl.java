package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.VerificationCodeMapper;
import com.mk.coffee.model.VerificationCode;
import com.mk.coffee.service.VerificationCodeService;
import com.mk.coffee.utils.DaYuUtils;
import com.mk.coffee.utils.DateUtils;
import com.mk.coffee.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
@Service
public class VerificationCodeServieImpl implements VerificationCodeService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    VerificationCodeMapper verificationCodeMapper;
    @Autowired
    DaYuUtils daYuUtils;


    /**
     * 得到验证码
     *
     * @param phone          手机号码
     * @param validityMinute 有效时间（分钟）
     * @return
     * @throws AppException
     */
    public boolean getVerificationCode(String phone, int validityMinute) throws AppException {
        String code = VerifyUtils.getFourRandom();
        logger.info("短信验证码：" + code);

        //查询最近一条验证码
        List<VerificationCode> olderVerificationCodes = verificationCodeMapper.selectCodesByPhoneOrderCreateDate(phone);
        if (olderVerificationCodes != null && olderVerificationCodes.size() > 0) {
            VerificationCode olderVerificationCode = olderVerificationCodes.get(0);
            if (olderVerificationCode != null) {
                //验证码可用
                logger.info("当前时间：" + DateUtils.getDate2LStr(DateUtils.getGMT8Time()));
                if (DateUtils.getGMT8Time().getTime() - olderVerificationCode.getCreateDate().getTime() <= olderVerificationCode.getValidityMinute() * 1000 * 60
                        && !olderVerificationCode.getVerifyState()) {
                    throw AppException.getException(ErrorCode.Verification_Code_Availability.getCode());
                }
            }
        }

        //验证不可用，重新发送
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setVerifyState(false);
        verificationCode.setCode(code);
        verificationCode.setPhone(phone);
        verificationCode.setCreateDate(new Date());
        verificationCode.setValidityMinute(validityMinute);//默认5分钟
        //短信机发送,发送成功就插入数据库
        daYuUtils.sendSmsCode(verificationCode);

        //插入数据库
        return verificationCodeMapper.insert(verificationCode) > 0;
    }

    public boolean verificationByCode(int code) {
        return false;
    }

    @Override
    public boolean updateVerification(VerificationCode verificationCode) {
        return verificationCodeMapper.updateByPrimaryKeySelective(verificationCode) > 0;
    }
}
