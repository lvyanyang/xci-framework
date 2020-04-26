/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.exceptions;

import com.github.lvyanyang.core.RestResult;

/**
 * 异常基类
 * @author 吕艳阳
 */
public class AppException extends RuntimeException {
    /**
     * 错误码,默认-1
     */
    private int code = -1;
    /**
     * 参数对象
     */
    private Object args;

    public AppException(RestResult result) {
        super(result.getMsg());
        this.code = result.getCode();
        this.args = result.getData();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(int code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(int code, String message, Object args) {
        super(message);
        this.code = code;
        this.args = args;
    }

    public AppException(int code, String message, Object args, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.args = args;
    }

    public AppException(String message, Object args) {
        super(message);
        this.args = args;
    }

    public AppException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public AppException(Throwable throwable) {
        super(throwable);
    }

    public int getCode() {
        return code;
    }

    public Object getArgs() {
        return args;
    }
}