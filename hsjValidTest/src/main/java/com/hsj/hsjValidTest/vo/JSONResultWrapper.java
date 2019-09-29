package com.hsj.hsjValidTest.vo;

import java.util.Map;

/**
 * 值对象返回
 *
 * @Description:TODO
 * @author:hsj qq:2356899074
 * @time:2017年11月14日 上午10:28:35
 */
public class JSONResultWrapper {

    private String message;
    private Object detail;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private JSONResultWrapper() {
        super();
    }

    private JSONResultWrapper(int resCode, String message, Map<String, String> map) {
        super();
        this.message = message;
        this.detail = map;
        this.code = resCode;
    }

    private JSONResultWrapper(int resCode, String message, Object object) {
        super();
        this.message = message;
        this.detail = object;
        this.code = resCode;
    }

    private JSONResultWrapper(int resCode, Map<String, String> map) {
        super();
        this.detail = map;
        this.code = resCode;
    }

    private JSONResultWrapper(int resCode, Object object) {
        super();
        this.detail = object;
        this.code = resCode;
    }

    private JSONResultWrapper(int resCode, String message) {
        super();
        this.message = message;
        this.code = resCode;
        this.detail = null;
    }

    public static JSONResultWrapper errorWithMap(int resCode, Map<String, String> map) {
        return new JSONResultWrapper(resCode, map);
    }

    public static JSONResultWrapper errorWithMessage(int resCode, String message) {
        return new JSONResultWrapper(resCode, message);
    }

    public static JSONResultWrapper errorWithMessageAndMap(int resCode, String message, Map<String, String> map) {
        return new JSONResultWrapper(resCode, message, map);
    }

    public static JSONResultWrapper okWithMessage(int resCode, String message) {
        return new JSONResultWrapper(resCode, message);
    }

    public static JSONResultWrapper okWithObject(int resCode, Object object) {
        return new JSONResultWrapper(resCode, object);
    }

    public static JSONResultWrapper errorWithMessageAndObject(int resCode, String message, Object object) {
        return new JSONResultWrapper(resCode, message, object);
    }

}
