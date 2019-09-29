package com.hsj.hsjValidTest.vo;

public enum ResultStatus {
    SUCCESS(0, "请求成功"),
    TOKEN_EXPIRED(300001, "凭证过期"),
    NOT_FOUND(400001, "找不到页面"),
    Username_not_null(500002, "用户名不能为空"),
    ERROR(500001, "服务器出错"),
    exam_param_error(20101, "参数错误"),
    game_param_error(20201, "参数错误");

    private final int code;
    private final String reasonPhrase;

    ResultStatus(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    public int code() {
        return this.code;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public static ResultStatus valueOf(int code) {
        ResultStatus status = resolve(code);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        } else {
            return status;
        }
    }

    public static ResultStatus resolve(int code) {
        ResultStatus[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ResultStatus status = var1[var3];
            if (status.code == code) {
                return status;
            }
        }

        return null;
    }


}
