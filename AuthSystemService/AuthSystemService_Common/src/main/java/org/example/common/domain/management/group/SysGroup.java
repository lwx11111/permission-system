package org.example.common.domain.management.group;

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
import java.util.Map;

/**
 * <p>
 * 组管理
 * </p>
 *
 * @author lwx
 * @since 2024-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_group")
@Schema(name="组管理_SysGroup对象", description="组管理")
public class SysGroup extends Model<SysGroup> {

    private static final long serialVersionUID=1L;

    @Excel(name = "groupId")
    @TableId(value = "group_id", type = IdType.ASSIGN_ID)
    private String groupId;

    /**
    * 应用ID
    */
    @Schema(description = "应用ID")
    @Excel(name = "应用ID")
    @TableField("app_id")
    private String appId;

    /**
    * 组编码
    */
    @Schema(description = "组编码")
    @Excel(name = "组编码")
    @TableField("group_code")
    private String groupCode;

    /**
    * 组名称
    */
    @Schema(description = "组名称")
    @Excel(name = "组名称")
    @TableField("group_name")
    private String groupName;

    /**
    * 组描述
    */
    @Schema(description = "组描述")
    @Excel(name = "组描述")
    @TableField("description")
    private String description;

    /**
    * 排序编码
    */
    @Schema(description = "排序编码")
    @Excel(name = "排序编码")
    @TableField("order_code")
    private String orderCode;

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


}
