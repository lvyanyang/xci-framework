/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.internal;

import cn.hutool.core.io.IoUtil;
import com.xci.core.XCI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 解密请求参数
 * @author 吕艳阳
 */
public class DecryptHttpInputMessage implements HttpInputMessage {
    private HttpInputMessage inputMessage;

    public DecryptHttpInputMessage(HttpInputMessage inputMessage) {
        this.inputMessage = inputMessage;
    }

    @Override
    public InputStream getBody() throws IOException {
        String content = IoUtil.read(inputMessage.getBody(), StandardCharsets.UTF_8);
        String decryptBody = XCI.decrypt(content);
        return new ByteArrayInputStream(decryptBody.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public HttpHeaders getHeaders() {
        return inputMessage.getHeaders();
    }
}