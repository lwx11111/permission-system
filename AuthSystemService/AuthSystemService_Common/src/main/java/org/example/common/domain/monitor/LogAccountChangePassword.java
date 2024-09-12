package org.example.common.domain.monitor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Map;
import java.time.LocalDateTime;
/**
 * <p>
 * 密码修改日志
 * </p>
 *
 * @author lwx
 * @since 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("log_account_change_password")
@Schema(name="密码修改日志_LogAccountChangePassword对象", description="密码修改日志")
public class LogAccountChangePassword extends Model<LogAccountChangePassword> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 账号ID
    */
    @Schema(description = "账号ID")
    @Excel(name = "账号ID")
    @TableField("account_id")
    private String accountId;

    /**
    * 旧密码
    */
    @Schema(description = "旧密码")
    @Excel(name = "旧密码")
    @TableField("old_pass")
    private String oldPass;

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
    * 账号名
    */
    @Schema(description = "账号名")
    @Excel(name = "账号名")
    @TableField("account_name")
    private String accountName;

    /**
    * 用户名
    */
    @Schema(description = "用户名")
    @Excel(name = "用户名")
    @TableField("user_name")
    private String userName;

    /**
    * 用户ID
    */
    @Schema(description = "用户ID")
    @Excel(name = "用户ID")
    @TableField("user_id")
    private String userId;

    @TableField(exist = false)
    private Map<String,String> params;


}
