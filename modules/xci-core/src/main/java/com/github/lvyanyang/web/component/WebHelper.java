/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.web.component;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.lvyanyang.core.IApplication;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.XCI;
import com.github.lvyanyang.exceptions.AppException;
import com.github.lvyanyang.internal.ServletUtil;
import com.github.lvyanyang.model.Dic;
import com.github.lvyanyang.web.configuration.WebProperties;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 页面帮助类
 * @author 吕艳阳
 */
@Component
public class WebHelper {
    @Resource private IApplication application;
    @Resource private ResourceUrlProvider resourceUrlProvider;
    @Resource private WebProperties webProperties;

    /**
     * 标记新建记录
     * @param map 模型对象
     */
    public void createMark(ModelMap map) {
        XCI.createMark(map);
    }

    /**
     * 是否新建记录
     */
    public boolean create() {
        return XCI.isCreate();
    }

    /**
     * 获取站点Url信息
     */
    public String siteFullUrl() {
        return XCI.getSiteFullUrl();
    }

    /**
     * 获取当前日期时间对象
     */
    public Date now() {
        return new Date();
    }

    /**
     * 获取当前日期时间对象
     * @param offsetDay 偏移天数，正数为向后偏移，负数为向前偏移
     */
    public Date now(int offsetDay) {
        return DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, offsetDay);
    }

    /**
     * 获取指定日期偏移指定时间后的时间，生成的偏移日期不影响原日期
     * @param date      基准日期
     * @param offsetDay 偏移天数，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static DateTime dateOffset(Date date, int offsetDay) {
        return DateUtil.offset(date, DateField.DAY_OF_MONTH, offsetDay);
    }

    /**
     * 格式化日期(默认格式yyyy-MM-dd)
     * @param date 指定的时间
     * @return 返回日期格式化后的字符串
     */
    public String formatDate(Date date) {
        return XCI.formatDate(date);
    }

    /**
     * 格式化日期字符串(默认格式 yyyy-MM-dd HH:mm)
     * @param date 指定的时间
     * @return 返回日期格式化后的字符串
     */
    public String formatDateHasMinute(Date date) {
        return XCI.formatDateHasMinute(date);
    }

    /**
     * 格式化日期(默认格式 yyyy-MM-dd HH:mm:ss)
     * @param date 指定的时间
     * @return 返回日期格式化后的字符串
     */
    public String formatDateTime(Date date) {
        return XCI.formatDateTime(date);
    }

    /**
     * 获取格式化的时间字符串(默认格式 HH:mm:ss)
     * @param date 指定的时间
     * @return 返回日期格式化后的字符串
     */
    public String formatTime(Date date) {
        return XCI.formatTime(date);
    }

    /**
     * 格式化日期
     * @param date   指定的时间
     * @param format 格式字符串
     * @return 返回日期格式化后的字符串
     */
    public String formatDate(Date date, String format) {
        return XCI.formatDate(date, format);
    }

    /**
     * 以 conjunction 为分隔符将多个对象转换为字符串
     * @param conjunction 分隔符
     * @param objs        数组
     * @return 连接后的字符串
     * @see ArrayUtil#join(Object, CharSequence)
     */
    public String join(CharSequence conjunction, Object... objs) {
        return ArrayUtil.join(objs, conjunction);
    }

    public boolean contains(String source, String sub) {
        return source.toLowerCase().contains(sub.toLowerCase());
    }

    /**
     * 字符串是否为空白 空白的定义如下： <br>
     * 1、为null <br>
     * 2、为不可见字符（如空格）<br>
     * 3、""<br>
     * @param str 被检测的字符串
     * @return 是否为空
     */
    public boolean blank(CharSequence str) {
        return StrUtil.isBlank(str);
    }

    /**
     * 字符串是否为非空白 空白的定义如下： <br>
     * 1、不为null <br>
     * 2、不为不可见字符（如空格）<br>
     * 3、不为""<br>
     * @param str 被检测的字符串
     * @return 是否为非空
     */
    public boolean notBlank(CharSequence str) {
        return !blank(str);
    }

    /**
     * 获取Web项目下的web root路径
     * @return web root路径
     * @since 4.0.13
     */
    public File webRoot() {
        return FileUtil.getWebRoot();
    }

    /**
     * 获得文件的扩展名，扩展名不带“.”
     * @param fileName 文件名
     * @return 扩展名
     */
    public String extName(String fileName) {
        return FileUtil.extName(fileName);
    }

    /**
     * 返回主文件名
     * @param fileName 完整文件名
     * @return 主文件名
     */
    public String mainName(String fileName) {
        return FileUtil.mainName(fileName);
    }

    /**
     * json序列化
     * @param data 待序列化的对象
     */
    public String toJsonString(Object data) {
        return XCI.toJsonString(data);
    }

    /**
     * 获取客户端IP
     * @return IP地址
     */
    public String clientIP() {
        return ServletUtil.getClientIP(XCI.getRequest());
    }

    /**
     * 获取 request Attribute参数
     * @param name 属性名称
     */
    public String requstAttribute(String name) {
        var r = XCI.getRequest().getAttribute(name);
        return r == null ? R.Empty : r.toString();
    }

    /**
     * 获取 request Parameter参数
     * @param name 参数名称
     */
    public String requstParameter(String name) {
        return XCI.getRequest().getParameter(name);
    }

    /**
     * 获取请求上下文
     * @return 返回请求上下文
     */
    public String contextPath() {
        return XCI.getRequest().getContextPath();
    }

    /**
     * 获取请求上下文绝对路径
     * @param relativeUrl 指定的路径
     * @return 返回请求上下文绝对路径
     */
    public String url(String relativeUrl) {
        var u = resourceUrlProvider.getForLookupPath(relativeUrl);
        if (XCI.isBlank(u)) {
            return XCI.getRequest().getContextPath() + relativeUrl;
        }
        return u;
    }

    /**
     * 生成cdn Url
     * @param url 资源路径
     */
    public String cdn(String url) {
        var cdnRoot = webProperties.getCdn();
        if (cdnRoot.endsWith("/")) {
            cdnRoot = cdnRoot.substring(0, cdnRoot.length() - 1);
        }
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        return cdnRoot + url;
    }

    /**
     * 获取cdn服务器
     */
    public String cdnRoot() {
        return webProperties.getCdn();
    }

    /**
     * 根据参数编码获取参数值
     * @param code         参数编码
     * @param defaultValue 找不到参数时返回的默认值
     * @return 返回参数编码对应的参数值
     */
    public String param(String code, String defaultValue) {
        var result = application.getParam(code, defaultValue);
        if (!XCI.isBlank(result)) {
            return result.toString();
        }
        return R.Empty;
    }

    /**
     * 根据字典列表
     * @param code 字典类型编码
     */
    public List<Dic> dic(String code) {
        return application.getDic(code);
    }

    public String boolLabel(Object test, String trueLable, String falseLable) {
        return iif(test, "<span class=\"label label-success\">" + trueLable + "</span>",
                "<span class=\"label label-danger\">" + falseLable + "</span>");
    }

    public String boolLabel(Object test, String lable) {
        return boolLabel(test, lable, lable);
    }

    public String yesnoLabel(Object test) {
        return boolLabel(test, "是", "否");
    }

    public String statusLabel(Object test) {
        return boolLabel(test, "启用", "禁用");
    }

    public String iif(Object test, String out) {
        if (((test instanceof Integer) && ((Integer) test) == 1)
                || (((test instanceof Boolean) && ((Boolean) test)))) {
            return out;
        }
        return out;
    }

    public String iif(Object test, String trueOut, String falseOut) {
        if (((test instanceof Integer) && ((Integer) test) == 1)
                || (((test instanceof Boolean) && ((Boolean) test)))) {
            return trueOut;
        }
        return falseOut;
    }

    public String isReadonly(Object test) {
        return iif(test, "readonly", R.Empty);
    }

    public String isSelected(Object test) {
        return iif(test, "selected", R.Empty);
    }

    public String isChecked(Object test) {
        return iif(test, "checked", R.Empty);
    }

    public String isDisabled(Object test) {
        return iif(test, "disabled", R.Empty);
    }

    public String isActiveClass(Object test) {
        return iif(test, "class=\"active\"", R.Empty);
    }

    public String statusSelectOptions(String emptyLable, String enableLable,
                                      String disableLable, String selectedValue) {
        return XCI.format("<option value=\" \" {}>{}</option>", isSelected(selectedValue.equalsIgnoreCase(R.Empty)), emptyLable) +
                XCI.format("<option value=\"1\" {}>{}</option>", isSelected(selectedValue.equalsIgnoreCase("1")), enableLable) +
                XCI.format("<option value=\"0\" {}>{}</option>", isSelected(selectedValue.equalsIgnoreCase("0")), disableLable);
    }

    public String statusSelectOptions(String selectedValue) {
        return statusSelectOptions("全部状态", "启用", "禁用", selectedValue);
    }

    public String statusSelectOptions() {
        return statusSelectOptions(R.Empty);
    }

    public String selectOptions(List list, Object selectedValue) {
        return selectOptions(list, selectedValue, false);
    }
    public String selectOptions(List list, Object selectedValue, boolean isMultiple) {
        return selectOptions(list, selectedValue, isMultiple, "name", "id", "spell");
    }

    public String selectOptions(List list, Object selectedValue, boolean isMultiple,
                                String nameFieldName, String valueFieldName) {
        return selectOptions(list, selectedValue, isMultiple, nameFieldName, valueFieldName, null);
    }

    public String selectOptions(List list, Object selectedValue, boolean isMultiple,
                                String nameFieldName, String valueFieldName, String spellFieldName) {
        if (XCI.isBlank(nameFieldName)) throw new AppException("请指定[nameFieldName]");
        if (XCI.isBlank(valueFieldName)) throw new AppException("请指定[valueFieldName]");
        if (selectedValue == null) selectedValue = R.Empty;
        StringBuilder stringBuilder = new StringBuilder();
        for (Object item : list) {
            Object nameObj = ReflectUtil.getFieldValue(item, nameFieldName);
            Object valueObj = ReflectUtil.getFieldValue(item, valueFieldName);
            Object spellObj = null;
            if (XCI.isNotBlank(spellFieldName)) {
                spellObj = ReflectUtil.getFieldValue(item, spellFieldName);
            }
            String name = R.Empty;
            String value = R.Empty;
            String spell = R.Empty;
            if (nameObj != null) name = nameObj.toString();
            if (valueObj != null) value = valueObj.toString();
            if (spellObj != null) spell = spellObj.toString();

            var isSelected = isMultiple ? selectedValue.toString().contains(value) : selectedValue.equals(value);
            var spellkeys = XCI.isBlank(spell) ? R.Empty : XCI.format("data-keys=\"{}\"", spell);
            stringBuilder.append(XCI.format("<option value=\"{}\" {} {}>{}</option>", value, spellkeys, isSelected(isSelected), name));
        }
        return stringBuilder.toString();
    }

    public String dicSelectOptions(List<Dic> list, Object selectedValue, boolean isMultiple) {
        if (selectedValue == null) selectedValue = R.Empty;
        StringBuilder stringBuilder = new StringBuilder();
        for (Dic item : list) {
            String name = item.getName();
            String value = item.getValue();
            String spell = item.getSpell();
            var isSelected = isMultiple ? selectedValue.toString().contains(value) : selectedValue.equals(value);
            var spellkeys = XCI.isBlank(spell) ? R.Empty : XCI.format("data-keys=\"{}\"", spell);
            stringBuilder.append(XCI.format("<option value=\"{}\" {} {}>{}</option>", value, spellkeys, isSelected(isSelected), name));
        }
        return stringBuilder.toString();
    }

    public String dicSelectOptions(List<Dic> list, Object selectedValue) {
        return dicSelectOptions(list, selectedValue, false);
    }

    public String dicSelectOptions(String code, Object selectedValue, boolean isMultiple) {
        return dicSelectOptions(application.getDic(code), selectedValue, isMultiple);
    }

    public String dicSelectOptions(String code, Object selectedValue) {
        return dicSelectOptions(code, selectedValue, false);
    }
}