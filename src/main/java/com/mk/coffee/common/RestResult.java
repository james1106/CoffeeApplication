package com.mk.coffee.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Administrator on 2017/2/23 0023.
 *
 * @JsonInclude(JsonInclude.Include.NON_NULL) 属性为空不参与序列化
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult<T> {

    private boolean result;

    private String code;
    private String message;
    private T data;

    private RestResult() {
    }

    public static <T> RestResult<T> newInstance() {
        return new RestResult<T>();
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
