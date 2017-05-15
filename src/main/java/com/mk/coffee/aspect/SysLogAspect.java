package com.mk.coffee.aspect;

import com.mk.coffee.model.SysLog;
import com.mk.coffee.service.SysLogService;
import org.apache.log4j.Logger;
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
 * 后台 Rest请求切面
 */
@Aspect
@Order(5)
@Component
public class SysLogAspect {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private SysLogService sysLogService;

    private SysLog sysLog;
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.mk.coffee.controller.sys..*.*(..))")
    public void sysLog() {
    }

    @Before("sysLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        sysLog = new SysLog();
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        if (request.getHeader("userId") != null) {
            sysLog.setUserId(Integer.parseInt(request.getHeader("userId")));
        }
        sysLog.setCreateDate(new Date());
        sysLog.setMethod(request.getMethod());
        sysLog.setUrl(request.getRequestURL().toString());
        sysLog.setIp(request.getRemoteAddr());
        sysLog.setParam(Arrays.toString(joinPoint.getArgs()));

        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "sysLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
        sysLog.setSpendTime((System.currentTimeMillis() - startTime.get()) + "");
        sysLog.setResult(ret.toString());
        sysLogService.addItem(sysLog);
    }
}
