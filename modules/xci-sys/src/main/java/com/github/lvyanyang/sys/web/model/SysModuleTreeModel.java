/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.web.model;

import com.github.lvyanyang.model.BeanDiff;
import com.github.lvyanyang.sys.entity.SysModule;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统模块树形模型
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-28 16:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysModuleTreeModel extends SysModule {
    /**
     * 节点展开状态
     */
    public String state;

    /**
     * 节点图标
     */
    public String iconCls;

    public String getIconCls() {
        return super.getWebCls();
    }

    /**
     * 转为模块树形模型
     * @param modules 系统模块列表
     */
    public static List<SysModuleTreeModel> toTreeList(List<SysModule> modules){
        List<SysModuleTreeModel> list = new ArrayList<>(modules.size());
        for (var item : modules) {
            var treeModel = new SysModuleTreeModel();
            BeanUtils.copyProperties(item, treeModel);
            boolean hasChild = modules.stream().anyMatch(p -> p.getParentId().equals(item.getId()));
            // item.setLeaf(hasChild ? 0 : 1);
            String state = "open";
            if (!item.getExpand() && hasChild) {
                state = "closed";
            }
            treeModel.setState(state);
            list.add(treeModel);
        }
        return list;
    }
}
