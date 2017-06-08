package com.mk.coffee.common;

/**
 * 错误码对应数据库error_code表的Key值
 * Created by Administrator on 2017/2/23 0023.
 */
public enum ErrorCode {

    NOT_FOUND_DATA("not_found_data", "暂时没有数据"),
    Phone_ALREADY("phone_already", "手机号码已注册会员"),
    Member_Already_Exist("member_already_exist", "用户已存在"),
    Member_Unbound_WX("member_unbound_wx", "该用户未绑定微信"),
    ILLEGAL_PARAMS("illegal_params", "非法的请求参数"),
    SERVER_ERROR("server_error", "请求出错"),
    Phone_Not_Exist("phone_not_exist", "该手机号码未注册"),
    Password_Illegal("password_illegal", "输入的密码不正确"),
    Phone_Illegal("phone_illegal", "该号码不是手机号码"),
    Verification_Code_Availability("verification_code_availability", "上一条验证码未失效，请稍后再试"),
    Verification_Code_Invalid("verification_code_invalid", "验证码已失效，请重新发送"),
    Verification_Already_Use("verification_already_use", "验证码已使用"),
    Verification_Code_Not_Exist("verification_code_not_exist", "验证码不存在，请重试"),
    Verification_Code_Error("verification_code_error", "验证码不正确"),
    EBean_NULL("ebean_null", "您还没有充值过e豆"),
    EBean_Insufficient("ebean_insufficient", "e豆余额不足"),
    Member_Identical("member_identical", "会员ID相同"),
    Token_Not_Exist("token_not_exist", "令牌不存在，请登录"),
    Token_Error("token_error", "令牌不正确，请重新登录"),
    Token_Invalid("token_invalid", "令牌已失效"),
    Send_Sms_Fail("send_sms_fail", "发送短信失败"),
    Upload_File_Null("upload_file_null", "上传文件为空"),
    Upload_File_Failure("upload_file_failure", "上传文件失败"),
    Member_Not_Exist("member_not_exist", "用户不存在"),
    Product_Already_Exist("product_already_exist", "商品已存在"),
    Product_Not_Exist("product_not_exist", "商品不存在"),
    Gen_Order_Fail("gen_order_fail", "生成订单失败"),
    Get_Open_Id_Fail("get_open_id_fail", "得到openId失败"),
    Pay_Fail("pay_fail", "支付失败，请稍后再试"),
    Pay_Notify_Fail("pay_notify_fail", "微信支付回调结果异常"),
    Already_Pay("already_pay", "已经支付过"),
    WX_Config_Info_Error("wx_config_info_error", "微信配置错误"),
    WX_Refresh_Fail("wx_refresh_fail", "微信刷新失败"),
    Get_AccessToken_Fail("get_accesstoken_fail", "得到微信AccessToken失败"),
    Get_WX_Card_Fail("get_wx_card_fail", "得到微信卡券失败"),
    Create_Conversion_Code_Fail("create_conversion_code_fail", "生成兑换码失败"),
    Conversion_Code_Exist("conversion_code_exist", "兑换码已存在"),
    Make_Coffees_Fail("make_coffees_fail", "获取咖啡失败"),
    Update_ProductConversionCode_State_Fail("update_product_conversion_code_state_fail", "更新兑换码状态失败"),
    Make_Coffees_Verify_Fail("make_coffees_verify_fail", "制作咖啡MD5校验失败"),

    Cart_Id_OR_EncryptCode_IS_NULL("cart_id_or_encrypt_code_is_null", "手机号码或验证码为空"),
    Phone_Or_Code_Null("phone_or_code_null", "手机或code为空"),
    Test_Member_Illegal("test_member_illegal", "该用户不是测试员"),
    Keyword_Illegal_Number("keyword_illegal_number", "你输入的不是数字"),
    CoffeesMachine_Code_Already_Exist("coffeesMachine_code_already_exist", "咖啡机编号已存在"),
    User_Not_Exist("user_not_exist", "用户不存在"),
    Mobile_Already_Exist("mobile_already_exist", "手机号码已存在"),
    Current_User_Not_Exist("current_user_not_exist", "当前用户已退出"),
    User_Role_Already("user_role_already", "此用户已属于该角色"),
    UserId_Is_Null("userId_is_null", "您还没有登录，不能进行相关操作"),
    Coffees_Machine_NOT_SYS_USER("coffees_machine_not_sys_user","该咖啡机器暂时没有商户管理"),
    Un_Authorized_Exception("un_authorized_exception", "该管理员没有相关权限进行此操作");
    private String code;
    private String message;


    ErrorCode(String code) {
        this.code = code;
    }

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
