package org.example.common.domain.management;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
/**
 * <p>
 * 功能路由表
 * </p>
 *
 * @author lwx
 * @since 2024-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_function")
@Schema(name="功能路由表_SysFunction对象", description="功能路由表")
public class SysFunction extends Model<SysFunction> {

    private static final long serialVersionUID=1L;

    @Excel(name = "funId")
    @TableId(value = "fun_id", type = IdType.ASSIGN_ID)
    private String funId;

    /**
    * 类别 0-菜单 1-按钮
    */
    @Schema(description = "类别 0-菜单 1-按钮      ")
    @Excel(name = "类别 0-菜单 1-按钮      ")
    @TableField("fun_type")
    private String funType;

    /**
    * 功能编码
    */
    @Schema(description = "功能编码")
    @Excel(name = "功能编码")
    @TableField("fun_code")
    private String funCode;

    /**
    * 功能名称
    */
    @Schema(description = "功能名称")
    @Excel(name = "功能名称")
    @TableField("fun_name")
    private String funName;

    /**
    * 链接
    */
    @Schema(description = "链接")
    @Excel(name = "链接")
    @TableField("url")
    private String url;

    /**
    * 拓展链接
    */
    @Schema(description = "拓展链接")
    @Excel(name = "拓展链接")
    @TableField("extend_url")
    private String extendUrl;

    /**
    * 参数
    */
    @Schema(description = "参数")
    @Excel(name = "参数")
    @TableField("query")
    private String query;

    /**
    * 图标
    */
    @Schema(description = "图标")
    @Excel(name = "图标")
    @TableField("icon")
    private String icon;

    /**
    * 权限标识
    */
    @Schema(description = "权限标识")
    @Excel(name = "权限标识")
    @TableField("perm_key")
    private String permKey;

    /**
    * 上级路由id
    */
    @Schema(description = "上级路由id")
    @Excel(name = "上级路由id")
    @TableField("parent_id")
    private String parentId;

    /**
    * 应用标识
    */
    @Schema(description = "应用标识")
    @Excel(name = "应用标识")
    @TableField("app_id")
    private String appId;

    /**
    * 状态 1正常 0禁用
    */
    @Schema(description = "状态 1正常 0禁用")
    @Excel(name = "状态 1正常 0禁用")
    @TableField("status")
    private String status;

    /**
    * 创建人
    */
    @Schema(description = "创建人")
    @Excel(name = "创建人")
    @TableField("created_by")
    private String createdBy;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
    * 更新人
    */
    @Schema(description = "更新人")
    @Excel(name = "更新人")
    @TableField("updated_by")
    private String updatedBy;

    /**
    * 更新时间
    */
    @Schema(description = "更新时间")
    @Excel(name = "更新时间", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("updated_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    /**
    * 排序编码
    */
    @Schema(description = "排序编码")
    @Excel(name = "排序编码")
    @TableField("order_code")
    private String orderCode;

    @Excel(name = "leaf")
    @TableField("leaf")
    private String leaf;

    @Excel(name = "inherent")
    @TableField("inherent")
    private String inherent;

    @TableField(exist = false)
    private Map<String,String> params;

    @TableField(exist = false)
    private List<SysFunction> children;


}
