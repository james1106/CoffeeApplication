package com.mk.coffee.conf.jpush;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 极光推送
 * Created by Administrator on 2017/4/20 0020.
 */
@Component
@ConfigurationProperties(prefix = "jpush")
public class JPushProperties {
    private String appKey;
    private String masterSecret;

}
