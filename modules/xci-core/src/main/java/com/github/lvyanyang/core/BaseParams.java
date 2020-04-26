/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;

public class BaseParams {
    /**
     * 参数集合
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @ApiModelProperty(hidden = true)
    private HashMap<String, Object> params;

    public HashMap<String, Object> getParams() {
        if (params == null) params = new HashMap<>();
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }
}
