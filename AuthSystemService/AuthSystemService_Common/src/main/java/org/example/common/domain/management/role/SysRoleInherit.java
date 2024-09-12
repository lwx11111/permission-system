package org.example.common.domain.management.role;

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
 * 角色继承表
 * </p>
 *
 * @author lwx
 * @since 2024-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_inherit")
@Schema(name="角色继承表_SysRoleInherit对象", description="角色继承表")
public class SysRoleInherit extends Model<SysRoleInherit> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 角色ID
    */
    @Schema(description = "角色ID")
    @Excel(name = "角色ID")
    @TableField("role_id")
    private String roleId;

    /**
    * 继承角色ID
    */
    @Schema(description = "继承角色ID")
    @Excel(name = "继承角色ID")
    @TableField("inherit_role_id")
    private String inheritRoleId;

    @TableField(exist = false)
    private Map<String,String> params;


}
