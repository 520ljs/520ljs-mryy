package com.ss.mryy.response;

/**
 * @Author:ljy.s
 * @Date:2023/4/18 - 04 - 18 - 15:08
 */

/**
 * 状态码和对应的状态码消息
 */
public enum ResponseCode {

    SUCCESS("200", "success"),

    /**
     * 9000 - 9999 错误信息
     */
    FAIL("9000", "未知错误"),

    FAIL_DAO("9001", "数据库断开连接"),

    ERROR_1("9002", "手机号为空"),

    ERROR_2("9003", "密码为空"),

    ERROR_3("9004", "用户名为空"),

    ERROR_4("9005", "用户名已经存在"),

    ERROR_5("9006", "账号密码不匹配"),

    ERROR_6("9007", "参数为空");

    private String code;

    private String msg;

    ResponseCode() {
    }

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
