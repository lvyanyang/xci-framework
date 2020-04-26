/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.exceptions;

/**
 * 异常基类
 * @author 吕艳阳
 */
public class TaskException extends Exception {
    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
    public TaskException(String message) {
        super(message);
    }
}