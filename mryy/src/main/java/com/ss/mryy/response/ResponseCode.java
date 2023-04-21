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

    FAIL("500", "未知错误"),

    FAIL_DAO("501", "数据库断开连接");
    private String code;

    private String info;

    ResponseCode() {
    }

    ResponseCode(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
