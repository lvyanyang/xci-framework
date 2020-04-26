/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.component;

import com.xci.configuration.ApiProperties;
import com.xci.web.configuration.WebProperties;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.core.XCI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据请求类型处理404错误:
 * 如果是普通请求(浏览器),返回错误页面
 * 如果是Ajax请求,返回统一的Json错误
 */
@ApiIgnore
@RestController
@ConditionalOnProperty(name = "xci.api.enabled-error404-handle", havingValue = R.TRUE)
public class NotFoundController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    @Resource
    private ApiProperties apiProperties;
    @Autowired(required = false)
    private WebProperties webProperties;

    //处理404错误
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @RequestMapping(value = ERROR_PATH)
    public Object handleError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        boolean isApp = XCI.isApiRequest(request);
        if (isApp||webProperties==null) {
            return RestResult.fail(apiProperties.getError404Msg());
        }

        boolean isAjax = XCI.isAjaxRequest(request);
        if (isAjax) {
            return RestResult.fail(webProperties.getError404AjaxMessage());
        }

        // javax.servlet.error.status_code 该属性给出状态码，状态码可被存储，并在存储为 java.lang.Integer 数据类型后可被分析。
        // javax.servlet.error.exception_type 该属性给出异常类型的信息，异常类型可被存储，并在存储为 java.lang.Class 数据类型后可被分析。
        // javax.servlet.error.message 该属性给出确切错误消息的信息，信息可被存储，并在存储为 java.lang.String 数据类型后可被分析。
        // javax.servlet.error.request_uri 该属性给出有关 URL 调用 Servlet 的信息，信息可被存储，并在存储为 java.lang.String 数据类型后可被分析。
        // javax.servlet.error.exception 该属性给出异常产生的信息，信息可被存储，并在存储为 java.lang.Throwable 数据类型后可被分析。
        // javax.servlet.error.servlet_name 该属性给出 servlet 的名称，名称可被存储，并在存储为 java.lang.String 数据类型后可被分析。

        var url = R.Empty;
        ModelAndView view = new ModelAndView();
        var statusCode = (int) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 404) {
             url = webProperties.getError404Url();
        } else {
            url = webProperties.getError500Url();
        }
        String contextPath = request.getContextPath();
        view.addObject("url", request.getAttribute("javax.servlet.error.request_uri"));
        view.setViewName(XCI.forwardUrl(contextPath + url));
        return view;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}