package com.minip.tx.utils;

public enum ErrorTypeEnum {
    //枚举类实例
    INVALID_PARAMS(1103, "参数为空或不合法"),
    DB_OPERATION_FAILED(1104, "数据库操作失败"),
    REDIS_OP_FAILED(1105, "Redis操作失败"),
    ACCOUNT_NOT_ENOUGH(1106, "账户余额不足"),
    NOT_ATTENDED(1201, "未参赛，无法提交"),
    DECIMAL_ERROR(1202, "金额算术出错");



    private int code;
    private String cause;

    public int getCode() {
        return code;
    }

    public String getCause() {
        return cause;
    }

    private ErrorTypeEnum(int code, String cause){
        this.code = code;
        this.cause = cause;
    }

}
