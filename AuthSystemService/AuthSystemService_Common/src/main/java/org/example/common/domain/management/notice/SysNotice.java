package org.example.common.domain.management.notice;

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
 *
 * </p>
 *
 * @author lwx
 * @since 2024-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_notice")
@Schema(name="_SysNotice对象", description="")
public class SysNotice extends Model<SysNotice> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 应用ID
    */
    @Schema(description = "应用ID")
    @Excel(name = "应用ID")
    @TableField("app_id")
    private String appId;

    /**
    * 公告标题
    */
    @Schema(description = "公告标题")
    @Excel(name = "公告标题")
    @TableField("notice_title")
    private String noticeTitle;

    /**
    * 公告内容
    */
    @Schema(description = "公告内容")
    @Excel(name = "公告内容")
    @TableField("notice_content")
    private String noticeContent;

    /**
    * 影响范围
    */
    @Schema(description = "影响范围")
    @Excel(name = "影响范围")
    @TableField("influence_scope")
    private String influenceScope;

    /**
    * 公告原因
    */
    @Schema(description = "公告原因")
    @Excel(name = "公告原因")
    @TableField("notice_reason")
    private String noticeReason;

    /**
    * 联系人姓名
    */
    @Schema(description = "联系人姓名")
    @Excel(name = "联系人姓名")
    @TableField("contact_person")
    private String contactPerson;

    /**
    * 联系人电话
    */
    @Schema(description = "联系人电话")
    @Excel(name = "联系人电话")
    @TableField("contact_person_tel")
    private String contactPersonTel;

    /**
    * 备注
    */
    @Schema(description = "备注")
    @Excel(name = "备注")
    @TableField("remark")
    private String remark;

    /**
    * 状态
    */
    @Schema(description = "状态")
    @Excel(name = "状态")
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

    @TableField(exist = false)
    private Map<String,String> params;

    @TableField(exist = false)
    private List<String> groupIds;



}
