package org.example.common.domain.management;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Map;

/**
 * <p>
 * 项目表
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_app")
@Schema(name="项目表_SysApp对象", description="项目表")
public class SysApp extends Model<SysApp> {

    private static final long serialVersionUID=1L;

    @Excel(name = "appId")
        @TableId(value = "app_id", type = IdType.ASSIGN_ID)
    private String appId;

    @Excel(name = "appCode")
    @TableField("app_code")
    private String appCode;

    @Excel(name = "appName")
    @TableField("app_name")
    private String appName;

    @Excel(name = "icon")
    @TableField("icon")
    private String icon;

    @Excel(name = "remark")
    @TableField("remark")
    private String remark;

    @Excel(name = "createdBy")
    @TableField("created_by")
    private String createdBy;

    @Excel(name = "createTime")
    @TableField("create_time")
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Excel(name = "updatedBy")
    @TableField("updated_by")
    private String updatedBy;

    @Excel(name = "updatedTime")
    @TableField("updated_time")
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    /**
    * 0-不固定
            1-固定
    */
    @Schema(description = "0-不固定 ")
    @Excel(name = "0-不固定 ")
    @TableField("fixed")
    private String fixed;

    @Excel(name = "secretKey")
    @TableField("secret_key")
    private String secretKey;

    @Excel(name = "appKey")
    @TableField("app_key")
    private String appKey;

    @Excel(name = "status")
    @TableField("status")
    private String status;

    @TableField(exist = false)
    private Map<String,String> params;


}
