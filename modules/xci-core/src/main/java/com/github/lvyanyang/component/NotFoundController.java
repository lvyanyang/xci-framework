/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.component;

import cn.hutool.core.bean.BeanUtil;
import com.github.lvyanyang.configuration.ApiProperties;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.web.configuration.WebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据请求类型处理404错误:
 * 如果是普通请求(浏览器),返回错误页面
 * 如果是Ajax请求,返回统一的Json错误
 */
@ApiIgnore
@Controller
@ConditionalOnProperty(name = "xci.api.enabled-notfound-error-controller", havingValue = R.TRUE)
public class NotFoundController extends BasicErrorController {
    @Resource private ApiProperties apiProperties;
    @Resource private WebProperties webProperties;

    @Autowired
    public NotFoundController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes, serverProperties.getError());
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        // javax.servlet.error.status_code 该属性给出状态码，状态码可被存储，并在存储为 java.lang.Integer 数据类型后可被分析。
        // javax.servlet.error.exception_type 该属性给出异常类型的信息，异常类型可被存储，并在存储为 java.lang.Class 数据类型后可被分析。
        // javax.servlet.error.message 该属性给出确切错误消息的信息，信息可被存储，并在存储为 java.lang.String 数据类型后可被分析。
        // javax.servlet.error.request_uri 该属性给出有关 URL 调用 Servlet 的信息，信息可被存储，并在存储为 java.lang.String 数据类型后可被分析。
        // javax.servlet.error.exception 该属性给出异常产生的信息，信息可被存储，并在存储为 java.lang.Throwable 数据类型后可被分析。
        // javax.servlet.error.servlet_name 该属性给出 servlet 的名称，名称可被存储，并在存储为 java.lang.String 数据类型后可被分析。

        HttpStatus status = getStatus(request);
        var statusCode = (int) request.getAttribute("javax.servlet.error.status_code");
        var url = statusCode == 404 ? webProperties.getError404Url() : webProperties.getError500Url();
        var model = new HashMap<String, Object>();
        model.put("code", statusCode);
        model.put("url", request.getAttribute("javax.servlet.error.request_uri"));
        return new ModelAndView(XCI.forwardUrl(request.getContextPath() + url), model, status);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        boolean isAjax = XCI.isAjaxRequest();
        var result = RestResult.fail(isAjax ? webProperties.getError404AjaxMessage() : apiProperties.getError404Msg());
        return new ResponseEntity<>(BeanUtil.beanToMap(result, false, true), status);
    }
}