/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.lvyanyang.exceptions.AppException;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装一个操作结果对象
 */
public class RestResult<T> {
    private static final RestResult OK = new RestResult<>(0, null, null);
    private static final RestResult FAIL = new RestResult<>(-1, null, null);

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码", position = 1)
    private int code;

    /**
     * 消息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "消息", position = 2)
    private String msg;

    /**
     * 数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "数据", position = 3)
    private T data;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private boolean success;

    /**
     * 初始化操作结果对象
     * @param code 状态码
     * @param msg  消息
     * @param data 数据
     */
    private RestResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 初始化成功操作结果对象
     */
    public static RestResult ok() {
        return OK;
    }

    /**
     * 初始化成功操作结果对象
     */
    public static <R> RestResult<R> ok(R data) {
        return new RestResult<>(0, null, data);
    }

    /**
     * 初始化成功操作结果对象
     */
    public static <R> RestResult<R> ok(String msg, R data) {
        return new RestResult<>(0, msg, data);
    }

    /**
     * 初始化失败操作结果对象
     */
    public static RestResult fail() {
        return FAIL;
    }

    /**
     * 初始化失败操作结果对象
     */
    public static RestResult fail(String msg) {
        return new RestResult<>(-1, msg, null);
    }

    /**
     * 初始化失败操作结果对象
     */
    public static RestResult fail(int code, String msg) {
        return new RestResult<>(code, msg, null);
    }

    /**
     * 初始化失败操作结果对象
     */
    public static <R> RestResult<R> fail(String msg, R data) {
        return new RestResult<>(-1, msg, data);
    }

    /**
     * 初始化失败操作结果对象
     */
    public static <R> RestResult<R> fail(int code, String msg, R data) {
        return new RestResult<>(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return isOk();
    }

    @JsonIgnore
    public boolean isOk() {
        return code == 0;
    }

    @JsonIgnore
    public boolean isFail() {
        return code != 0;
    }

    /**
     * 如果状态码不为0,则抛出应用程序异常
     */
    public void ifFailThrow() {
        if (isFail()) {
            throw new AppException(msg);
        }
    }

    /**
     * 如果状态码不为0,则抛出应用程序异常
     * @param message 覆盖的错误消息
     */
    public void ifFailThrow(String message) {
        if (isFail()) {
            throw new AppException(message);
        }
    }

    /**
     * 合并
     * @param list 多个对象
     * @return 将多个对象合并为一个对象
     */
    public static RestResult merge(List<RestResult> list) {
        if (list == null || list.size() == 0) {
            return OK;
        }
        boolean ok = true;
        String msg;
        Map<String, Object> data = new HashMap<>();
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            RestResult current = list.get(i);
            if (current == null) continue;
            ok = ok && current.isOk();
            if (XCI.isNotBlank(current.getMsg())) {
                message.append(current.getMsg()).append(";");
            }
            data.put("d" + i, current.getData());
        }
        if (message.length() > 0) {
            msg = message.deleteCharAt(message.length() - 1).toString();
        } else {
            msg = message.toString();
        }
        return new RestResult<>(ok ? 0 : -1, msg, data);
    }
}