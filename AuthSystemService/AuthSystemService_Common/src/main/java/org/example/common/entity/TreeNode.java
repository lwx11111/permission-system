package org.example.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author lwx
 * @since 2024/8/16 15:09
 */
@Data
public abstract class TreeNode {
    @TableField(exist = false)
    private String id;

    @TableField(exist = false)
    @JSONField(serialzeFeatures = SerializerFeature.WriteNullListAsEmpty)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<? extends TreeNode> children = null;

    public abstract String getIcon();

    public abstract String getParentId();

    public abstract String getPKAttrName();

    public abstract String getLeaf();
}
