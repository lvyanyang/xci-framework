/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.core;

import cn.hutool.core.io.FileUtil;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 表示Json文件列表数据
 * @author 吕艳阳
 */
public class JsonListData<T> {
    private CopyOnWriteArrayList<T> data;
    private Class<T> tClass;
    private File file;

    /**
     * 初始化Json文件列表数据
     * @param file 文件对象
     */
    public JsonListData(File file,Class<T> dataClass) {
        this.tClass = dataClass;
        this.file = file;
        load();
    }

    /**
     * 从指定文件中加载数据
     */
    public void load() {
        if (file.exists()) {
            String content = FileUtil.readUtf8String(file);
            if (XCI.isNotBlank(content)) {
                data = XCI.toJsonListObject(content, CopyOnWriteArrayList.class, tClass);
            }
        }
        if (data == null) {
            data = new CopyOnWriteArrayList<>();
        }
    }

    /**
     * 重新加载数据
     */
    public void reload() {
        load();
    }

    /**
     * 保存数据到指定文件中
     */
    public void save() {
        save(true);
    }

    /**
     * 保存数据到指定文件中
     *
     * @param isPretty 是否格式化保存
     */
    public void save(boolean isPretty) {
        String content = XCI.toJsonString(data, isPretty);
        FileUtil.mkParentDirs(file);
        FileUtil.writeUtf8String(content, file);
    }

    /**
     * 检查唯一性
     */
    public Boolean exist(@NotNull Function<List<T>, Boolean> checker) {
        return checker.apply(data);
    }

    /**
     * 添加数据,自动保存
     *
     * @param entity 数据对象
     */
    public void add(T entity) {
        data.add(entity);
        save();
    }

    /**
     * 添加数据,自动保存
     *
     * @param entities 数据对象集合
     */
    public void add(List<T> entities) {
        data.addAll(entities);
        save();
    }

    /**
     * 修改数据,自动保存
     *
     * @param entity    数据对象
     * @param predicate 更新条件
     */
    public void update(T entity, Function<T, Boolean> predicate) {
        if (XCI.updateListItem(data, entity, predicate)) {
            save();
        }
    }

    /**
     * 删除数据,自动保存
     *
     * @param action 执行过程
     */
    public void remove(@NotNull Consumer<List<T>> action) {
        action.accept(data);
        save();
    }

    /**
     * 数据处理
     *
     * @param action 执行过程
     */
    public void execute(@NotNull Consumer<List<T>> action) {
        action.accept(data);
    }

    /**
     * 根据条件查询单个对象
     *
     * @param predicate 搜索条件
     * @return 返回符合条件的单个对象
     */
    public T get(@NotNull Predicate<T> predicate) {
        return data.stream().filter(predicate).findFirst().orElse(null);
    }

    /**
     * 获取列表数据
     */
    public List<T> getInnerList() {
        return data;
    }

    /**
     * 获取列表数据
     *
     * @param predicate 过滤条件,可以为空
     */
    public List<T> getList(Predicate<T> predicate) {
        List<T> srcList = data;
        if (predicate != null) {
            srcList = data.stream().filter(predicate).collect(Collectors.toList());
        }
        return new ArrayList<>(srcList);
    }
}
