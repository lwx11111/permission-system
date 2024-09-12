package org.example.common.domain.management.notice;

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
 * 公共接受组
 * </p>
 *
 * @author lwx
 * @since 2024-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_notice_group_rela")
@Schema(name="公共接受组_SysNoticeGroupRela对象", description="公共接受组")
public class SysNoticeGroupRela extends Model<SysNoticeGroupRela> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 公共ID
    */
    @Schema(description = "公共ID")
    @Excel(name = "公共ID")
    @TableField("notice_id")
    private String noticeId;

    /**
    * 组ID
    */
    @Schema(description = "组ID")
    @Excel(name = "组ID")
    @TableField("group_id")
    private String groupId;

    @TableField(exist = false)
    private Map<String,String> params;


}
