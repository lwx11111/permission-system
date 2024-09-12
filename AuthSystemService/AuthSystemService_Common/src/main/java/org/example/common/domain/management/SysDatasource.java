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
import java.util.Map;

/**
 * <p>
 * 数据源管理
 * </p>
 *
 * @author lwx
 * @since 2024-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_datasource")
@Schema(name="数据源管理_SysDatasource对象", description="数据源管理")
public class SysDatasource extends Model<SysDatasource> {

    private static final long serialVersionUID=1L;

    @Excel(name = "dsId")
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
    * 数据源名称
    */
    @Schema(description = "数据源名称")
    @Excel(name = "数据源名称")
    @TableField("ds_name")
    private String dsName;

    /**
    * 数据源url
    */
    @Schema(description = "数据源url")
    @Excel(name = "数据源url")
    @TableField("ds_url")
    private String dsUrl;

    /**
    * 数据源驱动名
    */
    @Schema(description = "数据源驱动名")
    @Excel(name = "数据源驱动名")
    @TableField("driver_class")
    private String driverClass;

    /**
    * 数据源用户名
    */
    @Schema(description = "数据源用户名")
    @Excel(name = "数据源用户名")
    @TableField("ds_username")
    private String dsUsername;

    /**
    * 数据源密码
    */
    @Schema(description = "数据源密码")
    @Excel(name = "数据源密码")
    @TableField("ds_password")
    private String dsPassword;

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
