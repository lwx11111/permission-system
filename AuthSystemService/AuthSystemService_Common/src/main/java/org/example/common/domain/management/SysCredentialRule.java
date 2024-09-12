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
 * 系统密码规则
 * </p>
 *
 * @author lwx
 * @since 2024-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_credential_rule")
@Schema(name="系统密码规则_SysCredentialRule对象", description="系统密码规则")
public class SysCredentialRule extends Model<SysCredentialRule> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 规则名称
    */
    @Schema(description = "规则名称")
    @Excel(name = "规则名称")
    @TableField("rule_desc")
    private String ruleDesc;

    /**
    * 用户自定义 1自定义 0 不自定义
    */
    @Schema(description = "用户自定义 1自定义 0 不自定义")
    @Excel(name = "用户自定义 1自定义 0 不自定义")
    @TableField("user_define")
    private String userDefine;

    /**
    * 自定义表达式
    */
    @Schema(description = "自定义表达式")
    @Excel(name = "自定义表达式")
    @TableField("user_define_exp")
    private String userDefineExp;

    /**
    * 特殊字符个数
    */
    @Schema(description = "特殊字符个数")
    @Excel(name = "特殊字符个数")
    @TableField("specific_char_num")
    private Integer specificCharNum;

    /**
    * 大写字母个数
    */
    @Schema(description = "大写字母个数")
    @Excel(name = "大写字母个数")
    @TableField("big_letter_num")
    private Integer bigLetterNum;

    /**
    * 小写字母个数
    */
    @Schema(description = "小写字母个数")
    @Excel(name = "小写字母个数")
    @TableField("small_letter_num")
    private Integer smallLetterNum;

    /**
    * 数字个数
    */
    @Schema(description = "数字个数")
    @Excel(name = "数字个数")
    @TableField("number_num")
    private Integer numberNum;

    /**
    * 最小长度
    */
    @Schema(description = "最小长度")
    @Excel(name = "最小长度")
    @TableField("min_length")
    private Integer minLength;

    /**
    * 状态 0-不生效 1-生效
    */
    @Schema(description = "状态 0-不生效 1-生效")
    @Excel(name = "状态 0-不生效 1-生效")
    @TableField("active_status")
    private String activeStatus;

    /**
    * 历史密码不重复次数 0无限制
    */
    @Schema(description = "历史密码不重复次数 0无限制")
    @Excel(name = "历史密码不重复次数 0无限制")
    @TableField("history_repeat_times")
    private Integer historyRepeatTimes;

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
    * 应用ID
    */
    @Schema(description = "应用ID")
    @Excel(name = "应用ID")
    @TableField("app_id")
    private String appId;

    @TableField(exist = false)
    private Map<String,String> params;


}
