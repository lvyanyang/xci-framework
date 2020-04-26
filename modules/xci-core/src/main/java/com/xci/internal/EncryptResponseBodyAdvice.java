/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xci.annotation.ApiEncrypt;
import com.xci.core.XCI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

/**
 * 请求响应加密处理类
 * 只对 @ResponseBody 参数有效
 */
@Slf4j
@ControllerAdvice
//@ConditionalOnProperty(prefix = "xci.api", name = "response-body-encrypt" , havingValue = "true")
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    private ObjectMapper mapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethodAnnotation(ApiEncrypt.class) != null ||
                returnType.getContainingClass().getAnnotation(ApiEncrypt.class) != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) return null;
        try {
            response.getHeaders().add("encrypt", "1");
            String srcData = mapper.writeValueAsString(body);
            return XCI.encrypt(srcData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}