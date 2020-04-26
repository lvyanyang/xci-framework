/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelBaseEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import cn.afterturn.easypoi.exception.excel.ExcelExportException;
import cn.afterturn.easypoi.exception.excel.enums.ExcelExportEnum;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lvyanyang.exceptions.*;
import com.github.lvyanyang.model.*;
import com.github.promeg.pinyinhelper.Pinyin;
import com.github.lvyanyang.annotation.AllowAnonymous;
import com.github.lvyanyang.annotation.Authorize;
import com.github.lvyanyang.component.SpringBeanFactory;
import com.github.lvyanyang.internal.ServletUtil;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.*;
import io.swagger.annotations.ApiModelProperty;
import net.sf.ehcache.Element;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cache.Cache;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.Key;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 常用功能操作工具类
 * @author 吕艳阳
 */
public class XCI {
    private static final Logger log = LoggerFactory.getLogger(XCI.class);
    private static final Snowflake snowflake = new Snowflake(1, 1);
    private static final SecretKeySpec secretKey = new SecretKeySpec(R.AES_SECRET_KEY.getBytes(StandardCharsets.UTF_8), R.AES_METHOD);
    private static final IvParameterSpec ivSpec = new IvParameterSpec(R.AES_Padding_IV.getBytes(StandardCharsets.UTF_8));
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
    private static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";
    private static char[][] HTML_CHARS;
    private static ObjectMapper objectMapper;
    private static Whitelist whitelist;
    private static Set<Class> basicClassSet;

    // region String

    /**
     * 清除Html不安全标签
     */
    public static String cleanHtml(String content) {
        if (isBlank(content)) {
            return content;
        }
        if (whitelist == null) {
            whitelist = Whitelist.relaxed();
            //白名单 增加可信标签到白名单
            whitelist.addTags("embed", "object", "param", "span", "div");

            //增加可信属性
            whitelist.addAttributes(":all", "style", "class", "id", "tableName", "src");
            whitelist.addAttributes("object", "width", "height", "classid", "codebase");
            whitelist.addAttributes("param", "tableName", "value");
            whitelist.addAttributes("table", "summary", "width", "cellpadding", "cellspacing", "border", "bordercolor");
            whitelist.addAttributes("embed", "src", "quality", "width", "height", "allowFullScreen",
                    "allowScriptAccess", "flashvars", "tableName", "type", "pluginspage");
        }
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }

    /**
     * 清除Xss不安全内容
     * <p>清除所有HTML标签，但是不删除标签内的内容</p>
     */
    public static String cleanXss(String content) {
        return content.replaceAll(RE_HTML_MARK, R.Empty);
    }

    /**
     * HTML字符转义
     * @param text 被编码的文本
     * @return 编码后的字符
     */
    private static String encodeHtml(String text) {
        if (HTML_CHARS == null) {
            HTML_CHARS = new char[64][];
            for (int i = 0; i < 64; i++) {
                HTML_CHARS[i] = new char[]{(char) i};
            }

            // special HTML characters
            HTML_CHARS['\''] = "&#039;".toCharArray(); // 单引号
            HTML_CHARS['"'] = "&#34;".toCharArray(); // 单引号
            HTML_CHARS['&'] = "&#38;".toCharArray(); // &符
            HTML_CHARS['<'] = "&#60;".toCharArray(); // 小于号
            HTML_CHARS['>'] = "&#62;".toCharArray(); // 大于号
        }
        int len;
        if ((text == null) || ((len = text.length()) == 0)) {
            return StringUtils.EMPTY;
        }
        StringBuilder buffer = new StringBuilder(len + (len >> 2));
        char c;
        for (int i = 0; i < len; i++) {
            c = text.charAt(i);
            if (c < 64) {
                buffer.append(HTML_CHARS[c]);
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    /**
     * 还原被转义的HTML字符
     * @param text 被转义的内容
     * @return 解码后的字符串
     */
    public static String decodeHtml(String text) {
        if (StringUtils.isEmpty(text)) {
            return text;
        }

        StringBuilder tmp = new StringBuilder(text.length());
        int lastPos = 0, pos;
        char ch;
        while (lastPos < text.length()) {
            pos = text.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (text.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(text.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(text.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(text.substring(lastPos));
                    lastPos = text.length();
                } else {
                    tmp.append(text, lastPos, pos);
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 获取所给中文的每个汉字首字母组成首字母字符串
     * @param chinese 汉字字符串
     * @return 首字母字符串
     */
    public static String getSpell(String chinese) {
        return getSpell(chinese, false);
    }

    /**
     * 获取所给中文的每个汉字字母字符串
     * @param chinese 汉字字符串
     * @param isFull  是否全拼
     * @return 首字母字符串
     */
    public static String getSpell(String chinese, boolean isFull) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chinese.length(); i++) {
            char item = chinese.charAt(i);
            String py = Pinyin.toPinyin(item);
            if (!isFull) {
                stringBuilder.append(py, 0, 1);
            } else {
                stringBuilder.append(py);
            }
        }
        return stringBuilder.toString().toLowerCase();
    }

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value) {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") =》 this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params   参数值
     * @return 格式化后的文本
     */
    public static String format(CharSequence template, Object... params) {
        if (null == template) {
            return null;
        }
        if (params == null || params.length == 0 || StrUtil.isBlank(template)) {
            return template.toString();
        }
        return StrFormatter.format(template.toString(), params);
    }

    /**
     * 有序的格式化文本，使用{number}做为占位符<br>
     * 例：<br>
     * 通常使用：format("this is {0} for {1}", "a", "b") =》 this is a for b<br>
     * @param pattern   文本格式
     * @param arguments 参数
     * @return 格式化后的文本
     */
    public static String formatIndexed(CharSequence pattern, Object... arguments) {
        return MessageFormat.format(pattern.toString(), arguments);
    }

    /**
     * 格式化文本，使用 {varName} 占位<br>
     * map = {a: "aValue", b: "bValue"} format("{a} and {b}", map) ---=》 aValue and bValue
     * @param template 文本模板，被替换的部分用 {key} 表示
     * @param map      参数值对
     * @return 格式化后的文本
     */
    public static String format(CharSequence template, Map<?, ?> map) {
        return StrUtil.format(template, map);
    }

    /**
     * 验证 sortDirField by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(R.SQL_PATTERN);
    }

    /**
     * 字符串中是否包含指定的子字符串
     */
    public static boolean stringContains(String source, String sub) {
        return source.toLowerCase().contains(sub.toLowerCase());
    }

    /**
     * 切分字符串
     * @param str 被切分的字符串
     * @return 切分后的数组
     */
    public static List<String> splitToList(CharSequence str) {
        return splitToList(str, ',');
    }

    /**
     * 切分字符串
     * @param str 被切分的字符串
     * @return 切分后的数组
     */
    public static List<String> splitToList(CharSequence str, char separator) {
        return StrUtil.split(str, separator, true, true);
    }

    /**
     * 切分字符串
     * @param str 被切分的字符串
     * @return 切分后的数组
     */
    public static String[] splitToArrayOrNull(CharSequence str) {
        if (isBlank(str)) {
            return null;
        }
        return StrSpliter.splitToArray(str.toString(), ',', -1, true, true);
    }

    /**
     * 切分字符串
     * @param str 被切分的字符串
     * @return 切分后的数组
     */
    public static String[] splitToArray(CharSequence str) {
        return splitToArray(str, ',');
    }

    /**
     * 切分字符串
     * @param str       被切分的字符串
     * @param separator 分隔符字符
     * @return 切分后的数组
     */
    public static String[] splitToArray(CharSequence str, char separator) {
        if (null == str) {
            return new String[]{};
        }
        return StrSpliter.splitToArray(str.toString(), separator, -1, true, true);
    }

    /**
     * 提取html中的文字
     */
    public static String htmlToText(String html) {
        if (isNotBlank(html)) {
            return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        }
        return "";
    }

    // /**
    //  * An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!
    //  * <p>
    //  * 这种格式的字符转换为emoji表情
    //  */
    // public static String replaceEmoji(String value) {
    //     return EmojiParser.parseToUnicode(value);
    // }

    /**
     * 路径清理
     * 规则:
     * 1.如果不是以/开通的调整为以/开头
     * 2.如果是以/结尾的调整为去掉/结尾
     * @param pathItem 路径字符串
     */
    public static String pathOfClean(String pathItem) {
        return pathOfCore(pathItem);
    }

    /**
     * 路径拼接
     * @param more 路径字符串
     */
    public static String pathOf(String... more) {
        StringBuilder sb = new StringBuilder();
        for (String item : more) {
            if (isBlank(item)) continue;
            sb.append(pathOfCore(item));
        }
        return sb.toString();
    }

    private static String pathOfCore(String pathItem) {
        if (!pathItem.startsWith("/")) {
            pathItem = "/" + pathItem;
        }
        if (pathItem.endsWith("/")) {
            pathItem = pathItem.substring(0, pathItem.length() - 1);
        }
        return pathItem;
    }

    // endregion

    // region isEmpty

    /**
     * 如果对象是字符串是否为空串空的定义如下:<br>
     * 1、为null <br>
     * 2、为""<br>
     * @param obj 对象
     * @return 如果为字符串是否为空串
     * @since 3.3.0
     */
    public static boolean isEmpty(Object obj) {
        return ObjectUtils.isEmpty(obj);
    }

    /**
     * 字符串是否为非空白 空白的定义如下： <br>
     * 1、不为null <br>
     * 2、不为""<br>
     * @param obj 被检测的字符串对象
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Object obj) {
        return !ObjectUtils.isEmpty(obj);
    }

    /**
     * 如果对象是字符串是否为空白，空白的定义如下： <br>
     * 1、为null <br>
     * 2、为不可见字符（如空格）<br>
     * 3、""<br>
     * @param obj 字符串对象
     * @return 如果为字符串是否为空串
     * @since 3.3.0
     */
    public static boolean isBlank(Object obj) {
        if (null == obj) {
            return true;
        } else if (obj instanceof CharSequence) {
            return StrUtil.isBlank((CharSequence) obj);
        }
        return false;
    }

    /**
     * 字符串是否为非空白 空白的定义如下： <br>
     * 1、不为null <br>
     * 2、不为不可见字符（如空格）<br>
     * 3、不为""<br>
     * @param obj 被检测的字符串对象
     * @return 是否为非空
     */
    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    // endregion

    // region 加密

    /**
     * 十六进制字符串转为字节数组
     * @param str 字符串
     */
    public static byte[] fromHex(String str) {
        byte[] digest = new byte[str.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = str.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }

    /**
     * 字节数组转为十六进制字符串
     */
    public static String toHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte value : bytes) {
            String plainText = Integer.toHexString(0xff & value);
            if (plainText.length() < 2) plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }

    /**
     * 将字节码转换成base64文本
     * @param bytes 字节数组
     */
    public static String toBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 将文本转换成base64文本
     * @param content 待转文本
     */
    public static String toBase64Str(String content) {
        return Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 将base64转换成字节码
     * @param base64 编码字符串
     */
    public static byte[] fromBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    /**
     * 将base64转换成文本
     * @param base64 编码字符串
     */
    public static String fromBase64Str(String base64) {
        return new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8);
    }

    /**
     * 字符串加密
     * @param str 待加密的字符串
     */
    public static String encrypt(String str) {
        if (isBlank(str)) {
            return R.Empty;
        }
        try {
            Cipher cipher = Cipher.getInstance(R.AES_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            return toBase64(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new AppException("数据加密出现问题");
        }
    }

    /**
     * 字符串解密
     * @param str 待解密的字符串
     */
    public static String decrypt(String str) {
        if (isBlank(str)) {
            return R.Empty;
        }
        try {
            Cipher cipher = Cipher.getInstance(R.AES_INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            return new String(cipher.doFinal(fromBase64(str)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new AppException("数据解密出现问题");
        }
    }

    // endregion

    //region Other

    /**
     * 开始计时监控
     * @return 返回当前时刻的毫秒数
     */
    public static long startWatch() {
        return System.currentTimeMillis();
    }

    /**
     * 停止计时监控
     * @param startTime 开始时间
     * @return 返回执行的毫秒数
     */
    public static long stopWatch(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * 基本数据类型直接返回
     */
    @SuppressWarnings("rawtypes")
    public static boolean isBasicType(Class clazz) {
        if (basicClassSet == null) {
            basicClassSet = new HashSet<>();
            basicClassSet.add(String.class);
            basicClassSet.add(Integer.class);
            basicClassSet.add(Long.class);
            basicClassSet.add(Short.class);
            basicClassSet.add(Float.class);
            basicClassSet.add(Double.class);
            basicClassSet.add(Boolean.class);
            basicClassSet.add(Character.class);
        }
        return basicClassSet.contains(clazz);
    }

    /**
     * 判断文件是否是图片类型
     */
    public static boolean isImage(InputStream imageFile) {
        try {
            Image img = ImageIO.read(imageFile);
            return img != null && img.getWidth(null) > 0 && img.getHeight(null) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取方法执行结果字符串
     * @param result 执行结果(如不为空json序列化)
     */
    public static String getExecuteResultString(Object result) {
        String resultStr = R.Empty;
        if (result != null) {
            if (XCI.isBasicType(result.getClass())) {
                resultStr = result.toString();
            } else {
                resultStr = XCI.toJsonString(result, true);
            }
        }
        return resultStr;
    }

    /**
     * 获取方法执行参数字符串
     * @param method        方法
     * @param args          参数数组
     * @param excludeParams 排除的参数名称数组
     */
    public static String getExecuteParamString(Method method, Object[] args, String[] excludeParams) {
        if (args == null || args.length == 0) return R.Empty;
        Map<String, Object> params = new HashMap<>();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (paramNames == null || paramNames.length == 0) return R.Empty;
        for (int i = 0; i < args.length; i++) {
            String name = paramNames[i];
            Object o = args[i];
            if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                continue;
            }
            if (!ObjectUtils.isEmpty(excludeParams) && ArrayUtil.contains(excludeParams, name)) {
                continue;
            }
            params.put(name, o);
        }
        return XCI.toJsonString(params, true);
    }
    //endregion

    // region 逻辑

    /**
     * 对象为空或者空字符串时执行语句
     * @param obj    测试对象
     * @param action 回调
     */
    public static void ifBlankAction(Object obj, IAction action) {
        if (isBlank(obj)) {
            action.call();
        }
    }

    /**
     * 对象不为空时执行语句
     * @param obj    测试对象
     * @param action 回调
     */
    public static void IfNotBlankAction(Object obj, IAction action) {
        if (isNotBlank(obj)) {
            action.call();
        }
    }

    /**
     * 对象为空时执行语句
     * @param obj    测试对象
     * @param action 回调
     */
    public static void ifNullAction(Object obj, IAction action) {
        if (obj == null) {
            action.call();
        }
    }

    /**
     * 对象为空时执行语句
     * @param obj    测试对象
     * @param action 回调
     */
    public static void ifNotNullAction(Object obj, IAction action) {
        if (obj != null) {
            action.call();
        }
    }

    /**
     * 测试条件为true时执行语句
     * @param test       测试条件
     * @param trueAction true回调
     */
    public static void ifTrueAction(boolean test, IAction trueAction) {
        if (test) {
            trueAction.call();
        }
    }

    /**
     * 测试条件为false时执行语句
     * @param test        测试条件
     * @param falseAction false回调
     */
    public static void ifFalseAction(boolean test, IAction falseAction) {
        if (!test) {
            falseAction.call();
        }
    }

    /**
     * 根据测试条件执行语句
     * @param test        测试条件
     * @param trueAction  true回调
     * @param falseAction false回调
     */
    public static void ifAction(boolean test, IAction trueAction, IAction falseAction) {
        if (test) {
            trueAction.call();
        } else {
            falseAction.call();
        }
    }

    // endregion

    // region BuildID

    /**
     * 生成普通 Guid
     */
    public static String guid() {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 创建MongoDB ID生成策略实现
     */
    public static String objectId() {
        return ObjectId.next();
    }

    /**
     * 获取新唯一编号（18位数值）
     */
    public static long nextId() {
        return snowflake.nextId();
    }

    /**
     * 获取新唯一编号（18位数值）字符串
     */
    public static String nextIdStr() {
        return String.valueOf(snowflake.nextId());
    }

    // endregion

    // region 异常处理助手

    /**
     * 如果测试条件为 true,则抛出应用程序异常
     * @param test   测试条件
     * @param result 操作结果对象
     */
    public static void ifTrueThrow(boolean test, Supplier<RestResult> result) {
        if (test) {
            throw new AppException(result.get());
        }
    }

    /**
     * 如果测试条件为 true,则抛出应用程序异常
     * @param test 测试条件
     * @param msg  错误消息
     */
    public static void ifTrueThrow(boolean test, String msg) {
        if (test) {
            throw new AppException(-1, msg);
        }
    }

    /**
     * 如果测试条件为 false,则抛出应用程序异常
     * @param test   测试条件
     * @param result 操作结果对象
     */
    public static void ifFalseThrow(boolean test, Supplier<RestResult> result) {
        if (!test) {
            throw new AppException(result.get());
        }
    }

    /**
     * 如果测试条件为 false,则抛出应用程序异常
     * @param test 测试条件
     * @param msg  错误消息
     */
    public static void ifFalseThrow(boolean test, String msg) {
        if (!test) {
            throw new AppException(-1, msg);
        }
    }

    /**
     * 如果测试对象为null,则抛出应用程序异常
     * @param obj    测试对象
     * @param result 操作结果对象
     */
    public static void ifNullThrow(Object obj, Supplier<RestResult> result) {
        ifTrueThrow(obj == null, result);
    }

    /**
     * 如果测试对象为null,则抛出应用程序异常
     * @param obj    测试对象
     * @param result 操作结果对象
     */
    public static void ifNotNullThrow(Object obj, Supplier<RestResult> result) {
        ifTrueThrow(obj != null, result);
    }


    /**
     * 如果测试对象为null或者内容为空,则抛出应用程序异常
     * @param obj    测试对象
     * @param result 操作结果对象
     */
    public static void ifBlankThrow(Object obj, Supplier<RestResult> result) {
        ifTrueThrow(obj == null || StrUtil.isBlank(obj.toString()), result);
    }

    /**
     * 如果测试对象为null或者内容为空,则抛出应用程序异常
     * @param obj    测试对象
     * @param result 操作结果对象
     */
    public static void ifNotBlankThrow(Object obj, Supplier<RestResult> result) {
        ifTrueThrow(obj != null && !StrUtil.isBlank(obj.toString()), result);
    }

    /**
     * 抛出应用程序异常
     * @param result 操作结果对象
     */
    public static void appThrow(RestResult result) {
        throw new AppException(result);
    }

    /**
     * 抛出应用程序异常
     * @param msg 错误消息
     */
    public static void appThrow(String msg) {
        throw new AppException(msg);
    }

    public static String getRootErrorMseeage(Exception e) {
        Throwable root = ExceptionUtils.getRootCause(e);
        root = (root == null ? e : root);
        if (root == null) {
            return R.Empty;
        }
        String msg = root.getMessage();
        if (msg == null) {
            return "null";
        }
        return StringUtils.defaultString(msg);
    }

    /**
     * 获取exception的详细错误信息。
     */
    public static String getExceptionDetails(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }

    /**
     * 接口框架默认异常处理
     * @param e                   异常对象
     * @param unknownErrorHandler 未知异常处理
     * @return 处理后的结果
     */
    public static RestResult defaultApiExceptionHandler(Exception e, Function<Exception,String> unknownErrorHandler) {
        String msg = e.getMessage();
        if (e.getCause() != null) {
            msg = e.getCause().getMessage();
        }
        if (e instanceof MaxUploadSizeExceededException) {
            //文件上传异常
            msg = "文件大小不应大于" + ((MaxUploadSizeExceededException) e).getMaxUploadSize() / 1000 + "kb";
        } else if (e instanceof ValidException) {
            //参数校验异常
            msg = ((ValidException) e).getMsg();
        } else if (e instanceof org.apache.ibatis.javassist.NotFoundException || e instanceof NotFoundException) {
            //数据未找到异常
            msg = e.getMessage();
        } else if (e instanceof AppException || e instanceof TaskException) {
            //框架错误异常
            msg = e.getMessage();
        } else if (e instanceof JwtException) {
            //jwt解析错误异常
            msg = e.getMessage();
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            //请求方法不支持异常
            msg = "不支持' " + ((HttpRequestMethodNotSupportedException) e).getMethod() + "'请求";
        } else if (e instanceof UnAuthorizedException) {
            //未授权异常
            UnAuthorizedException ex = ((UnAuthorizedException) e);
            msg = ex.getCode() + e.getMessage();
        } else if (unknownErrorHandler != null) {
            msg = unknownErrorHandler.apply(e);
        }
        return RestResult.fail(msg);
    }

    /**
     * 获取Web异常错误页
     * @param e 异常对象
     * @param errorUrl 错误页面视图路径
     * @param msg 错误消息，如果为null，返回e对象的消息
     * @return 返回Web视图对象
     */
    public static ModelAndView getExceptionView(Throwable e,String errorUrl,String msg){
        var request = XCI.getRequest();
        String contextPath = request.getContextPath();
        ModelAndView view = new ModelAndView();
        view.addObject("msg", msg);
        view.addObject("exception", e);
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        view.addObject("details", stringWriter.toString());
        view.addObject("params", XCI.toJsonString(XCI.getRequestParamMap()));
        view.addObject("method", request.getMethod());
        view.addObject("url", request.getRequestURI());
        view.setViewName(XCI.forwardUrl(contextPath + errorUrl));
        return view;
    }

    // endregion

    // region File

    /**
     * 构造根目录文件对象
     * @param path 项目根目录
     */
    public static File buildRootFile(String path) {
        if (isBlank(path)) {
            return new File(R.Empty);
        }
        return new File(new File(R.Empty).getAbsolutePath() + path);
    }

    /**
     * 构造根目录文件对象
     * @param path 项目根目录
     */
    public static String buildRootFilePath(String path) {
        if (isBlank(path)) {
            return new File(R.Empty).toString();
        }
        return new File(R.Empty).getAbsolutePath() + path;
    }

    /**
     * 读取文件检测 md5
     * @param rootPath 应用根路径
     * @param md5      配置文件 Md5值,如果配置文件的 md5值与指定的 md5相同则返回空字符串
     * @return 读取成功返回文件内容, 否则返回空字符串
     */
    public static String readFileByMd5(String rootPath, String md5) {
        return readFileByMd5(buildRootFile(rootPath), md5);
    }

    /**
     * 读取文件检测 md5
     * @param file 路径
     * @param md5  配置文件 Md5值,如果配置文件的 md5值与指定的 md5相同则返回空字符串
     * @return 读取成功返回文件内容, 否则返回空字符串
     */
    public static String readFileByMd5(File file, String md5) {
        if (file.exists()) {
            String setting = FileUtil.readUtf8String(file);
            if (isNotBlank(md5) && org.apache.commons.codec.digest.DigestUtils.md5Hex(setting).equalsIgnoreCase(md5)) {
                return R.Empty;
            }
            return setting;
        }
        return R.Empty;
    }

    /**
     * 写入文件
     * @param rootPath 应用根路径
     * @param content  写入的内容
     * @return 返回写入的文件
     */
    public static File writeFileByUtf8(String rootPath, String content) {
        File file = buildRootFile(rootPath);
        FileUtil.mkParentDirs(file);
        return FileUtil.writeUtf8String(content, file);
    }

    /**
     * 删除文件
     * @param rootPath 应用根路径
     * @return 删除成功返回 true
     */
    public static boolean deleteFile(String rootPath) {
        File file = buildRootFile(rootPath);
        return FileUtil.del(file);
    }


    /**
     * 获得文件的扩展名，扩展名不带“.”
     * @param fileName 文件名
     * @return 扩展名
     */
    public static String getFileExtName(String fileName) {
        return FileUtil.extName(fileName);
    }

    /**
     * 返回主文件名
     * @param fileName 完整文件名
     * @return 主文件名
     */
    public static String getFileName(String fileName) {
        return FileUtil.mainName(fileName);
    }


    // endregion

    // region Date

    /**
     * 获取当前日期
     * @return 返回当前日期对象
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 格式化日期(默认格式yyyy-MM-dd)
     * @param date 指定的时间
     * @return 返回日期格式化后的字符串
     */
    public static String formatDate(Date date) {
        return formatDate(date, R.DEFAULT_DATE_PATTERN);
    }

    /**
     * 格式化日期字符串(默认格式 yyyy-MM-dd HH:mm)
     * @param date 指定的时间
     * @return 返回日期格式化后的字符串
     */
    public static String formatDateHasMinute(Date date) {
        return formatDate(date, R.DEFAULT_DATE_MS_PATTERN);
    }

    /**
     * 格式化日期(默认格式 yyyy-MM-dd HH:mm:ss)
     * @param date 指定的时间
     * @return 返回日期格式化后的字符串
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, R.DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 获取格式化的时间字符串(默认格式 HH:mm:ss)
     * @param date 指定的时间
     * @return 返回日期格式化后的字符串
     */
    public static String formatTime(Date date) {
        return formatDate(date, R.DEFAULT_TIME_PATTERN);
    }

    /**
     * 格式化日期
     * @param date   指定的时间
     * @param format 格式字符串
     * @return 返回日期格式化后的字符串
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return R.Empty;
        }
        return DateUtil.format(date, format);
    }

    /**
     * 日期对象转为本地日期时间对象
     * @param date 日期对象
     * @return 本地日期时间对象
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 日期对象转为本地日期对象
     * @param date 日期对象
     * @return 本地日期对象
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * 日期对象转为本地时间对象
     * @param date 日期对象
     * @return 本地时间对象
     */
    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }

    /**
     * 本地日期对象转为日期对象
     * @param localDate 本地日期对象
     * @return 日期对象
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 本地日期时间对象转为日期对象
     * @param localDateTime 本地日期时间对象
     * @return 日期对象
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 本地日期和本地时间对象转为日期对象
     * @param localDate 本地日期对象
     * @param localTime 本地时间对象
     * @return 日期对象
     */
    public static Date localDateTimeToDate(LocalDate localDate, LocalTime localTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDateTime.of(localDate, localTime).atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 测试检测时间是否在指定的开始时间和结束时间之内,精度到毫秒单位,如果不在范围内返回true
     * @param checkedDate 检测时间
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @return 如果在开始时间和结束时间范围内返回false, 否则返回true
     */
    public static boolean isExpired(@NotNull Date checkedDate, Date startTime, Date endTime) {
        long checkedTime = checkedDate.getTime();
        return (startTime != null && startTime.getTime() >= checkedTime)
                || (endTime != null && endTime.getTime() <= checkedTime);
    }

    /**
     * 测试时间是否在指定的结束时间之前,精度到毫秒单位,如果不在范围内返回true
     * @param checkedDate 检测时间
     * @param endTime     结束时间
     * @return 如果结束时间之前返回false, 否则返回true
     */
    public static boolean isExpired(@NotNull Date checkedDate, @NotNull Date endTime) {
        long checkedTime = checkedDate.getTime();
        return endTime.getTime() <= checkedTime;
    }

    // endregion

    // region Collection

    /**
     * 更新列表元素
     * @param list      列表对象
     * @param newItem   新元素
     * @param predicate 更新条件
     * @param <E>       对象类型
     * @return 更新成功返回true
     */
    public static <E> boolean updateListItem(List<E> list, E newItem, Function<E, Boolean> predicate) {
        int index = listIndexOf(list, predicate);
        if (index > -1) {
            list.set(index, newItem);
            return true;
        }
        return false;
    }

    /**
     * 根据条件获取元素索引号
     */
    public static <E> int listIndexOf(List<E> list, Function<E, Boolean> predicate) {
        if (list != null && predicate != null) {
            for (int i = 0; i < list.size(); ++i) {
                E item = list.get(i);
                if (predicate.apply(item)) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 在一个一维数组中搜索指定对象，并返回其首个匹配项的索引。
     * @param array 在一个一维数组中搜索指定对象，并返回其首个匹配项的索引。
     * @param value 要在 array 中查找的对象。
     * @param <T>   数组元素的类型。
     * @return 如果在整个 array 中找到 value 的第一个匹配项，则为该项的从零开始的索引；否则为 -1。
     */
    public static <T> int indexOf(T[] array, T value) {
        if (array == null || array.length == 0) return -1;
        for (int i = 0; i < array.length; i++) {
            T current = array[i];
            if (current.equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 列表排序
     * @param pageIndex  页码，从1开始
     * @param pageSize   每页的条目数
     * @param comparator 比较器,可为空
     * @param colls      集合数组
     * @param <T>        集合元素类型
     * @return 分页后的段落内容
     */
    @SafeVarargs
    public static <T> List<T> pageList(int pageIndex, int pageSize, Comparator<T> comparator, Collection<T>... colls) {
        final List<T> result = new ArrayList<>();
        for (Collection<T> coll : colls) {
            result.addAll(coll);
        }

        if (comparator != null) {
            result.sort(comparator);
        }

        int resultSize = result.size();
        // 每页条目数大于总数直接返回所有
        if (resultSize <= pageSize) {
            return result;
        }
        final int[] startEnd = PageUtil.transToStartEnd(pageIndex, pageSize);
        if (startEnd[1] > resultSize) {
            // 越界直接返回空
            return new ArrayList<>();
        }

        return result.subList(startEnd[0], startEnd[1]);
    }

    // endregion

    // region Map

    /**
     * 去除map中值两边空格
     */
    public static void trimMapValue(Map<String, Object> map) {
        for (Map.Entry<String, Object> item : map.entrySet()) {
            Object value = item.getValue();
            if (value instanceof String && !ObjectUtils.isEmpty(value)) {
                item.setValue(value.toString().trim());
            }
        }
    }

    // endregion

    // region Convert

    /**
     * 转换为字符<br>
     * 如果给定的值为null，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Character toChar(Object value, Character defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        if (value instanceof Character) {
            return (Character) value;
        }

        final String valueStr = toStr(value, null);
        return StringUtils.isEmpty(valueStr) ? defaultValue : valueStr.charAt(0);
    }

    /**
     * 转换为字符<br>
     * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static Character toChar(Object value) {
        return toChar(value, null);
    }

    /**
     * 转换为byte<br>
     * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Byte toByte(Object value, Byte defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Byte) {
            return (Byte) value;
        }
        if (value instanceof Number) {
            return ((Number) value).byteValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return Byte.parseByte(valueStr);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为byte<br>
     * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static Byte toByte(Object value) {
        return toByte(value, null);
    }

    /**
     * 转换为Short<br>
     * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Short toShort(Object value, Short defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Short) {
            return (Short) value;
        }
        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return Short.parseShort(valueStr.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为Short<br>
     * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static Short toShort(Object value) {
        return toShort(value, null);
    }

    /**
     * 转换为Number<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Number toNumber(Object value, Number defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Number) {
            return (Number) value;
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return NumberFormat.getInstance().parse(valueStr);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为Number<br>
     * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static Number toNumber(Object value) {
        return toNumber(value, null);
    }

    /**
     * 转换为int<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Integer toInt(@NotNull Object value, Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(valueStr.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为int<br>
     * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static Integer toInt(Object value) {
        return toInt(value, null);
    }

    /**
     * 转换为Integer数组<br>
     * @param str 被转换的值
     * @return 结果
     */
    public static Integer[] toIntArray(String str) {
        return toIntArray(str, ",");
    }

    /**
     * 转换为Long数组<br>
     * @param str 被转换的值
     * @return 结果
     */
    public static Long[] toLongArray(String str) {
        return toLongArray(str, ",");
    }

    /**
     * 转换为Integer数组<br>
     * @param str   被转换的值
     * @param split 分隔符
     * @return 结果
     */
    public static Integer[] toIntArray(String str, String split) {
        if (StringUtils.isEmpty(str)) {
            return new Integer[]{};
        }
        String[] arr = str.split(split);
        final Integer[] ints = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            final Integer v = toInt(arr[i], 0);
            ints[i] = v;
        }
        return ints;
    }

    /**
     * 转换为Long数组<br>
     * @param split 分隔符
     * @param str   被转换的值
     * @return 结果
     */
    public static Long[] toLongArray(String str, String split) {
        if (StringUtils.isEmpty(str)) {
            return new Long[]{};
        }
        String[] arr = str.split(split);
        final Long[] longs = new Long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            final Long v = toLong(arr[i], null);
            longs[i] = v;
        }
        return longs;
    }

    /**
     * 转换为String数组<br>
     * @param str 被转换的值
     * @return 结果
     */
    public static String[] toStrArray(String str) {
        return toStrArray(str, ",");
    }

    /**
     * 转换为String数组<br>
     * @param str   被转换的值
     * @param split 分隔符
     * @return 结果
     */
    public static String[] toStrArray(String str, String split) {
        return str.split(split);
    }

    /**
     * 转换为long<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Long toLong(Object value, Long defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Long) {
            return (Long) value;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            // 支持科学计数法
            return new BigDecimal(valueStr.trim()).longValue();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为long<br>
     * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static Long toLong(Object value) {
        return toLong(value, null);
    }

    /**
     * 转换为double<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Double toDouble(Object value, Double defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Double) {
            return (Double) value;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            // 支持科学计数法
            return new BigDecimal(valueStr.trim()).doubleValue();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为double<br>
     * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static Double toDouble(Object value) {
        return toDouble(value, null);
    }

    /**
     * 转换为Float<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Float toFloat(Object value, Float defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Float) {
            return (Float) value;
        }
        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(valueStr.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为Float<br>
     * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static Float toFloat(Object value) {
        return toFloat(value, null);
    }

    /**
     * 转换为boolean<br>
     * String支持的值为：true、false、yes、ok、no，1,0 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Boolean toBool(Object value, Boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        valueStr = valueStr.trim().toLowerCase();
        switch (valueStr) {
            case "1":
            case "true":
            case "yes":
            case "ok":
                return true;
            case "0":
            case "false":
            case "no":
                return false;
            default:
                return defaultValue;
        }
    }

    /**
     * 转换为boolean<br>
     * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static Boolean toBool(Object value) {
        return toBool(value, null);
    }

    /**
     * 转换为Enum对象<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * @param clazz        Enum的Class
     * @param value        值
     * @param defaultValue 默认值
     * @return Enum
     */
    public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value, E defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (clazz.isAssignableFrom(value.getClass())) {
            @SuppressWarnings("unchecked")
            E myE = (E) value;
            return myE;
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return Enum.valueOf(clazz, valueStr);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为Enum对象<br>
     * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
     * @param clazz Enum的Class
     * @param value 值
     * @return Enum
     */
    public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value) {
        return toEnum(clazz, value, null);
    }

    /**
     * 转换为BigInteger<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static BigInteger toBigInteger(Object value, BigInteger defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }
        if (value instanceof Long) {
            return BigInteger.valueOf((Long) value);
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return new BigInteger(valueStr);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为BigInteger<br>
     * 如果给定的值为空，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static BigInteger toBigInteger(Object value) {
        return toBigInteger(value, null);
    }

    /**
     * 转换为BigDecimal<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof Long) {
            return new BigDecimal((Long) value);
        }
        if (value instanceof Double) {
            return BigDecimal.valueOf((Double) value);
        }
        if (value instanceof Integer) {
            return new BigDecimal((Integer) value);
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return new BigDecimal(valueStr);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为BigDecimal<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static BigDecimal toBigDecimal(Object value) {
        return toBigDecimal(value, null);
    }

    /**
     * 将对象转为字符串<br>
     * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组 2、对象数组会调用Arrays.toString方法
     * @param obj 对象
     * @return 字符串
     */
    public static String utf8Str(Object obj) {
        return toStrByCharset(obj, StandardCharsets.UTF_8);
    }

    /**
     * 转换为字符串<br>
     * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
     * 转换失败不会报错
     * @param value 被转换的值
     * @return 结果
     */
    public static String toStr(Object value) {
        return toStr(value, null);
    }

    /**
     * 转换为字符串<br>
     * 如果给定的值为null，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static String toStr(Object value, String defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return value.toString();
    }

    /**
     * 将对象转为字符串<br>
     * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组 2、对象数组会调用Arrays.toString方法
     * @param obj         对象
     * @param charsetName 字符集
     * @return 字符串
     */
    public static String toStrByCharset(Object obj, String charsetName) {
        return toStrByCharset(obj, Charset.forName(charsetName));
    }

    /**
     * 将对象转为字符串<br>
     * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组 2、对象数组会调用Arrays.toString方法
     * @param obj     对象
     * @param charset 字符集
     * @return 字符串
     */
    public static String toStrByCharset(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof byte[]) {
            return toStrByBytesCharset((byte[]) obj, charset);
        } else if (obj instanceof ByteBuffer) {
            return toStrByByteBufferCharset((ByteBuffer) obj, charset);
        }
        return obj.toString();
    }

    /**
     * 将byte数组转为字符串
     * @param bytes   byte数组
     * @param charset 字符集
     * @return 字符串
     */
    public static String toStrByBytesCharset(byte[] bytes, String charset) {
        return toStrByCharset(bytes, StringUtils.isEmpty(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * 解码字节码
     * @param bytes   字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String toStrByBytesCharset(byte[] bytes, Charset charset) {
        if (bytes == null) {
            return null;
        }

        if (null == charset) {
            return new String(bytes);
        }
        return new String(bytes, charset);
    }

    /**
     * 将编码的byteBuffer数据转换为字符串
     * @param data    数据
     * @param charset 字符集，如果为空使用当前系统字符集
     * @return 字符串
     */
    public static String toStrByByteBufferCharset(ByteBuffer data, String charset) {
        if (data == null) {
            return null;
        }

        return toStrByCharset(data, Charset.forName(charset));
    }

    /**
     * 将编码的byteBuffer数据转换为字符串
     * @param data    数据
     * @param charset 字符集，如果为空使用当前系统字符集
     * @return 字符串
     */
    public static String toStrByByteBufferCharset(ByteBuffer data, Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }

    // region Charset

    /**
     * 转换为Charset对象
     * @param charset 字符集，为空则返回默认字符集
     * @return Charset
     */
    public static Charset charset(String charset) {
        return StringUtils.isEmpty(charset) ? Charset.defaultCharset() : Charset.forName(charset);
    }

    /**
     * @return 系统默认字符集编码
     */
    public static String defaultCharsetName() {
        return Charset.defaultCharset().name();
    }

    /**
     * 转换字符串的字符集编码
     * @param source      字符串
     * @param srcCharset  源字符集，默认GBK
     * @param destCharset 目标字符集，默认UTF-8
     * @return 转换后的字符集
     */
    public static String toCharset(String source, Charset srcCharset, Charset destCharset) {
        if (null == srcCharset) {
            srcCharset = StandardCharsets.ISO_8859_1;
        }

        if (null == destCharset) {
            destCharset = StandardCharsets.UTF_8;
        }

        if (StringUtils.isEmpty(source) || srcCharset.equals(destCharset)) {
            return source;
        }
        return new String(source.getBytes(srcCharset), destCharset);
    }

    // endregion

    // region Cookie

    /**
     * 获取Cookie
     */
    public static Cookie[] getCookies() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request;
        if (attributes != null) {
            request = attributes.getRequest();
            return request.getCookies();
        }
        return new Cookie[0];
    }

    /**
     * 获取cookie
     */
    public static String getCookie(String name) {
        try {
            Cookie[] cookies = getCookies();
            for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
                if ((name).equalsIgnoreCase(cookies[i].getName())) {
                    return URLDecoder.decode(cookies[i].getValue(), StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置 Cookie
     */
    public static void setCookie(Cookie cookie) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response;
        if (attributes != null) {
            response = attributes.getResponse();
            if (response != null) {
                response.addCookie(cookie);
            }
        }
    }

    /**
     * 设置 Cookie
     */
    public static void setCookie(String name, String value) {
        try {
            String v = URLEncoder.encode(value, StandardCharsets.UTF_8);
            Cookie cookie = new Cookie(name, v);
            cookie.setPath("/");
            setCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除cookie
     */
    public static void removeCookie(String name) {
        try {
            Cookie[] cookies = getCookies();
            for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
                if ((name).equalsIgnoreCase(cookies[i].getName())) {
                    Cookie cookie = new Cookie(name, "");
                    cookie.setPath("/");
                    // 设置保存cookie最大时长为0，即使其失效
                    cookie.setMaxAge(0);
                    setCookie(cookie);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // endregion

    // region Excel

    /**
     * 导出Excel
     * @param data      导出的数据
     * @param pojoClass 实体对象类型
     * @param title     标题
     */
    public static void exportExcel(@NotNull List data, Class<?> pojoClass, String title) {
        exportExcel(data, pojoClass, title, null, null);
    }

    /**
     * 导出Excel
     * @param data       导出的数据
     * @param pojoClass  实体对象类型
     * @param title      标题
     * @param fieldNames 导出字段名称字符串,逗号隔开(@Excel注解中的name属性)
     */
    public static void exportExcel(@NotNull List data, Class<?> pojoClass, String title, String fieldNames) {
        exportExcel(data, pojoClass, title, null, fieldNames);
    }

    /**
     * 导出Excel
     * @param data       导出的数据
     * @param pojoClass  实体对象类型
     * @param title      标题
     * @param fieldNames 导出字段名称字符串,逗号隔开(@Excel注解中的name属性)
     */
    public static void exportExcel(@NotNull List data, Class<?> pojoClass, String title, ExportParams exportParams, String fieldNames) {
        if (exportParams == null) {
            exportParams = new ExportParams(title, title, ExcelType.XSSF);
        }
        HttpServletResponse response = getResponse();
        Workbook workbook = new SXSSFWorkbook();
        createSheet(workbook, exportParams, pojoClass, data, fieldNames);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(title + ".xlsx", StandardCharsets.UTF_8));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");

        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取实体对象Excel导出字段列表
     * @param pojoClass 实体类型
     */
    public static List<String> getExcelFiledNames(Class<?> pojoClass) {
        List<ExcelExportEntity> excelParams = new ArrayList<>();
        //得到所有字段
        Field[] fileds = PoiPublicUtil.getClassFields(pojoClass);
        ExcelTarget etarget = pojoClass.getAnnotation(ExcelTarget.class);
        String targetId = etarget == null ? null : etarget.value();
        try {
            new ExcelExportService().getAllExcelField(null, targetId, fileds, excelParams, pojoClass, null, null);
            return excelParams.stream().map(ExcelBaseEntity::getName).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static void createSheet(Workbook workbook, ExportParams entity, Class<?> pojoClass, Collection<?> dataSet,
                                    String fieldNames) {
        if (workbook == null || entity == null || pojoClass == null || dataSet == null) {
            throw new ExcelExportException(ExcelExportEnum.PARAMETER_ERROR);
        }
        try {
            List<ExcelExportEntity> excelParams = new ArrayList<>();
            // 得到所有字段
            Field[] fileds = PoiPublicUtil.getClassFields(pojoClass);
            ExcelTarget etarget = pojoClass.getAnnotation(ExcelTarget.class);
            String targetId = etarget == null ? null : etarget.value();
            ExcelExportService excelExportService = new ExcelExportService();
            excelExportService.getAllExcelField(entity.getExclusions(), targetId, fileds, excelParams, pojoClass, null,
                    null);
            final List<ExcelExportEntity> exportList;
            if (isNotBlank(fieldNames)) {
                exportList = new ArrayList<>();
                String[] fieldNameList = StrUtil.splitToArray(fieldNames, ',');
                for (String item : fieldNameList) {
                    excelParams.stream().filter(p -> p.getName().equals(item)).findFirst().ifPresent(exportList::add);
                }
            } else {
                exportList = excelParams;
            }
            //获取所有参数后,后面的逻辑判断就一致了
            excelExportService.createSheetForMap(workbook, entity, exportList, dataSet);
        } catch (Exception e) {
            throw new ExcelExportException(ExcelExportEnum.EXPORT_ERROR, e.getCause());
        }
    }

    /**
     * 获取excel字段/字段注释Map
     * @param pojoClass 实体对象类型
     */
    public static Map<String, String> getExcelFieldMap(@NotNull Class<?> pojoClass) {
        Map<String, String> map = new HashMap<>();
        ExcelTarget etarget = pojoClass.getAnnotation(ExcelTarget.class);
        String targetId = etarget == null ? null : etarget.value();
        Field[] fields;
        do {
            fields = pojoClass.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                String fieldComment = fieldName;
                if (field.getAnnotation(Excel.class) != null) {
                    Excel excel = field.getAnnotation(Excel.class);
                    fieldComment = PoiPublicUtil.getValueByTargetId(excel.name(), targetId, null);
                    if (StringUtils.isBlank(fieldComment)) {
                        fieldComment = fieldName;
                    }
                }
                map.put(fieldName, fieldComment);
            }
            pojoClass = pojoClass.getSuperclass();
        } while (pojoClass != Object.class && pojoClass != null);
        return map;
    }

    // /**
    //  * 自适应excel单元格
    //  *
    //  * @param sheet excel
    //  */
    // public static void autoSizeExcelColumn(Sheet sheet) {
    //     for (int columnNum = 0; columnNum <= 8; columnNum++) {
    //         int columnWidth = sheet.getColumnWidth(columnNum) / 256;
    //         for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
    //             Row currentRow;
    //             //当前行未被使用过
    //             if (sheet.getRow(rowNum) == null) {
    //                 currentRow = sheet.createRow(rowNum);
    //             } else {
    //                 currentRow = sheet.getRow(rowNum);
    //             }
    //             if (currentRow.getCell(columnNum) != null) {
    //                 Cell currentCell = currentRow.getCell(columnNum);
    //                 if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
    //                     int length = currentCell.getStringCellValue().getBytes().length;
    //                     if (columnWidth < length) {
    //                         columnWidth = length;
    //                     }
    //                 }
    //             }
    //         }
    //         sheet.setColumnWidth(columnNum, columnWidth * 256);
    //     }
    // }

    // endregion

    // region Web

    /**
     * 标记新建记录
     * @param map 模型对象
     */
    public static void createMark(ModelMap map) {
        map.put(R.CREATE_MARK, "1");
    }

    /**
     * 是否新建记录
     */
    public static boolean isCreate() {
        return "1".equals(getRequest().getParameter(R.CREATE_MARK));
    }

    public static String redirectUrl(String url) {
        if (XCI.isEmpty(url)) {
            throw new RuntimeException("请指定重定向的Url地址");
        }
        return "redirect:" + url;
    }

    public static String forwardUrl(String url) {
        if (XCI.isEmpty(url)) {
            throw new RuntimeException("请指定跳转的Url地址");
        }
        return "forward:" + url;
    }

    /**
     * 获取服务器IP
     */
    public static String getHostIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "未知";
    }

    /**
     * 获取客户端IP
     */
    public static String getClientIP() {
        return ServletUtil.getClientIP(getRequest());
    }

    // /**
    //  * 解析ip地址地理位置
    //  * @param ip ip地址
    //  */
    // public static String resolvingIpLocation(String ip) {
    //     String address = "";
    //     if (isBlank(ip)) {
    //         return address;
    //     }
    //     // if (!setting.getApi().isResolvingIpLocation()) {
    //     //     return address;
    //     // }
    //     // 内网不查询
    //     if (isInternalIP(ip)) {
    //         return "内网IP";
    //     }
    //
    //     var url = "?ip=" + ip;
    //     String rspStr = HttpUtil.get(url, 3000);
    //     if (StringUtils.isEmpty(rspStr)) {
    //         return address;
    //     }
    //     try {
    //         JSONObject obj = JSONUtil.parseObj(rspStr);
    //         JSONObject data = obj.get("data", JSONObject.class);
    //         String country = data.getStr("country");
    //         String region = data.getStr("region");
    //         String city = data.getStr("city");
    //         String isp = data.getStr("isp");
    //         address = format("{} {}{} {}", country, region, city, isp);
    //     } catch (Exception e) {
    //         log.error("解析ip出现错误,请求字符串:{},返回的字符串:{}", url, rspStr);
    //     }
    //     return address;
    // }

    /**
     * 是否内网 ip
     * @param ipAddress ip 地址
     */
    public static boolean isInternalIP(String ipAddress) {
        if (ipAddress.equals("0:0:0:0:0:0:0:1") || ipAddress.equals("localhost") || ipAddress.equals("127.0.0.1")) {
            return true;
        }
        boolean isInnerIp;
        long ipNum = getIpNum(ipAddress);
        /*
         私有IP：A类  10.0.0.0-10.255.255.255
         B类  172.16.0.0-172.31.255.255
         C类  192.168.0.0-192.168.255.255
         当然，还有127这个网段是环回地址
         **/
        long aBegin = getIpNum("10.0.0.0");
        long aEnd = getIpNum("10.255.255.255");
        long bBegin = getIpNum("172.16.0.0");
        long bEnd = getIpNum("172.31.255.255");
        long cBegin = getIpNum("192.168.0.0");
        long cEnd = getIpNum("192.168.255.255");
        isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd);
        return isInnerIp;
    }

    private static long getIpNum(String ipAddress) {
        String[] ip = ipAddress.split("\\.");
        long a = Integer.parseInt(ip[0]);
        long b = Integer.parseInt(ip[1]);
        long c = Integer.parseInt(ip[2]);
        long d = Integer.parseInt(ip[3]);

        return a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
    }

    private static boolean isInner(long userIp, long begin, long end) {
        return (userIp >= begin) && (userIp <= end);
    }

    /**
     * 判断请求是否为ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String type = request.getHeader("X-Requested-With");
        if (isBlank(type)) {
            type = request.getParameter("X-Requested-With");
        }
        return isNotBlank(type) && "XMLHttpRequest".equals(type);
    }

    /**
     * 判断请求是否为 api 请求
     */
    public static boolean isApiRequest(HttpServletRequest request) {
        String type = request.getHeader("X-Requested-With");
        if (isBlank(type)) {
            type = request.getParameter("X-Requested-With");
        }
        return isNotBlank(type) && "api".equals(type);
    }

    /**
     * 输出Json对象
     * @param data 输出的Json对象
     * @throws IOException IO异常
     */
    public static void writeJson(RestResult data) throws IOException {
        HttpServletResponse response = getResponse();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getWriter().write(toJsonString(data));
    }

    /**
     * 获取站点Url信息
     */
    public static String getSiteFullUrl() {
        HttpServletRequest request = getRequest();
        //http
        String scheme = request.getScheme();
        //localhost
        String serverName = request.getServerName();
        //8080
        int serverPort = request.getServerPort();
        if (serverPort == 80) {
            return format("{}://{}", scheme, serverName);
        }
        return format("{}://{}:{}", scheme, serverName, serverPort);
    }

    /**
     * 获取完整Url信息
     */
    public static String getSiteFullUrl(String virtualUrl) {
        HttpServletRequest request = getRequest();
        String rootUrl = getSiteFullUrl();
        if (!virtualUrl.startsWith("/")) {
            virtualUrl = "/" + virtualUrl;
        }
        return format("{}{}{}", rootUrl, request.getContextPath(), virtualUrl);
    }

    /**
     * 获取Web项目下的web root路径
     * @return web root路径
     * @since 4.0.13
     */
    public static File getWebRoot() {
        return FileUtil.getWebRoot();
    }

    /**
     * 获取请求参数键值对
     */
    public static GMap getRequestParamMap() {
        HttpServletRequest request = getRequest();
        GMap map = new GMap();
        Enumeration<String> es = request.getParameterNames();
        while (es.hasMoreElements()) {
            String val = es.nextElement();
            map.put(val, request.getParameter(val));
        }
        return map;
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequestAttributes().getRequest().getSession();
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();
        Map<String, Object> returnMap = new HashMap<>();
        Iterator<?> entries = properties.entrySet().iterator();
        Map.Entry<?, ?> entry;
        String name;
        String value = R.Empty;
        while (entries.hasNext()) {
            entry = (Map.Entry<?, ?>) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (String s : values) {
                    value = s + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 检测浏览器兼容性
     * @return 不存在兼容性返回true
     */
    public static boolean checkBrowserCompatibility() {
        String userAgent = getRequest().getHeader("User-Agent");
        if (isBlank(userAgent)) {
            return false;
        }
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        String name = "";
        int version = 0;
        if (agent.getBrowser() != null) {
            name = agent.getBrowser().getName();
        }
        if (agent.getBrowserVersion() != null) {
            version = Integer.parseInt(agent.getBrowserVersion().getMajorVersion());
        }
        return ((name.contains("Chrome") && version >= 40) || (name.contains("Firefox") && version >= 40) || (name.contains("Opera") && version >= 40) || (name.contains(
                "Safari") && version >= 8) || (name.contains("Edge") && version >= 10) || (name.contains("Internet Explorer") && version >= 11));
    }

    /**
     * 将URL的参数和body参数合并
     */
    public static SortedMap<String, String> getAllParams(HttpServletRequest request) throws IOException {
        SortedMap<String, String> result = new TreeMap<>();
        // 获取URL上的参数
        getUrlParams(request, result);
        // 获取body参数
        getAllRequestParam(result);
        return result;
    }

    /**
     * 获取 Body 参数
     */
    @SuppressWarnings("unchecked")
    public static void getAllRequestParam(SortedMap<String, String> result)
            throws IOException {
        var request = getRequest();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder wholeStr = new StringBuilder();
        // 一行一行的读取body体里面的内容
        String str;
        while ((str = reader.readLine()) != null) {
            wholeStr.append(str);
        }
        wholeStr.trimToSize();
        String s = wholeStr.toString();
        if (!StringUtils.isEmpty(s)) {
            // 转化成json对象
            Map<String, String> allRequestParam = toJsonObject(s, Map.class);
            // 将URL的参数和body参数进行合并
            for (Map.Entry entry : allRequestParam.entrySet()) {
                result.put((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    /**
     * 获取url参数
     */
    public static void getUrlParams(HttpServletRequest request, SortedMap<String, String> result) {
        String param = "";
        String urlParam = request.getQueryString();
        if (urlParam != null) {
            param = URLDecoder.decode(urlParam, StandardCharsets.UTF_8);
        }
        String[] params = param.split("&");
        for (String s : params) {
            int index = s.indexOf("=");
            if (index != -1) {
                result.put(s.substring(0, index), s.substring(index + 1));
            }
        }
    }

    /**
     * 获取请求上下文
     * @return 返回请求上下文
     */
    public static String getContextPath() {
        return getRequest().getContextPath();
    }

    /**
     * 文件下载
     * @param file        下载的文件对象
     * @param fileName    文件名称
     * @param contentType 文档类型
     */
    public static void download(File file, String fileName, String contentType, HttpServletResponse response) {
        if (!file.exists()) {
            throw new AppException("无效的文件");
        }
        if (isEmpty(contentType)) {
            try {
                contentType = Files.probeContentType(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                contentType = "application/octet-stream";
            }
        }
        response.setContentLengthLong(file.length());
        response.setContentType(contentType);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        byte[] buff = new byte[81920];
        BufferedInputStream bis = null;
        OutputStream os;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, i);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // endregion

    // region Thread

    /**
     * sleep等待,单位为毫秒
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.error("Thread.sleep出错", e);
        }
    }

    /**
     * 停止线程池
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍人超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                        log.info("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 打印线程异常信息
     */
    public static void printThreadException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            log.error(t.getMessage(), t);
        }
    }

    // endregion

    // region Jwt

    /**
     * 生成jwt令牌
     * @param data       写入的数据
     * @param secretKey  加密密钥
     * @param expireDate 过期时间
     */
    public static String createJwt(Map<String, Object> data, String secretKey, Date expireDate) {
        Assert.notEmpty(data);
        // 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = org.apache.commons.codec.binary.Base64.decodeBase64(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // iss: jwt签发者
        // sub: jwt所面向的用户
        // aud: 接收jwt的一方
        // exp: jwt的过期时间，这个过期时间必须要大于签发时间
        // nbf: 定义在什么时间之前，该jwt都是不可用的
        // iat: jwt的签发时间
        // jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
        JwtBuilder builder = Jwts.builder().setClaims(data).setIssuedAt(now).signWith(signatureAlgorithm, signingKey);

        if (expireDate != null) {
            builder.setExpiration(expireDate).setNotBefore(now);
        }
        return builder.compact();
    }

    /**
     * 解析jwt
     * @param token     令牌字符串
     * @param secretKey 加密密钥
     */
    public static Map<String, Object> parseJwt(String token, String secretKey) {
        try {
            return Jwts.parser().setSigningKey(org.apache.commons.codec.binary.Base64.decodeBase64(secretKey)).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtException("认证签名过期", e);
        } catch (MalformedJwtException e) {
            throw new JwtException("认证签名格式错误", e);
        } catch (IllegalArgumentException e) {
            throw new JwtException("认证签名参数错误", e);
        } catch (Exception e) {
            throw new JwtException("无效的认证签名", e);
        }
    }

    /**
     * 获取 TokenId
     */
    public static RestResult<String> getTokenId(String token) {
        Map<String, Object> map = XCI.parseJwt(token, R.JWT_SECRET_KEY);
        Object idObj = map.get(R.ID);
        if (idObj == null || idObj.toString().length() == 0) {
            return RestResult.fail("获取TokenId失败,token信息已被篡改");
        }
        String id = XCI.decrypt(idObj.toString());
        return RestResult.ok(id);
    }

    // endregion

    // region Security

    /**
     * 加密用户密码
     * @param account  账号
     * @param password 密码
     * @param salt     密码盐
     * @return 返回加密后的密文
     */
    public static String encryptPassword(String account, String password, String salt) {
        String accountMd5 = SecureUtil.md5(account);
        String passwordMd5 = SecureUtil.md5(password);
        String secretKeyMd5 = SecureUtil.md5(salt);
        return SecureUtil.md5("@" + secretKeyMd5 + "#" + passwordMd5 + "$" + accountMd5 + "%" + secretKeyMd5);
    }

    /**
     * 生成用户密码秘钥
     */
    public static String createSalt() {
        String sj1 = SecureUtil.md5(guid());
        String sj2 = SecureUtil.md5(guid());
        return SecureUtil.md5(sj1 + sj2);
    }

    // endregion

    // region Cache

    /**
     * 获取缓存对象值
     * @param cache 缓存容器
     * @param key   缓存键
     * @param <T>   对象类型
     * @return 返回缓存容器中指定名称的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getCacheValue(Cache cache, Object key) {
        Cache.ValueWrapper wrapper = cache.get(key);
        if (wrapper == null) {
            return null;
        }
        return (T) wrapper.get();
    }

    /**
     * 获取缓存对象值
     * @param cache       缓存容器
     * @param key         缓存键
     * @param valueLoader 不存在缓存键时值加载器
     * @param <T>         对象类型
     * @return 返回缓存容器中指定名称的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getCacheValue(Cache cache, Object key, Callable<T> valueLoader) {
        Cache.ValueWrapper wrapper = cache.get(key);
        if (wrapper == null) {
            try {
                T current = valueLoader.call();
                cache.put(key, current);
                return current;
            } catch (Exception e) {
                throw new Cache.ValueRetrievalException(key, valueLoader, e);
            }
        }
        return (T) wrapper.get();
    }

    /**
     * 获取缓存对象集合
     * @param cache 缓存容器
     * @param <T>   对象类型
     * @return 返回缓存容器中所有的缓存值
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getCacheValueList(Cache cache) {
        net.sf.ehcache.Cache ehcache = (net.sf.ehcache.Cache) cache.getNativeCache();
        Map<Object, Element> all = ehcache.getAll(ehcache.getKeysWithExpiryCheck());
        List<T> originList = new ArrayList<>();
        for (Element item : all.values()) {
            originList.add((T) item.getObjectValue());
        }
        return originList;
    }

    /**
     * 检查缓存容器中是否存在指定名称的对象
     * @param cache 缓存容器
     * @param key   缓存键
     * @return 存在返回true
     */
    public static boolean cacheContains(Cache cache, Object key) {
        net.sf.ehcache.Cache ehcache = (net.sf.ehcache.Cache) cache.getNativeCache();
        return ehcache.isKeyInCache(key);
    }

    // endregion

    // region Spring

    /**
     * 通过name获取 Bean.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) SpringBeanFactory.context.getBean(name);
    }

    /**
     * 通过class获取Bean.
     */
    public static <T> T getBean(Class<T> clazz) {
        return SpringBeanFactory.context.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return SpringBeanFactory.context.getBean(name, clazz);
    }

    /**
     * 获取接口实现类集合
     */
    public static <T> List<T> getBeansOfTypeList(Class<T> clazz) {
        Map<String, T> map = SpringBeanFactory.context.getBeansOfType(clazz);
        List<T> list = Lists.newArrayList();
        for (var item : map.entrySet()) {
            list.add(item.getValue());
        }
        return list;
    }

    /**
     * 获取接口实现类Map
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return SpringBeanFactory.context.getBeansOfType(clazz);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     * @param name bean 名称
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return SpringBeanFactory.context.containsBean(name);
    }

    /**
     * 是否能找到指定类型的bean对象，能找到则返回true
     */
    public static <T> boolean containsBean(Class<T> clazz) {
        try {
            SpringBeanFactory.context.getBean(clazz);
            return true;
        } catch (BeansException ex) {
            return false;
        }
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * @param name bean 名称
     * @return boolean
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return SpringBeanFactory.context.isSingleton(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @param name bean 名称
     */
    public static String[] getSpringAliases(String name) throws NoSuchBeanDefinitionException {
        return SpringBeanFactory.context.getAliases(name);
    }

    /**
     * @param name 类型名称
     * @return Class 注册对象的类型
     */
    public static Class<?> getSpringType(String name) throws NoSuchBeanDefinitionException {
        return SpringBeanFactory.context.getType(name);
    }

    /**
     * 获取aop代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy() {
        return (T) AopContext.currentProxy();
    }

    /**
     * 主动向Spring容器中注册bean
     * @param applicationContext spring上下文
     * @param name               BeanName
     * @param clazz              注册的bean的类性
     * @param args               构造方法的必要参数，顺序和类型要求和clazz中定义的一致
     * @return 返回注册到容器中的bean对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T registerBean(ConfigurableApplicationContext applicationContext, String name,
                                     Class<T> clazz, Object... args) {
        if (applicationContext.containsBean(name)) {
            Object bean = applicationContext.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz)) {
                return (T) bean;
            } else {
                throw new RuntimeException("BeanName 重复 " + name);
            }
        }

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        for (Object arg : args) {
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
        return applicationContext.getBean(name, clazz);
    }

    // endregion

    // region Tree

    /**
     * 生成路径字符串
     * @param parentPath 上级路径
     * @param index      序号
     * @return 返回新创建的排序路径
     */
    public static String buildTreePath(String parentPath, Integer index) {
        if (isBlank(parentPath)) {
            parentPath = R.Empty;
        }
        return parentPath + StrUtil.fillBefore(index.toString(), '0', R.PATH_LENGTH);
    }

    /**
     * 获取新建节点排序路径
     * @param index 序号
     */
    public static String buildNewTreePath(Integer index) {
        return StrUtil.fillBefore(index.toString(), '0', R.PATH_LENGTH);
    }

    /**
     * 树形查询条件
     * @param list       数据源
     * @param resultList 结果列表
     */
    public static List<?> filterTree(List<? extends ITreeModel> list, List resultList) {
        // Predicate<? super TreeModel> predicate
        // List resultList = list.stream().filter(predicate).collect(Collectors.toList());
        if (resultList == null || resultList.size() == 0)
            return resultList;
        List<ITreeModel> treeList = new ArrayList<>();
        for (Object entity : resultList) {
            ITreeModel model = (ITreeModel) entity;
            //先把自己加入进来
            treeList.add(model);
            //向上查询
            Long pid = model.getParentId();
            getTreeParentsCore(list, treeList, pid);
        }
        //treeList.sort(Comparator.comparing(TreeModel::getPath));
        return treeList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 获取指定节点的所有父节点
     * @param list    数据源
     * @param id      指定节点id
     * @param hasSelf 是否包含自身
     */
    public static List<?> getTreeParents(List<? extends ITreeModel> list, Long id, boolean hasSelf) {
        ITreeModel current = getTreeCurrentModel(list, id);
        List<ITreeModel> storeList = new ArrayList<>();
        if (current != null) {
            if (hasSelf) {
                storeList.add(current);
            }
            getTreeParentsCore(list, storeList, current.getParentId());
        }
        return storeList;
    }

    private static void getTreeParentsCore(List<? extends ITreeModel> list, List<ITreeModel> storeList, Long pid) {
        while (true) {
            if (pid <= 0)
                break;
            final Long parentId = pid;
            Optional<? extends ITreeModel> parentOptional = list.stream().filter(p -> p.getId().equals(parentId)).findFirst();
            if (parentOptional.isEmpty())
                break;

            ITreeModel parent = parentOptional.get();
            storeList.add(parent);
            pid = parent.getParentId();
        }
    }

    /**
     * 获取指定节点的所有子节点
     * @param list    数据源
     * @param id      指定节点id
     * @param hasSelf 是否包含自身
     */
    public static List<? extends ITreeModel> getTreeChilds(List<? extends ITreeModel> list, Long id, boolean hasSelf) {
        ITreeModel current = getTreeCurrentModel(list, id);
        List<ITreeModel> storeList = new ArrayList<>();
        if (current != null) {
            if (hasSelf) {
                storeList.add(current);
            }
            getTreeChildsCore(list, storeList, current.getId());
        }
        return storeList;
    }

    private static void getTreeChildsCore(List<? extends ITreeModel> entityList, List<ITreeModel> storeList, Long id) {
        List<ITreeModel> data = entityList.stream().filter(p -> p.getParentId().equals(id)).collect(Collectors.toList());
        if (data.size() <= 0)
            return;
        for (ITreeModel item : data) {
            storeList.add(item);
            getTreeChildsCore(entityList, storeList, item.getId());
        }
    }

    private static ITreeModel getTreeCurrentModel(List<? extends ITreeModel> list, Long id) {
        Optional<? extends ITreeModel> entityOptional = list.stream().filter(p -> p.getId().equals(id)).findFirst();
        return entityOptional.orElse(null);
    }

    public static List<IdValue> toParentIdValues(List<TreeNodeIndex> nodeIndexs) {
        List<IdValue> list = new ArrayList<>();
        for (TreeNodeIndex nodeIndex : nodeIndexs) {
            list.add(new IdValue(Long.valueOf(nodeIndex.getId()), nodeIndex.getParentId()));
        }
        return list;
    }

    public static List<IdValue> toPathIdValues(List<TreeNodeIndex> nodeIndexs) {
        List<IdValue> list = new ArrayList<>();
        for (TreeNodeIndex nodeIndex : nodeIndexs) {
            list.add(new IdValue(Long.valueOf(nodeIndex.getId()), nodeIndex.getIndex()));
        }
        return list;
    }

    // endregion

    // region Json

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = getBean(ObjectMapper.class);
        }
        return objectMapper;
    }

    public static void setObjectMapper(ObjectMapper oMapper) {
        objectMapper = oMapper;
    }

    /**
     * 对象转Json字符串
     */
    public static String toJsonString(Object object) {
        return toJsonString(object, false);
    }

    /**
     * 对象转Json字符串
     * @param isPretty 是否格式化保存
     */
    public static String toJsonString(Object object, boolean isPretty) {
        if (object == null) {
            return "";
        }
        String jsonString;
        try {
            var mapper = getObjectMapper();
            if (isPretty) {
                jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            } else {
                jsonString = mapper.writeValueAsString(object);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("转换json字符失败!", e);
        }
        return jsonString;
    }

    /**
     * Json字符串转对象
     */
    public static <T> T toJsonObject(String data, Class<T> c) {
        try {
            return getObjectMapper().readValue(data, c);
        } catch (IOException e) {
            throw new RuntimeException("将json字符转换为对象时失败!", e);
        }
    }

    /**
     * Json字符串转对象
     */
    public static <T> T toJsonObject(String data, JavaType valueType) {
        try {
            return getObjectMapper().readValue(data, valueType);
        } catch (IOException e) {
            throw new RuntimeException("将json字符转换为对象时失败!", e);
        }
    }

    /**
     * Json字符串转集合对象
     */
    public static <T> T toJsonListObject(String data, Class<?> listClass, Class<?> elementClass) {
        try {
            return getObjectMapper().readValue(data, getJsonCollectionType(listClass, elementClass));
        } catch (IOException e) {
            throw new RuntimeException("将json字符转换为对象时失败!", e);
        }
    }

    /**
     * Json字符串转Map对象
     */
    public static <T> T toJsonMapObject(String data, Class<?> collectionClass, Class<?> keyClass, Class<?> elementClass) {
        try {
            return getObjectMapper().readValue(data, getJsonCollectionType(collectionClass, keyClass, elementClass));
        } catch (IOException e) {
            throw new RuntimeException("将json字符转换为对象时失败!", e);
        }
    }

    private static JavaType getJsonCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getObjectMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 加载 json 文件数据
     * @param file            文件对象
     * @param collectionClass 集合类型
     * @param elementClass    元素类型
     * @param <T>             对象类型
     * @return 数据集合
     */
    public static <T> List<T> loadJsonList(File file, Class<?> collectionClass, Class<?> elementClass) {
        if (file.exists()) {
            String content = FileUtil.readUtf8String(file);
            if (isNotBlank(content)) {
                return toJsonListObject(content, collectionClass, elementClass);
            }
        }
        return Lists.newArrayList();
    }

    /**
     * 保存 json 文件数据
     * @param file 文件对象
     * @param list 数据列表
     */
    public static void saveJsonList(File file, List list) {
        String content = toJsonString(list);
        FileUtil.mkParentDirs(file);
        FileUtil.writeUtf8String(content, file);
    }

    // endregion

    // region Business

    /**
     * 手动验证Bean对象
     * @param bean bean对象
     */
    public RestResult validateBean(Object bean) {
        Validator beanValidator = XCI.getBean(Validator.class);
        var result = beanValidator.validate(bean);
        StringBuilder builder = new StringBuilder();
        if (result.size() > 0) {
            for (var item : result) {
                String field = item.getPropertyPath().toString();
                String msg = item.getMessage();
                builder.append(XCI.getValidMsg(field, msg));
            }
            return RestResult.fail(builder.toString());
        }
        return RestResult.ok();
    }

    /**
     * 检测方法是否属于匿名方法
     * @param method 方法对象
     */
    public static boolean IsAllowAnonymous(Method method) {
        if (method.getAnnotation(AllowAnonymous.class) != null || method.getDeclaringClass().getAnnotation(
                AllowAnonymous.class) != null) {
            return true;
        }
        // 类和方法都没有@Authorize,不需要用户认证,直接放行
        Authorize classAuth = method.getDeclaringClass().getAnnotation(Authorize.class);
        Authorize methodAuth = method.getAnnotation(Authorize.class);
        return methodAuth == null && classAuth == null;
    }

    /**
     * 获取验证错误信息
     */
    public static String getValidMsg(String field, String msg) {
        return format("[{}]{};", field, msg);
    }

    /**
     * @param params 所有的请求参数都会在这里进行排序加密
     * @return 验证签名结果
     */
    public static boolean verifySign(SortedMap<String, String> params) {
        String urlSign = params.get("sign");
        // log.info("Url Sign : {}", urlSign);
        if (StringUtils.isEmpty(urlSign)) {
            return false;
        }
        // 把参数加密  要先去掉 Url 里的 Sign
        params.remove("sign");
        String paramsJsonStr = toJsonString(params);
        String paramsSign = DigestUtils.md5DigestAsHex(paramsJsonStr.getBytes()).toUpperCase();

        // log.info("Param Sign : {}", paramsSign);
        return !StringUtils.isEmpty(paramsSign) && urlSign.equals(paramsSign);
    }

    /**
     * 对象属性值比较
     * @param oldBean 原实体
     * @param newBean 新实体
     * @param <T>     实体类型
     * @return 返回修改变化字符串
     */
    public static <T> List<BeanDiff> contrast(T oldBean, T newBean) {
        List<BeanDiff> list = Lists.newArrayList();
        // StringBuilder sb = new StringBuilder();
        // 通过反射获取类的Class对象
        Class clazz = oldBean.getClass();
        // 获取类型及字段属性
        Field[] fields = ReflectUtil.getFields(clazz);
        for (Field field : fields) {
            String fileName = field.getName();
            String fileLabel = R.Empty;
            if (fileName.equalsIgnoreCase("serialVersionUID")) {
                continue;
            }
            if (field.isAnnotationPresent(ApiModelProperty.class)) {
                fileLabel = field.getAnnotation(ApiModelProperty.class).value();
            }
            try {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                // 获取对应属性值
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(oldBean);
                Object o2 = getMethod.invoke(newBean);
                if (o1 == null || o2 == null) {
                    continue;
                }
                if (!o1.equals(o2)) {
                    var bean = new BeanDiff();
                    bean.setFieldName(fileName);
                    bean.setFieldLabel(fileLabel);
                    bean.setBefore(o1);
                    bean.setAfter(o2);
                    list.add(bean);
                }
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 如果id为空或者id小于等于0则无效
     * @param created 是否新建
     * @param id      主键
     * @return 如果无效, 返回true
     */
    public static boolean invalidId(boolean created, Long id) {
        //如果是编辑,直接返回有效,不做处理
        if (!created) return false;
        return invalidId(id);
    }

    /**
     * 如果id为空或者id小于等于0则无效
     * @param id 主键
     * @return 如果无效, 返回true
     */
    public static boolean invalidId(Long id) {
        if (id == null) {
            return true;
        }
        return id <= 0;
    }

    /**
     * 如果value为空或者value小于等于0则无效
     * @param value Long值
     * @return 如果无效, 返回true
     */
    public static boolean invalidLong(Long value) {
        if (value == null) {
            return true;
        }
        return value <= 0;
    }

    /**
     * 如果value为空或者value小于等于0则无效
     * @param value Integer值
     * @return 如果无效, 返回true
     */
    public static boolean invalidInteger(Integer value) {
        if (value == null) {
            return true;
        }
        return value <= 0;
    }

    /**
     * 如果value不为空并且value大于0则有效
     * @param value Long值
     * @return 如果有效, 返回true
     */
    public static boolean validLong(Long value) {
        if (value == null) {
            return false;
        }
        return value > 0;
    }

    /**
     * 如果value不为空并且value大于0则有效
     * @param value Integer值
     * @return 如果有效, 返回true
     */
    public static boolean validInteger(Integer value) {
        if (value == null) {
            return false;
        }
        return value > 0;
    }

    /**
     * 如果新建记录,则排除主键为空,否则返回主键值
     * @param created 是否新建
     * @param id      主键
     */
    public static Long excludeId(boolean created, Long id) {
        return created ? null : id;
    }

    /**
     * 获取当前请求浏览器操作系统IP地址信息
     */
    public static BrowserOsIpInfo getRequestBrowserOsInfo() {
        BrowserOsIpInfo info = new BrowserOsIpInfo();
        HttpServletRequest request = getRequest();
        String ip = ServletUtil.getClientIP(request);
        info.setIp(ip);
        // 解析ip地址地理位置
        // info.setIpLocation(resolvingIpLocation(ip));
        var userAgent = request.getHeader("User-Agent");
        info.setUserAgent(userAgent);
        if (userAgent != null) {
            UserAgent agent = UserAgent.parseUserAgentString(userAgent);
            String bver = R.Empty;
            if (agent.getBrowserVersion() != null) {
                bver = agent.getBrowserVersion().getMajorVersion();
            }
            info.setBrowser(format("{} {}", agent.getBrowser().getName(), bver));
            info.setOs(agent.getOperatingSystem().getName());
        }
        return info;
    }

    /**
     * 处理结束日期,结束日期增加一天
     * @param operateEndDateTime 结束日期
     * @return 返回增加后的日期
     */
    public static Date PlusEndDateTime(Date operateEndDateTime) {
        if (operateEndDateTime != null) {
            return new DateTime(operateEndDateTime).offset(DateField.DAY_OF_MONTH, 1).toJdkDate();
        }
        return null;
    }

    /**
     * 处理对象字符串替换操作
     * 如果对象是 Map,查找Map中所有的字符串 Value 进行处理
     * 如果对象是 JavaBean,查找对象中字符串类型字段进行处理
     * @param obj      待处理对象
     * @param converts 转换策略
     * @param oldStr   待替换字符串
     * @param newStr   替换后字符串
     */
    public static void objectStringConverter(Object obj, StringConverterType[] converts,
                                             Class<?>[] customs,String oldStr, String newStr) {
        if (obj instanceof Map) {
            //Map
            var map = (Map) obj;
            for (Object key : map.keySet()) {
                Object val = map.get(key);
                //字符串类型,并且字符串不能为空才进行处理
                if (!(val instanceof String) || isBlank(val)) continue;
                var res = stringConvert((String) val, converts, customs,oldStr, newStr);
                map.put(key, res);
            }
        } else {
            //JavaBean
            var fields = ReflectUtil.getFields(obj.getClass());
            for (Field field : fields) {
                var val = ReflectUtil.getFieldValue(obj, field);
                //字符串类型,并且字符串不能为空才进行处理
                if (!field.getType().equals(String.class) || isBlank(val)) continue;
                var res = stringConvert((String) val, converts, customs,oldStr, newStr);
                ReflectUtil.setFieldValue(obj, field, res);
            }
        }
    }

    /**
     * 处理字符串替换操作
     * @param item     字符串
     * @param converts 转换策略
     * @param oldStr   待替换字符串
     * @param newStr   替换后字符串
     */
    public static String stringConvert(String item, StringConverterType[] converts,
                                       Class<?>[] customs,String oldStr, String newStr) {
        if (isBlank(item)) return item;
        if (isEmpty(converts)) return item;
        for (StringConverterType convert : converts) {
            switch (convert) {
                case custom:
                    for (Class<?> custom : customs) {
                        var bean = getBean(custom);
                        var func = (Function<String,String>)bean;
                        item = func.apply(item);
                    }
                    break;
                case trimLeft://去除左边空格
                    item = StrUtil.trimStart(item);
                    break;
                case trimRight://去除右边空格
                    item = StrUtil.trimEnd(item);
                    break;
                case trimLeftRight://去除左边和右边空格
                    item = StrUtil.trim(item);
                    break;
                case trimAll://去除所有空格
                    item = StrUtil.trim(item).replaceAll("\\s*", R.Empty);
                    break;
                case toSBC://半角转全角
                    item = Convert.toSBC(item);
                    break;
                case toDBC://全角转半角
                    item = Convert.toDBC(item);
                    break;
                case toUnderlineCase://将驼峰式命名的字符串转换为下划线方式
                    item = StrUtil.toUnderlineCase(item);
                    break;
                case toCamelCase://将下划线方式命名的字符串转换为驼峰式
                    item = StrUtil.toCamelCase(item);
                    break;
                case replace://字符串替换
                    var kvs = parseArrayString(oldStr, newStr, ',');
                    for (KeyValue kv : kvs) {
                        item = item.replace(kv.getKey(), kv.getValue());
                    }
                    break;
            }
        }
        return item;
    }

    /**
     * 把字符串按分割符号分割后,存入 keyvalue 集合中
     * key 字符串是必须的,value 字符串如果没有对应的值则设置为空字符串
     * @param keyString   key 字符串
     * @param valueString value 字符串
     * @param separator   分隔符
     * @return KeyValue 集合
     */
    public static List<KeyValue> parseArrayString(String keyString, String valueString, char separator) {
        List<KeyValue> list = new ArrayList<>();
        String[] keyArray = splitToArray(keyString, separator);
        String[] valueArray = splitToArray(valueString, separator);
        for (int i = 0; i < keyArray.length; i++) {
            KeyValue kv = new KeyValue();
            kv.setKey(keyArray[i]);
            kv.setValue(R.Empty);
            if (valueArray.length >= (i + 1)) {
                kv.setValue(valueArray[i]);
            }
            list.add(kv);
        }
        return list;
    }
    // endregion
}