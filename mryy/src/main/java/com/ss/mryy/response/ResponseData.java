package com.ss.mryy.response;

/**
 * @Author:ljy.s
 * @Date:2023/4/18 - 04 - 18 - 14:59
 */

/**
 * 封装返回值类型
 */
public class ResponseData {

    private String code;

    private String msg;

    private Object data;

    public ResponseData() {
    }

    public ResponseData(ResponseCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

    public ResponseData(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public ResponseData(String code, String massage, Object data) {
        this.code = code;
        this.msg = massage;
        this.data = data;
    }

    public ResponseData(String code, String massage) {
        this.code = code;
        this.msg = massage;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
