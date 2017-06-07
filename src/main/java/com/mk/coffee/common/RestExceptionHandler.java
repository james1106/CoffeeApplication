package com.mk.coffee.common;

import com.mk.coffee.exception.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


/**
 * 统一异常处理
 * Created by Administrator on 2017/2/23 0023.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> RestResult<T> runtimeExceptionHandler(Exception e) {
        LOGGER.error("---------> huge error!", e);
        return RestResultGenerator.genErrorResult(AppException.getException(ErrorCode.SERVER_ERROR.getCode()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> RestResult<T> illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("---------> invalid request!", e);
        return RestResultGenerator.genErrorResult(AppException.getException(ErrorCode.ILLEGAL_PARAMS.getCode()));
    }

    //没有权限异常
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public <T> RestResult<T> unauthorizedExceptionHandler(UnauthorizedException exception) {
        LOGGER.error("UnauthorizedException", exception);
        return RestResultGenerator.genErrorResult(AppException.getException(ErrorCode.Un_Authorized_Exception));
    }

    //空指针异常
    @ExceptionHandler({NullPointerException.class})
    @ResponseBody
    public <T> RestResult<T> nullPointerExceptionHandler(NullPointerException exception) {
        LOGGER.error("NullPointerException", exception);
        return RestResultGenerator.genErrorResult(AppException.getException(ErrorCode.NOT_FOUND_DATA));
    }

    //处理APP异常
    @ExceptionHandler({AppException.class})
    @ResponseBody
    public <T> RestResult<T> appExceptionHandler(AppException ex) {
        //TODO:记录日志
        LOGGER.error("AppException", ex);
        return RestResultGenerator.genErrorResult(ex.getCode(), ex.getMessage());
    }



}
