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

    private String info;

    private Object data;

    public ResponseData() {
    }

    public ResponseData(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public ResponseData(ResponseCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.info = responseCode.getInfo();
        this.data = data;
    }

    public ResponseData(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.info = responseCode.getInfo();
    }

    public ResponseData(String code, String info) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
