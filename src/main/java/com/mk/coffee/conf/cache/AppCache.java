package com.mk.coffee.conf.cache;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

import com.mk.coffee.mapper.ErrorCodeMapper;
import com.mk.coffee.model.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 应用缓存类，使用静态对象方式对部分常用参数进行内存缓存。
 */
@Repository
public class AppCache {
    /**
     * 初始化ErrorCode配置
     */
    public static final Map<String, String> errorInfoConfig = new ConcurrentHashMap();

    @Autowired
    ErrorCodeMapper errorCodeMapper;

    /**
     * 加载“错误消息”“提示消息”
     *
     * @see #loadErrorConfig()
     */
    @PostConstruct
    public void init() {
        loadErrorConfig();
    }

    /**
     * 清理代码缓存
     */
    public void clear() {
        errorInfoConfig.clear();
    }

    /**
     * 重新加载代码缓存
     */
    public void reLoad() {
        clear();
        init();
    }

    /**
     * 从数据库加载“错误消息”，初始化到{@link #errorInfoConfig}
     */
    public void loadErrorConfig() {
        List<ErrorCode> errorCodeList = errorCodeMapper.selectByExample(null);
        for (int i = 0; i < errorCodeList.size(); i++) {
            ErrorCode errorCode = errorCodeList.get(i);
            errorInfoConfig.put(errorCode.getCode(), errorCode.getMessage());
        }
    }

}
