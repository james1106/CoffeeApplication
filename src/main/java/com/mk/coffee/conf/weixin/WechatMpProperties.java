package com.mk.coffee.conf.weixin;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/3/7 0007.
 */
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatMpProperties {
    /**
     * 设置微信公众号的appid
     */
    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    private String secret;

    /**
     * 设置微信公众号的token
     */
    private String token;

    /**
     * 设置微信公众号的EncodingAESKey
     */
    private String aesKey;

    private String notifyURL;

    private int intervalTime = 10;//支付失效间隔时间(分钟)

    private String body;//主题

    //微信支付商户号
    private String partenerId;
    //微信支付平台商户API密钥
    private String partenerKey;

    private String certPath;//证书的路径

    private String paySucceedTemplateId;//支付成功模版ID
    private String paySucceedTemplateUrl;//支付成功模版跳转url

    private String makeCoffeesTemplateId;//制作Coffees通知
    private String makeCoffeesTemplateUrl;//制作Coffees通知url

    public String getMakeCoffeesTemplateId() {
        return makeCoffeesTemplateId;
    }

    public void setMakeCoffeesTemplateId(String makeCoffeesTemplateId) {
        this.makeCoffeesTemplateId = makeCoffeesTemplateId;
    }

    public String getMakeCoffeesTemplateUrl() {
        return makeCoffeesTemplateUrl;
    }

    public void setMakeCoffeesTemplateUrl(String makeCoffeesTemplateUrl) {
        this.makeCoffeesTemplateUrl = makeCoffeesTemplateUrl;
    }

    public String getPaySucceedTemplateUrl() {
        return paySucceedTemplateUrl;
    }

    public void setPaySucceedTemplateUrl(String paySucceedTemplateUrl) {
        this.paySucceedTemplateUrl = paySucceedTemplateUrl;
    }

    public String getPaySucceedTemplateId() {
        return paySucceedTemplateId;
    }

    public void setPaySucceedTemplateId(String paySucceedTemplateId) {
        this.paySucceedTemplateId = paySucceedTemplateId;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getPartenerId() {
        return partenerId;
    }

    public void setPartenerId(String partenerId) {
        this.partenerId = partenerId;
    }

    public String getPartenerKey() {
        return partenerKey;
    }

    public void setPartenerKey(String partenerKey) {
        this.partenerKey = partenerKey;
    }

    public String getNotifyURL() {
        return notifyURL;
    }

    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return this.aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
