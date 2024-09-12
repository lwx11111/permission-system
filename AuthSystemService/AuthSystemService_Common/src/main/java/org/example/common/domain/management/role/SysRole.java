package org.example.common.domain.management.role;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lwx
 * @since 2024-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
@Schema(name="角色表_SysRole对象", description="角色表")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID=1L;

    @Excel(name = "roleId")
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private String roleId;

    /**
    * 角色编码
    */
    @Schema(description = "角色编码")
    @Excel(name = "角色编码")
    @TableField("role_code")
    private String roleCode;

    /**
    * 应用标识
    */
    @Schema(description = "应用标识")
    @Excel(name = "应用标识")
    @TableField("app_id")
    private String appId;

    /**
    * 角色名称
    */
    @Schema(description = "角色名称")
    @Excel(name = "角色名称")
    @TableField("role_name")
    private String roleName;

    /**
    * 角色描述
    */
    @Schema(description = "角色描述")
    @Excel(name = "角色描述")
    @TableField("remark")
    private String remark;

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
    * 修改人
    */
    @Schema(description = "修改人")
    @Excel(name = "修改人")
    @TableField("updated_by")
    private String updatedBy;

    /**
    * 修改时间
    */
    @Schema(description = "修改时间")
    @Excel(name = "修改时间", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("updated_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    @Excel(name = "orderCode")
    @TableField("order_code")
    private String orderCode;

    @TableField(exist = false)
    private Map<String,String> params;

    @TableField(exist = false)
    private List<String> functions;

    @TableField(exist = false)
    private List<String> dataset;

    @TableField(exist = false)
    private List<String> inheritRoles;



}
