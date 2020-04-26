/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import java.util.HashMap;

/**
 * 通用字典集合
 * @author 吕艳阳
 */
@SuppressWarnings("unchecked")
public class GMap extends HashMap<String, Object> {

    public GMap append(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public GMap appendIfAbsent(String key, Object value) {
          super.putIfAbsent(key, value);
        return this;
    }

    public static GMap newMap(String key, Object value) {
        GMap map = new GMap();
        map.put(key, value);
        return map;
    }

    public static GMap newMap() {
        return new GMap();
    }
}
