package org.example.common.domain.management;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Map;

/**
 * <p>
 * 设置表
 * </p>
 *
 * @author lwx
 * @since 2024-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_setting")
@Schema(name="设置表_SysSetting对象", description="设置表")
public class SysSetting extends Model<SysSetting> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 设置编码
    */
    @Schema(description = "设置编码")
    @Excel(name = "设置编码")
    @TableField("setting_code")
    private String settingCode;

    /**
    * 设置名称
    */
    @Schema(description = "设置名称")
    @Excel(name = "设置名称")
    @TableField("setting_name")
    private String settingName;

    /**
    * 设置值
    */
    @Schema(description = "设置值")
    @Excel(name = "设置值")
    @TableField("setting_val")
    private String settingVal;

    /**
    * 应用ID
    */
    @Schema(description = "应用ID")
    @Excel(name = "应用ID")
    @TableField("app_id")
    private String appId;

    /**
    * 设置描述
    */
    @Schema(description = "设置描述")
    @Excel(name = "设置描述")
    @TableField("description")
    private String description;

    @TableField(exist = false)
    private Map<String,String> params;


}
