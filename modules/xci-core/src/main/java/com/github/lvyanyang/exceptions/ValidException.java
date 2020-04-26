/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.exceptions;

/**
 * 参数验证异常
 * @author 吕艳阳
 */
public class ValidException extends RuntimeException {
    private String msg;

    public ValidException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}