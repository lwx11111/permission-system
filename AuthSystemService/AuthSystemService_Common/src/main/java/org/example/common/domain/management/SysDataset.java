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
 * 数据集管理
 * </p>
 *
 * @author lwx
 * @since 2024-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dataset")
@Schema(name="数据集管理_SysDataset对象", description="数据集管理")
public class SysDataset extends Model<SysDataset> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 数据源id
    */
    @Schema(description = "数据源id")
    @Excel(name = "数据源id")
    @TableField("ds_id")
    private String dsId;

    /**
    * 应用id
    */
    @Schema(description = "应用id")
    @Excel(name = "应用id")
    @TableField("app_id")
    private String appId;

    /**
    * 编码
    */
    @Schema(description = "编码")
    @Excel(name = "编码")
    @TableField("dset_code")
    private String dsetCode;

    /**
    * 名称
    */
    @Schema(description = "名称")
    @Excel(name = "名称")
    @TableField("dset_name")
    private String dsetName;

    /**
    * 获取数据内容
    */
    @Schema(description = "获取数据内容")
    @Excel(name = "获取数据内容")
    @TableField("fetch_data_content")
    private String fetchDataContent;

    /**
    * 获取数据方式 1 REST接口 0数据源
    */
    @Schema(description = "获取数据方式 1 REST接口 0数据源")
    @Excel(name = "获取数据方式 1 REST接口 0数据源")
    @TableField("fetch_data_type")
    private String fetchDataType;

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
    * 表名
    */
    @Schema(description = "表名")
    @Excel(name = "表名")
    @TableField("res_table")
    private String resTable;

    /**
    * 主键列名
    */
    @Schema(description = "主键列名")
    @Excel(name = "主键列名")
    @TableField("primarykey_col")
    private String primarykeyCol;

    /**
    * 显示列名
    */
    @Schema(description = "显示列名")
    @Excel(name = "显示列名")
    @TableField("resname_col")
    private String resnameCol;

    /**
    * 过滤条件
    */
    @Schema(description = "过滤条件")
    @Excel(name = "过滤条件")
    @TableField("where_clause")
    private String whereClause;

    /**
    * 排序列名
    */
    @Schema(description = "排序列名")
    @Excel(name = "排序列名")
    @TableField("order_col")
    private String orderCol;

    @TableField(exist = false)
    private Map<String,String> params;

    @TableField(exist = false)
    private String dsetDataList;


}
