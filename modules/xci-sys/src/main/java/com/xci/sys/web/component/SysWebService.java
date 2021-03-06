/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.sys.web.component;

import com.xci.core.XCI;
import com.xci.sys.entity.SysDept;
import com.xci.sys.entity.SysDic;
import com.xci.sys.entity.SysDicCategory;
import com.xci.sys.entity.SysModule;
import com.xci.sys.web.model.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SysWebService {
    private static SysWebService me;

    @PostConstruct
    private void init() {
        SysWebService.me = this;
    }

    public static SysWebService me() {
        return me;
    }

    /**
     * 模块列表转为树节点列表
     * @param moduleList 模块列表
     */
    public List<TreeNode> toModuleNodeList(List<SysModule> moduleList) {
        List<TreeNode> nodes = new ArrayList<>();
        if (ObjectUtils.isEmpty(moduleList)) return nodes;

        for (var item : moduleList) {
            boolean hasChild = moduleList.stream().anyMatch(p -> p.getParentId().equals(item.getId()));
            TreeNode node = new TreeNode();
            node.setId(item.getId().toString());
            node.setPid(item.getParentId().toString());
            node.setText(item.getName());
            node.setSpell(item.getSpell());
            node.setLeaf(hasChild ? 0 : 1);
            node.setIconCls(item.getWebCls());
            node.setUrl(item.getWebUrl());
            String state = "open";
            if (!item.getExpand() && hasChild) {
                state = "closed";
            }
            node.setState(state);
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * 机构列表转为树节点列表
     * @param deptList 机构列表
     */
    public List<TreeNode> toDeptNodeList(List<SysDept> deptList) {
        List<TreeNode> nodes = new ArrayList<>();
        if (ObjectUtils.isEmpty(deptList)) return nodes;

        for (var item : deptList) {
            boolean hasChild = deptList.stream().anyMatch(p -> p.getParentId().equals(item.getId()));
            TreeNode node = new TreeNode();
            node.setId(item.getId().toString());
            node.setPid(item.getParentId().toString());
            node.setText(item.getName());
            node.setSpell(item.getSpell());
            node.setLeaf(hasChild ? 0 : 1);
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * 字典列表转为树节点列表
     * @param dicList 字典列表
     */
    public List<TreeNode> dicListToTreeNodeList(List<SysDic> dicList) {
        List<TreeNode> nodes = new ArrayList<>();
        if (ObjectUtils.isEmpty(dicList)) return nodes;

        for (var item : dicList) {
            boolean hasChild = dicList.stream().anyMatch(p -> p.getParentId().equals(item.getId()));
            TreeNode node = new TreeNode();
            node.setId(item.getId().toString());
            node.setPid(item.getParentId().toString());
            node.setText(item.getName());
            node.setSpell(item.getSpell());
            node.setLeaf(hasChild ? 0 : 1);
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * 字典类型列表转为树节点列表
     * @param dicCategoryList 字典类型列表
     */
    public List<TreeNode> dicCategoryListToTreeNodeList(List<SysDicCategory> dicCategoryList) {
        List<TreeNode> nodes = new ArrayList<>();
        if (ObjectUtils.isEmpty(dicCategoryList)) return nodes;

        for (var item : dicCategoryList) {
            boolean hasChild = dicCategoryList.stream().anyMatch(p -> p.getParentId().equals(item.getId()));
            TreeNode node = new TreeNode();
            node.setId(item.getId().toString());
            node.setPid(item.getParentId().toString());
            node.setText(item.getName());
            node.setSpell(XCI.getSpell(item.getName()));
            node.setLeaf(hasChild ? 0 : 1);
            nodes.add(node);
        }
        return nodes;
    }
}
