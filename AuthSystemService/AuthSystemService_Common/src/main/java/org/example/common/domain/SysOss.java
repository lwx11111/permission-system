package org.example.common.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author lwx20
 * @since 2023-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_oss")
@Schema(name="_SysOss对象", description="")
public class SysOss extends Model<SysOss> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
        @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 附件组ID
    */
    @Schema(description = "附件组ID")
    @Excel(name = "附件组ID")
    @TableField("group_id")
    private String groupId;

    /**
    * 附件原始名称
    */
    @Schema(description = "附件原始名称")
    @Excel(name = "附件原始名称")
    @TableField("name")
    private String name;

    /**
    * 附件存储名称
    */
    @Schema(description = "附件存储名称")
    @Excel(name = "附件存储名称")
    @TableField("storage_file_name")
    private String storageFileName;

    @ApiModelProperty(value = "存储桶")
    @Excel(name = "存储桶")
    @TableField("BUCKET")
    private String bucket;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    @Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("created_time")
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    /**
    * 创建人姓名
    */
    @Schema(description = "创建人姓名")
    @Excel(name = "创建人姓名")
    @TableField("created_by")
    private String createdBy;

    /**
    * 创建人ID
    */
    @Schema(description = "创建人ID")
    @Excel(name = "创建人ID")
    @TableField("created_by_id")
    private String createdById;

    /**
    * 状态（0删除，1有效）
    */
    @Schema(description = "状态（0删除，1有效）")
    @Excel(name = "状态（0删除，1有效）")
    @TableField("status")
    private Integer status;

    /**
    * 附件详细ID
    */
    @Schema(description = "附件详细ID")
    @Excel(name = "附件详细ID")
    @TableField("biz_id")
    private String bizId;

    /**
    * 拍照项名称
    */
    @Schema(description = "拍照项名称")
    @Excel(name = "拍照项名称")
    @TableField("tag_name")
    private String tagName;

    /**
    * 进度
    */
    @Schema(description = "进度")
    @Excel(name = "进度")
    @TableField("percentage")
    private Integer percentage;

    private String groupName;

    private String groupInfoId;

    private String groupInfoName;

    private Map<String, String> params;




}
