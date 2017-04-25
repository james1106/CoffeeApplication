package com.mk.coffee.common;

import com.mk.coffee.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * 生成restResult工具类
 * Created by Administrator on 2017/2/23 0023.
 */
public class RestResultGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestResultGenerator.class);

    /**
     * normal
     *
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genResult(boolean success, T data, String code, String message) {
        RestResult<T> result = RestResult.newInstance();
        result.setResult(success);
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:{}", result);
        }
        return result;
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genSuccessResult(T data) {

        return genResult(true, data, null, null);
    }

    /**
     * error message
     *
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(String code, String message) {

        return genResult(false, null, code, message);
    }

    /**
     * error
     *
     * @param error error enum
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(AppException error) {

        return genErrorResult(error.getCode(), error.getMessage());
    }

    /**
     * success no message
     *
     * @return
     */
    public static RestResult genSuccessResult() {
        return genSuccessResult(null);
    }


}
