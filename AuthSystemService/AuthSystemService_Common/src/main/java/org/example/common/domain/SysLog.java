package org.example.common.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @author lwx
 * @since 2024-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_log")
@Schema(name="_SysLog对象", description="")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
        @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 操作id，各实体主键
    */
    @Schema(description = "操作id，各实体主键")
    @Excel(name = "操作id，各实体主键")
    @TableField("key_id")
    private String keyId;

    /**
    * 操作名称
    */
    @Schema(description = "操作名称")
    @Excel(name = "操作名称")
    @TableField("name")
    private String name;

    /**
    * 操作结果
    */
    @Schema(description = "操作结果")
    @Excel(name = "操作结果")
    @TableField("result")
    private String result;

    /**
     * 操作结果
     */
    @Schema(description = "操作入参")
    @Excel(name = "操作入参")
    @TableField("parameter")
    private String parameter;

    /**
    * 操作时间
    */
    @Schema(description = "操作时间")
    @Excel(name = "操作时间", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("time")
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    @TableField(exist = false)
    private Map<String,String> params;


}
