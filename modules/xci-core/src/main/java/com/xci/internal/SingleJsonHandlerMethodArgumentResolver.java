/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.internal;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xci.annotation.SingleJson;
import com.xci.core.XCI;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 解析单个Json参数组件
 * @author 吕艳阳
 */
public class SingleJsonHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String JSONBODY_ATTRIBUTE = "JSON_REQUEST_BODY";

    // private ObjectMapper objectMapper;

    /**
     * 设置支持的方法参数类型
     * @param parameter 方法参数
     * @return 支持的类型
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 支持带@RequestJson注解的参数
        return parameter.hasParameterAnnotation(SingleJson.class);
    }

    /**
     * 参数解析，利用fastjson
     * 注意：非基本类型返回null会报空指针异常，要通过反射或者JSON工具类创建一个空对象
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String jsonBody = getRequestBody(webRequest);
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONUtil.parseObj(jsonBody);
        } catch (Exception ex) {
            throw new IllegalArgumentException(XCI.format("解析Json请求参数时发生错误,请输入正确的Json格式的参数字符串.错误内容:{}", ex.getMessage()));
        }
        // 根据@RequestJson注解value作为json解析的key
        SingleJson parameterAnnotation = parameter.getParameterAnnotation(SingleJson.class);
        //注解的value是JSON的key
        String key = parameterAnnotation.name();
        Object value = null;
        // 如果@RequestJson注解没有设置value，则取参数名FrameworkServlet作为json解析的key
        if (StringUtils.isNotEmpty(key)) {
            value = jsonObject.get(key);
            // 如果设置了value但是解析不到，报错
            if (value == null && parameterAnnotation.required()) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            }
        } else {
            // 注解为设置value则用参数名当做json的key
            key = parameter.getParameterName();
            value = jsonObject.get(key);
        }


        Class<?> parameterType = parameter.getParameterType();
        // 通过注解的value或者参数名解析，能拿到value进行解析
        if (value != null) {
            if (XCI.isBasicType(parameterType)) {
                return Convert.convert(parameterType,value) ;
            }
            return JSONUtil.toBean(value.toString(), parameterType);
        }

        // 解析不到则将整个json串解析为当前参数类型
        if (XCI.isBasicType(parameterType)) {
            if (parameterAnnotation.required()) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            } else {
                return null;
            }
        }

        Object result = parameterType.getDeclaredConstructor().newInstance();
        // 非基本类型，不允许解析所有字段，返回null
        if (!parameterAnnotation.parseAllFields()) {
            // 如果是必传参数抛异常
            if (parameterAnnotation.required()) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            }
            // 否则返回空对象
            return result;
        }
        // 非基本类型，允许解析，将外层属性解析
        result = JSONUtil.toBean(jsonObject.toString(), parameterType);
        // 如果非必要参数直接返回，否则如果没有一个属性有值则报错
        if (!parameterAnnotation.required()) {
            return result;
        } else {
            boolean haveValue = false;
            Field[] declaredFields = parameterType.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (field.get(result) != null) {
                    haveValue = true;
                    break;
                }
            }
            if (!haveValue) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            }
            return result;
        }
    }

    /**
     * 获取请求体JSON字符串
     */
    private String getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        // 有就直接获取
        String jsonBody = (String) webRequest.getAttribute(JSONBODY_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
        // 没有就从请求中读取
        if (jsonBody == null) {
            try {
                jsonBody = IOUtils.toString(servletRequest.getReader());
                webRequest.setAttribute(JSONBODY_ATTRIBUTE, jsonBody, NativeWebRequest.SCOPE_REQUEST);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonBody;
    }
}