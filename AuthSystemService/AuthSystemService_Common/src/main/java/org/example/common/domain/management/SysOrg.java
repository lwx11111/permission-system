package org.example.common.domain.management;

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
 * 公司部门表
 * </p>
 *
 * @author lwx
 * @since 2024-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_org")
@Schema(name="公司部门表_SysOrg对象", description="公司部门表")
public class SysOrg extends Model<SysOrg> {

    private static final long serialVersionUID=1L;

    /**
    * 部门编码
    */
    @Schema(description = "部门编码")
    @Excel(name = "部门编码")
    @TableId(value = "org_code", type = IdType.ASSIGN_ID)
    private String orgCode;

    /**
    * 公司ID
    */
    @Schema(description = "公司ID")
    @Excel(name = "公司ID")
    @TableField("company_id")
    private String companyId;

    /**
    * 部门名称
    */
    @Schema(description = "部门名称")
    @Excel(name = "部门名称")
    @TableField("org_name")
    private String orgName;

    /**
    * 部门形态 1公司 2部门 3外部组织
    */
    @Schema(description = "部门形态 1公司 2部门 3外部组织")
    @Excel(name = "部门形态 1公司 2部门 3外部组织")
    @TableField("style")
    private String style;

    /**
    * 状态 0正常 1锁定
    */
    @Schema(description = "状态 0正常 1锁定")
    @Excel(name = "状态 0正常 1锁定")
    @TableField("status")
    private String status;

    /**
    * 部门负责人
    */
    @Schema(description = "部门负责人")
    @Excel(name = "部门负责人")
    @TableField("org_manager")
    private String orgManager;

    /**
    * 描述
    */
    @Schema(description = "描述")
    @Excel(name = "描述")
    @TableField("description")
    private String description;

    /**
    * 父级编码
    */
    @Schema(description = "父级编码")
    @Excel(name = "父级编码")
    @TableField("parent_org_code")
    private String parentOrgCode;

    /**
    * 部门全路径
    */
    @Schema(description = "部门全路径")
    @Excel(name = "部门全路径")
    @TableField("org_path")
    private String orgPath;

    /**
    * 排序编码
    */
    @Schema(description = "排序编码")
    @Excel(name = "排序编码")
    @TableField("org_order")
    private String orgOrder;

    /**
    * 业务职责
    */
    @Schema(description = "业务职责")
    @Excel(name = "业务职责")
    @TableField("responsibility")
    private String responsibility;

    /**
    * 上级主管领导的UID
    */
    @Schema(description = "上级主管领导的UID")
    @Excel(name = "上级主管领导的UID")
    @TableField("supervisor")
    private String supervisor;

    /**
    * 部门电话
    */
    @Schema(description = "部门电话")
    @Excel(name = "部门电话")
    @TableField("telephone_number")
    private String telephoneNumber;

    /**
    * 传真电话
    */
    @Schema(description = "传真电话")
    @Excel(name = "传真电话")
    @TableField("facsimile_telephone_number")
    private String facsimileTelephoneNumber;

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
    * 应用ID
    */
    @Schema(description = "应用ID")
    @Excel(name = "应用ID")
    @TableField("app_id")
    private String appId;

    @TableField(exist = false)
    List<SysOrg> children;

    @TableField(exist = false)
    private Map<String,String> params;


}
