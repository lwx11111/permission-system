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
 * 字典表
 * </p>
 *
 * @author lwx
 * @since 2024-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict")
@Schema(name="字典表_SysDict对象", description="字典表")
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 字典编码
    */
    @Schema(description = "字典编码")
    @Excel(name = "字典编码")
    @TableField("dict_code")
    private String dictCode;

    /**
    * 字典名称
    */
    @Schema(description = "字典名称")
    @Excel(name = "字典名称")
    @TableField("dict_name")
    private String dictName;

    /**
    * 字典值
    */
    @Schema(description = "字典值")
    @Excel(name = "字典值")
    @TableField("dict_val")
    private String dictVal;

    /**
    * 排序编码
    */
    @Schema(description = "排序编码")
    @Excel(name = "排序编码")
    @TableField("dict_order")
    private String dictOrder;

    /**
    * 描述
    */
    @Schema(description = "描述")
    @Excel(name = "描述")
    @TableField("dict_desc")
    private String dictDesc;

    @TableField(exist = false)
    private Map<String,String> params;


}
