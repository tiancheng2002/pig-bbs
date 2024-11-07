package com.liang.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RespBeanEnum {

    SUCCESS(200, "success"),
    PARAM_ERROR(400,"参数错误"),
    ERROR(500, "服务器异常"),
    ACTION_ERROR(600, "操作失败"),

    Login_ERROR(400, "登录失败"),

    FORBIDDEN(403, "权限不足"),
    UNAUTHORIZED(401, ""),

    TOKEN_OVER(10010, "token已过期"),

    Captcha_OVER(10020, "验证码过期"),
    Captcha_ERROR(10021, "验证码错误"),

    USERNAME_PASSWORD(10030, "用户名或密码不正确"),
    PASSWORD(10031, "原密码错误"),
    USER_NO(10032, "用户不存在"),
    PASSWORD_NO_MATCH(10033,"两次密码不一致"),
    USER_STATUS_PROHIBIT(10036,"该账号已被封禁"),

    EMAIL_EXIST(10034,"该邮箱已注册"),

    QUESTION_NO_EXIST(10051,"该提问不存在"),

    TYPE_EXIST(10061,"该标签已存在"),

    TestACTION_ERROR(666, "演示环境，不允许操作");

    private final Integer code;

    private final String msg;

}
