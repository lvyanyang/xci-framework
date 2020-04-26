/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

import java.util.HashMap;

/**
 * 字符串字典集合
 * @author 吕艳阳
 */
@SuppressWarnings("unchecked")
public class SMap extends HashMap<String, String> {

    public SMap append(String key, String value) {
        super.put(key, value);
        return this;
    }

    public SMap appendIfAbsent(String key, String value) {
          super.putIfAbsent(key, value);
        return this;
    }

    public static SMap newMap(String key, String value) {
        SMap map = new SMap();
        map.put(key, value);
        return map;
    }

    public static SMap newMap() {
        return new SMap();
    }
}
