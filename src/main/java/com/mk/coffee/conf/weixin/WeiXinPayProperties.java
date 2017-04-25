package com.mk.coffee.conf.weixin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信支付配置 2017/3/6 0006.
 */
@Component
@ConfigurationProperties(prefix = "weixin")
public class WeiXinPayProperties {
    private String appid="wxb4de448e44d78c61";//微信支付分配的公众账号ID（企业号corpid即为此appId）
    private String mch_id="1410905702";//微信支付分配的商户号
    private String device_info="WEB";//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
    private String nonce_str;//随机字符串，长度要求在32位以内。
    private String sign;//通过签名算法计算得出的签名值，详见签名生成算法
    private String sign_type = "MD5";//签名类型，默认为MD5，支持HMAC-SHA256和MD5,(可选)
    private String body;//商品简单描述，该字段请按照规范传递，具体请见参数规定
    private String detail;//商品详细列表，使用Json格式（可选）
    private String out_trade_no;//商户系统内部订单号，要求32个字符内、且在同一个商户号下唯一
    private String total_fee;//总金额
    private String spbill_create_ip;//APP和网页支付提交用户端ip
    private String notify_url;//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
    private String trade_type;//取值如下：JSAPI，NATIVE，APP等，说明详见
    private String openid="oEIUowRhhWNxq3S68fXmwQ1Zos9A";//trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识


}
