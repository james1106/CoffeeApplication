package com.mk.coffee.common;

/**
 * 错误码对应数据库error_code表的Key值
 * Created by Administrator on 2017/2/23 0023.
 */
public enum ErrorCode {

    NOT_FOUND_DATA("not_found_data"),
    Phone_ALREADY("phone_already"),
    Member_Already_Exist("member_already_exist"),
    ILLEGAL_PARAMS("illegal_params"),
    SERVER_ERROR("server_error"),
    Phone_Not_Exist("phone_not_exist"),
    Password_Illegal("password_illegal","输入的密码不正确"),
    Phone_Illegal("phone_illegal"),
    Verification_Code_Availability("verification_code_availability"),
    Verification_Code_Invalid("verification_code_invalid"),
    Verification_Code_Not_Exist("verification_code_not_exist"),
    Verification_Code_Error("verification_code_error"),

    Token_Not_Exist("token_not_exist"),
    Token_Error("token_error"),
    Token_Invalid("token_invalid"),
    Send_Sms_Fail("send_sms_fail"),
    Upload_File_Null("upload_file_null"),
    Upload_File_Failure("upload_file_failure"),
    Member_Not_Exist("member_not_exist"),
    Product_Not_Exist("product_not_exist"),
    Gen_Order_Fail("gen_order_fail"),
    Get_Open_Id_Fail("get_open_id_fail"),
    Pay_Fail("pay_fail"),
    Pay_Notify_Fail("pay_notify_fail"),
    Already_Pay("already_pay"),
    WX_Config_Info_Error("wx_config_info_error"),
    WX_Refresh_Fail("wx_refresh_fail"),
    Get_AccessToken_Fail("Get_AccessToken_Fail"),
    Create_Conversion_Code_Fail("create_conversion_code_fail"),
    Conversion_Code_Exist("conversion_code_exist"),
    Make_Coffees_Fail("make_coffees_fail"),
    Update_ProductConversionCode_State_Fail("update_product_conversion_code_state_fail"),
    Make_Coffees_Verify_Fail("make_coffees_verify_fail"),

    Cart_Id_OR_EncryptCode_IS_NULL("cart_id_or_encrypt_code_is_null"),
    Phone_Or_Code_Null("phone_or_code_null"),
    User_Not_Exist("user_not_exist");

    /*
    not_found_data	暂时没有数据
	member_already	手机号码已注册
	illegal_params	非法的请求参数
	server_error	请求出错
	member_not_exist	该手机号码未注册
	phone_illegal	该号码不是手机号码


	verification_code_availability	上一条验证码未失效，请稍后再试
	verification_code_invalid	验证码已失效，请重新发送
	verification_code_not_exist	验证码不存在，请重试
	verification_code_error	验证码不正确

	token_not_exist	令牌不存在，请登录
	token_error	令牌不正确，请重新登录
	token_invalid	令牌已失效
	send_sms_fail	发送短信失败
    * */
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
