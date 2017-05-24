package com.mk.coffee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.LocalAuthMapper;
import com.mk.coffee.mapper.MembersMapper;
import com.mk.coffee.mapper.VerificationCodeMapper;
import com.mk.coffee.model.*;
import com.mk.coffee.service.MembersService;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.MD5STo16Byte;
import com.mk.coffee.utils.VerifyUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/25 0025.
 */
@Service
public class MembersServiceImpl implements MembersService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private VerifyUtils verifyUtils;
    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private ProductConversionCodeService productConversionCodeService;
    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Autowired
    private LocalAuthMapper localAuthMapper;

    @Autowired
    private WxMpService mpService;

    /**
     * 注册
     */
    public boolean register(Members members, String code) throws AppException {
        //查询成员
        Members members1 = membersMapper.getMemberByPhone(members.getPhone());
        if (members1 != null) {
            throw AppException.getException(ErrorCode.Phone_ALREADY.getCode());
        }

        //查询最近一条验证码
        List<VerificationCode> olderVerificationCodes = verificationCodeMapper.selectCodesByPhoneOrderCreateDate(members.getPhone());
        //验证
        if (olderVerificationCodes != null && olderVerificationCodes.size() > 0) {
            verifyUtils.verificationCode(members.getPhone(), code, olderVerificationCodes.get(0));
        }

        int count = membersMapper.register(members);
        return count > 0;
    }

    @Override
    @Transactional
    public boolean registerPhoneByCodeBindOpenId(String phone, String code, String openId) {
        if (EmptyUtils.isEmpty(phone) || EmptyUtils.isEmpty(code) || EmptyUtils.isEmpty(openId)) {
            throw AppException.getException(ErrorCode.Phone_Or_Code_Null.getCode());
        }
        //查询手机注册的成员
        Members phoneMember = membersMapper.getMemberByPhone(phone);
        if (phoneMember != null && phoneMember.getIsRegist()) {
            throw AppException.getException(ErrorCode.Phone_ALREADY.getCode());
        }
        //查询微信关注的成员纪录
        Members openIdMember = membersMapper.getMemberByOpenId(openId);
        //新的成员纪录
        Members members = new Members();
        members.setCreateDate(new Date());
        members.setOpenId(openId);
        members.setPhone(phone);
        members.setName(phone);
        members.setIsRegist(true);//标示为已注册手机号码
        if (openIdMember != null) {
            members.setId(openIdMember.getId());
            if (VerifyUtils.isNotEmpty(openIdMember.getHeadportraitUrl()))
                members.setHeadportraitUrl(openIdMember.getHeadportraitUrl());
            if (VerifyUtils.isNotEmpty(members.getSex())) members.setSex(openIdMember.getSex());
            //删除openId纪录
            MembersExample example = new MembersExample();
            example.createCriteria().andOpenIdEqualTo(openId);
            membersMapper.deleteByExample(example);
        }

        //优先手机号注册的数据
        if (phoneMember != null) {
            members.setId(phoneMember.getId());
            if (VerifyUtils.isNotEmpty(phoneMember.getName())) members.setName(phoneMember.getName());
            if (VerifyUtils.isNotEmpty(phoneMember.getEmail())) members.setEmail(phoneMember.getEmail());
            if (VerifyUtils.isNotEmpty(phoneMember.getHeadportraitUrl())) {
                members.setHeadportraitUrl(phoneMember.getHeadportraitUrl());
            }
            if (VerifyUtils.isNotEmpty(phoneMember.getPassword())) members.setPassword(phoneMember.getPassword());

            //删除手机注册纪录
            MembersExample example = new MembersExample();
            example.createCriteria().andPhoneEqualTo(phone);
            membersMapper.deleteByExample(example);
        }

        //查询最近一条验证码
        List<VerificationCode> olderVerificationCodes = verificationCodeMapper.selectCodesByPhoneOrderCreateDate(members.getPhone());
        //验证
        if (olderVerificationCodes != null && olderVerificationCodes.size() > 0) {
            verifyUtils.verificationCode(members.getPhone(), code, olderVerificationCodes.get(0));
        }
        //重新注册
        int count = membersMapper.insert(members);
        //送一杯
        return count > 0 && productConversionCodeService.createProductConversionCodeByMemberId(members.getId(), 102) != null;
    }


    @Override
    public boolean registerByWxMpUser(WxMpUser userWxInfo) {
        Members members = membersMapper.getMemberByOpenId(userWxInfo.getOpenId());
        if (members != null) {
            throw AppException.getException(ErrorCode.Member_Already_Exist.getCode());
        }
        //插入成员open_id
        members = new Members();
        members.setId(System.currentTimeMillis());
        members.setOpenId(userWxInfo.getOpenId());
        members.setSex(userWxInfo.getSex());
        members.setHeadportraitUrl(userWxInfo.getHeadImgUrl());
        members.setIsRegist(false);
        members.setCreateDate(new Date());
        return membersMapper.inertMemberByOpenId(members) > 0;
    }

    /**
     * @param phone 手机号
     * @return 返回成员id
     */

    @Transactional
    public LoginInfo login(String phone, String code) throws AppException {
        VerifyUtils.verifyPhoneNum(phone);
        //查询成员
        Members members1 = membersMapper.getMemberByPhone(phone);
        if (members1 == null) {
            throw AppException.getException(ErrorCode.Phone_Not_Exist.getCode());
        }
        //查询最近一条验证码
        List<VerificationCode> olderVerificationCodes = verificationCodeMapper.selectCodesByPhoneOrderCreateDate(phone);
        //验证
        if (olderVerificationCodes != null && olderVerificationCodes.size() > 0) {
            verifyUtils.verificationCode(phone, code, olderVerificationCodes.get(0));
        } else {
            throw AppException.getException(ErrorCode.Verification_Code_Error.getCode());

        }

        //删除旧令牌
        localAuthMapper.deleteTokenByMemberId(members1.getId());
        //生成新的令牌
        String token = MD5STo16Byte.getMD5Str32(VerifyUtils.getFourRandom() + System.currentTimeMillis());
        LocalAuth localAuth = new LocalAuth();
        localAuth.setMemberId(members1.getId());
        localAuth.setCreateDate(new Date());
        localAuth.setToken(token);
        localAuth.setValidityDay(30);
        localAuthMapper.insert(localAuth);
        //更新成员令牌
        members1.setLocalToken(token);
        membersMapper.updateTokenByPrimaryKey(members1);
        return new LoginInfo(members1.getId(), localAuth.getToken());
    }


    /**
     * 游客登录
     *
     * @param phone
     * @param code
     * @return
     * @throws AppException
     */
    @Transactional
    public LoginInfo touristsLogin(String phone, String code) throws AppException {
        VerifyUtils.verifyPhoneNum(phone);
        //查询成员
        Members members1 = membersMapper.getMemberByPhone(phone);
        //没有纪录就注册
        if (members1 == null) {
            members1 = new Members();
            members1.setId(System.currentTimeMillis());
            members1.setPhone(phone);
            members1.setCreateDate(new Date());
            membersMapper.register(members1);
        } else {
            //删除旧令牌
            localAuthMapper.deleteTokenByMemberId(members1.getId());
        }
        //查询最近一条验证码
        List<VerificationCode> olderVerificationCodes = verificationCodeMapper.selectCodesByPhoneOrderCreateDate(phone);
        //验证
        if (olderVerificationCodes != null && olderVerificationCodes.size() > 0) {
            verifyUtils.verificationCode(phone, code, olderVerificationCodes.get(0));
        } else {
            throw AppException.getException(ErrorCode.Verification_Code_Error.getCode());
        }

        //生成新的令牌
        String token = MD5STo16Byte.getMD5Str32(VerifyUtils.getFourRandom() + System.currentTimeMillis());
        LocalAuth localAuth = new LocalAuth();
        localAuth.setMemberId(members1.getId());
        localAuth.setCreateDate(new Date());
        localAuth.setToken(token);
        localAuth.setValidityDay(30);
        localAuthMapper.insert(localAuth);
        //更新成员令牌
        members1.setLocalToken(token);
        membersMapper.updateTokenByPrimaryKey(members1);
        return new LoginInfo(members1.getId(), token);
    }

    //通过openId登录
    @Override
    @Transactional
    public LoginInfo loginByOpenId(String openID) throws IOException {
        Members members1 = membersMapper.getMemberByOpenId(openID);
        //没有纪录就注册
        if (members1 == null) {
            members1 = new Members();
            members1.setId(System.currentTimeMillis());
            members1.setCreateDate(new Date());
            members1.setOpenId(openID);
            membersMapper.insert(members1);
        } else {
            //删除旧令牌
            localAuthMapper.deleteTokenByMemberId(members1.getId());
        }
        //生成新的令牌
        String token = MD5STo16Byte.getMD5Str32(VerifyUtils.getFourRandom() + System.currentTimeMillis());
        LocalAuth localAuth = new LocalAuth();
        localAuth.setMemberId(members1.getId());
        localAuth.setCreateDate(new Date());
        localAuth.setToken(token);
        localAuth.setValidityDay(30);
        localAuthMapper.insert(localAuth);
        //更新成员令牌
        members1.setLocalToken(token);
        membersMapper.updateTokenByPrimaryKey(members1);
        return new LoginInfo(members1.getId(), token);
    }


    /**
     * @param memberId
     * @return 成员信息
     */

    public Members getMember(long memberId) throws AppException {
        /*//验证Token
        LocalAuth localAuth = localAuthMapper.selectLocalAuthByToken(token);
        VerifyUtils.verificationToken(token, localAuth);*/

        Members members = membersMapper.getMemberById(memberId);
        if (members == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return members;
    }


    /**
     * 修改会员资料
     */
    public boolean updateMember(Members newMembers) throws AppException {
        /*//验证Token
        LocalAuth localAuth = localAuthMapper.selectLocalAuthByToken(token);
        VerifyUtils.verificationToken(token, localAuth);*/

        Members members1 = membersMapper.selectByPrimaryKey(newMembers.getId());
        if (members1 == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return membersMapper.updataMemberByMember(newMembers) > 0;
    }


    @Override
    public boolean inertMemberByOpenId(Members members) {
        Members member = membersMapper.selectMemberByOpenId(members.getOpenId());
        if (member != null) {
            return false;
        }
        return membersMapper.inertMemberByOpenId(members) > 0;

    }

    @Override
    public ListResult<Members> getListByPage(int page, int size) {
        //开始分页
        PageHelper.startPage(page, size);
        //查询结果
        List<Members> list = membersMapper.getMemberList();
        if (list == null || list.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        //获取分页信息
        PageInfo<Members> info = new PageInfo<>(list);
        return new ListResult<>(info.getList(), info.getTotal(), info.getPages());

    }

}
