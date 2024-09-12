package org.example.utils;

import org.example.common.domain.management.SysOrg;
import org.example.common.domain.management.SysFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 刘文轩
 * @Date 2024/9/3 9:16
 */
public class TreeBuilder {

    public static List<SysFunction> buildTree(List<SysFunction> nodes) {
        // 所有节点的id映射
        Map<String, SysFunction> nodeMap = new HashMap<>();
        // 子节点的父节点id映射
        Map<String, List<SysFunction>> childrenMap = new HashMap<>();

        for (SysFunction node : nodes) {
            nodeMap.put(node.getFunId(), node);
            // 子节点
            if (node.getParentId() != null && !"0".equals(node.getParentId())) {
                childrenMap.putIfAbsent(node.getParentId(), new ArrayList<>());
            }
        }

        // 形成List
        for (SysFunction node : nodes) {
            if (node.getParentId() != null & !"0".equals(node.getParentId())) {
                childrenMap.get(node.getParentId()).add(node);
            }
        }

        // Build the tree
        List<SysFunction> parentNodes = new ArrayList<>();
        for (SysFunction node : nodes) {
            // 父节点
            if ("0".equals(node.getParentId())) {
                parentNodes.add(node);
            // 子节点
            } else {
                SysFunction parentNode = nodeMap.get(node.getParentId());
                if (parentNode != null) {
                    System.out.println(parentNode.getFunName());
                    // 初始化
                    if (parentNode.getChildren() == null) {
                        parentNode.setChildren(new ArrayList<>());
                    }
                    parentNode.getChildren().add(node);
                }
            }
        }

        return parentNodes;
    }


    public static List<SysOrg> buildTreeOrg(List<SysOrg> nodes) {
        // 所有节点的id映射
        Map<String, SysOrg> nodeMap = new HashMap<>();
        // 子节点的父节点id映射
        Map<String, List<SysOrg>> childrenMap = new HashMap<>();

        for (SysOrg node : nodes) {
            nodeMap.put(node.getOrgCode(), node);
            // 子节点
            if (node.getParentOrgCode() != null && !"0".equals(node.getParentOrgCode())) {
                childrenMap.putIfAbsent(node.getParentOrgCode(), new ArrayList<>());
            }
        }

        // 形成List
        for (SysOrg node : nodes) {
            if (node.getParentOrgCode() != null & !"0".equals(node.getParentOrgCode())) {
                childrenMap.get(node.getParentOrgCode()).add(node);
            }
        }

        // Build the tree
        List<SysOrg> parentNodes = new ArrayList<>();
        for (SysOrg node : nodes) {
            // 父节点
            if ("0".equals(node.getParentOrgCode())) {
                parentNodes.add(node);
                // 子节点
            } else {
                SysOrg parentNode = nodeMap.get(node.getParentOrgCode());
                if (parentNode != null) {
                    // 初始化
                    if (parentNode.getChildren() == null) {
                        parentNode.setChildren(new ArrayList<>());
                    }
                    parentNode.getChildren().add(node);
                }
            }
        }

        return parentNodes;
    }
}
