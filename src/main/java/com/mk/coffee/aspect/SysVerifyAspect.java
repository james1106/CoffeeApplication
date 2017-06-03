package com.mk.coffee.aspect;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.SysLog;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.service.SysLogService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * 验证切面
 * Created by Administrator on 2017/6/3 0003.
 */
@Aspect
@Order(4)
@Component
public class SysVerifyAspect {
    private Logger logger = Logger.getLogger(getClass());

    @Pointcut("execution(public * com.mk.coffee.controller.sys.verify..*.*(..))")
    public void sysVerify() {
    }

    @Before("sysVerify()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser == null) {
            throw AppException.getException(ErrorCode.Current_User_Not_Exist);
        }

    }
}
