/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.component;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码服务
 * @author 吕艳阳
 */
@Slf4j
@Component
public class CaptchaService {
    @Resource private Producer captchaProducer;
    @Resource private Producer captchaProducerMath;
    @Resource private Cache captchaCache;//用户验证码缓存
    private static CaptchaService me;

    public static CaptchaService me() {
        return me;
    }

    @PostConstruct
    private void init() {
        CaptchaService.me = this;
    }

    /**
     * 生成验证码
     * @param cacheKey 缓存键
     * @param type     验证码类型:math,char
     */
    public void buildNew(String cacheKey, String type, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String capStr, code;
            BufferedImage bi;
            if ("math".equals(type)) {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = captchaProducerMath.createImage(capStr);
            } else {
                capStr = code = captchaProducer.createText();
                bi = captchaProducer.createImage(capStr);
            }

            captchaCache.put(cacheKey, code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}