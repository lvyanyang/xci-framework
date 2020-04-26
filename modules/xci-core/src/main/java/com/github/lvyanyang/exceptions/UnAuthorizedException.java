/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.exceptions;

/**
 * 未授权异常
 * @author 吕艳阳
 */
public class UnAuthorizedException extends RuntimeException {
    private String code;

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}