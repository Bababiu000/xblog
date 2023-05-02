package com.example.xblog.enums;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultType{
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "请登录！"),
    FORBIDDEN(403, "没有相关权限");

    /** 定义状态码 */
    private int code;

    /** 定义返回信息 */
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
