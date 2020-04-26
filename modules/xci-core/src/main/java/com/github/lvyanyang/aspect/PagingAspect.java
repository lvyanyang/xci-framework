/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.aspect;

import cn.hutool.core.util.StrUtil;
import com.github.lvyanyang.core.BasePageFilter;
import com.github.lvyanyang.core.R;
import com.github.lvyanyang.core.SMap;
import com.github.lvyanyang.core.XCI;
import com.github.pagehelper.PageHelper;
import com.github.lvyanyang.annotation.Paging;
import com.github.lvyanyang.annotation.Top;
import com.github.lvyanyang.configuration.ApiProperties;
import com.github.lvyanyang.exceptions.AppException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 数据库分页拦截器
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-25
 */
@Aspect
@Component
public class PagingAspect {
    @Resource private ApiProperties apiProperties;

    @Before("@annotation(com.github.lvyanyang.annotation.Top)")
    public void doTopDataList(JoinPoint joinPoint) {
        var method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        var fd = method.getAnnotation(Top.class);

        int maxPageSize = apiProperties.getMaxPageSize();
        int page = 1;
        int limit = fd.count();

        if (limit > maxPageSize) {
            throw new AppException(XCI.format("Top最大不能超过{}条记录,当前:{}", maxPageSize, limit));
        }

        PageHelper.startPage(page, limit, false);
    }

    @Before("@annotation(com.github.lvyanyang.annotation.Paging)")
    public void doPagingList(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        var pd = method.getAnnotation(Paging.class);
        Object[] methodArgs = joinPoint.getArgs();
        //Parameter[] methodParams = method.getParameters();
        var errMsg = "要启用数据库分页必须要有参数,参数类型为:BasePageFilter";
        XCI.ifTrueThrow(methodArgs.length == 0, errMsg);

        BasePageFilter pageObject = null;
        for (Object args : methodArgs) {
            // var param = methodParams[i];
            if (args instanceof BasePageFilter) {
                pageObject = (BasePageFilter) args;
                break;
            }
        }
        if (pageObject == null) throw new AppException(errMsg);

        int pageIndex = (pageObject.getPageIndex() != null ? pageObject.getPageIndex() : pd.defaultPageIndex());
        int pageSize = (pageObject.getPageSize() != null ? pageObject.getPageSize() : pd.defaultPageSize());
        String sortName = (XCI.isNotEmpty(pageObject.getSortName()) ? pageObject.getSortName() : pd.defaultSort());
        String sortDir = (XCI.isNotEmpty(pageObject.getSortDir()) ? pageObject.getSortDir() : pd.defaultSortDir());

        String orderBy = R.Empty;
        if (pd.allowSort() && !sortName.isEmpty()) {
            SMap sortNameMap = getSortNameMap(pd.sortNameMap());
            if (pd.allowMultiSort()) {
                orderBy = getMapMultiFieldSort(sortName, sortDir, pd.sortNameConvertType(), sortNameMap, pd.allowSortNames());
            } else {
                orderBy = getMapSingleFieldSort(sortName, sortDir, pd.sortNameConvertType(), sortNameMap, pd.allowSortNames());
            }
        }

        int maxPageSize = apiProperties.getMaxPageSize();
        if (pageIndex <= 0 || pageSize <= 0) {
            throw new RuntimeException("错误的分页参数,分页参数必须都是正整数并大于0.");
        }
        if (pageSize > maxPageSize) {
            throw new AppException(XCI.format("每页最大不能超过{}条记录,当前:{}", maxPageSize, pageSize));
        }
        if (!orderBy.isEmpty()) {
            PageHelper.startPage(pageIndex, pageSize, orderBy);
        } else {
            PageHelper.startPage(pageIndex, pageSize);
        }

        // Object obj = joinPoint.proceed();
        // if (obj instanceof Page) {
        //     var plist = (Page) obj;
        //     return PageList.of(plist);
        // }
        // return joinPoint.proceed();
    }

    // private PageModel convertToPageModel(Map<String, Object> params, DbPage pd) {
    //     String pageIndexField = pd.pageIndexField();
    //     String pageSizeField = pd.pageSizeField();
    //     String sortNameField = pd.sortNameField();
    //     String sortDirField = pd.sortDirField();
    //     PageModel model = new PageModel();
    //     model.setPageIndex(Convert.toInt(params.get(pageIndexField), null));
    //     model.setPageSize(Convert.toInt(params.get(pageSizeField), null));
    //     if (params.containsKey(sortNameField)) {
    //         model.setSortName(params.get(sortNameField).toString());
    //     }
    //     if (params.containsKey(sortDirField)) {
    //         model.setSortDir(params.get(sortDirField).toString());
    //     }
    //     return model;
    // }

    private SMap getSortNameMap(String sortNameMap) {
        SMap map = SMap.newMap();
        var sz = XCI.splitToArray(sortNameMap, ';');
        for (String item : sz) {
            var names = XCI.splitToArray(item, ':');
            if (names.length != 2) {
                continue;
            }
            map.put(names[0], names[1]);
        }
        return map;
    }

    private String getMapSingleFieldSort(String sortName, String sortDir, int sortNameConvertType, SMap sortNameMap, String allowSortNames) {
        if (sortName.isEmpty()) {
            return R.Empty;
        }
        if (sortDir.isEmpty()) {
            sortDir = "asc";
        }
        if (!allowSort(allowSortNames, sortName)) {
            XCI.appThrow(XCI.format("不允许使用[{}]排序", sortName));
        }
        sortName = getSortName(sortName, sortNameConvertType, sortNameMap);
        String orderBy = XCI.format("{} {}", sortName, sortDir);
        return XCI.escapeOrderBySql(orderBy);
    }

    private String getMapMultiFieldSort(String sortName, String sortDir, int sortNameConvertType, SMap sortNameMap, String allowSortNames) {
        String[] sorts = XCI.splitToArray(sortName);
        String[] orders = XCI.splitToArray(sortDir);

        if (XCI.isEmpty(sorts)) {
            return R.Empty;
        }
        if (XCI.isEmpty(orders)) {
            orders = new String[sorts.length];
            for (int i = 0; i < sorts.length; i++) {
                orders[i] = "asc";
            }
        }
        StringBuilder orderby = new StringBuilder();
        for (int i = 0; i < sorts.length; i++) {
            String sname = sorts[i];
            String sorder = orders[i];
            if (!allowSort(allowSortNames, sname)) {
                XCI.appThrow(XCI.format("不允许使用[{}]排序", sname));
            }
            sname = getSortName(sname, sortNameConvertType, sortNameMap);
            orderby.append(XCI.format("{} {}", sname, sorder));
            if (i != sorts.length - 1) {
                orderby.append(",");
            }
        }

        return XCI.escapeOrderBySql(orderby.toString());
    }

    private String getSortName(String name, int sortNameConvertType, SMap sortNameMap) {
        String resultName = name;
        String mapName = sortNameMap.get(name);
        if (XCI.isNotEmpty(mapName)) {
            resultName = mapName;
        }
        if (sortNameConvertType == 1) { //1驼峰转下划线
            return StrUtil.toUnderlineCase(resultName);
        } else if (sortNameConvertType == 2) { //2下划线转驼峰
            return StrUtil.toCamelCase(resultName);
        }
        return resultName;
    }

    private boolean allowSort(String allowSortNames, String name) {
        if (XCI.isBlank(allowSortNames)) {
            return true;
        }
        var sz = XCI.splitToArray(allowSortNames);
        return Arrays.asList(sz).contains(name);
    }
}