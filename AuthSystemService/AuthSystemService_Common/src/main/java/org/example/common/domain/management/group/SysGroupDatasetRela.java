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
 * 组数据集关系表
 * </p>
 *
 * @author lwx
 * @since 2024-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_group_dataset_rela")
@Schema(name="组数据集关系表_SysGroupDatasetRela对象", description="组数据集关系表")
public class SysGroupDatasetRela extends Model<SysGroupDatasetRela> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 数据集ID
    */
    @Schema(description = "数据集ID")
    @Excel(name = "数据集ID")
    @TableField("dset_id")
    private String dsetId;

    /**
    * 组ID
    */
    @Schema(description = "组ID")
    @Excel(name = "组ID")
    @TableField("group_id")
    private String groupId;

    /**
    * 数据ID
    */
    @Schema(description = "数据ID")
    @Excel(name = "数据ID")
    @TableField("data_id")
    private String dataId;

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

    @TableField(exist = false)
    private Map<String,String> params;


}
