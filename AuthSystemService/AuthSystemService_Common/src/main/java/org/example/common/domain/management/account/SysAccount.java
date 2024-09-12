package org.example.common.domain.management.account;

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
 * 用户账号信息表
 * </p>
 *
 * @author lwx
 * @since 2024-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_account")
@Schema(name="用户账号信息表_SysAccount对象", description="用户账号信息表")
public class SysAccount extends Model<SysAccount> {

    private static final long serialVersionUID=1L;

    /**
    * 账号ID
    */
    @Schema(description = "账号ID")
    @Excel(name = "账号ID")
    @TableId(value = "account_id", type = IdType.ASSIGN_ID)
    private String accountId;

    /**
    * 用户ID
    */
    @Schema(description = "用户ID")
    @Excel(name = "用户ID")
    @TableField("user_id")
    private String userId;

    /**
    * 用户名
    */
    @Schema(description = "用户名")
    @Excel(name = "用户名")
    @TableField("user_name")
    private String userName;

    /**
    * 应用ID
    */
    @Schema(description = "应用ID")
    @Excel(name = "应用ID")
    @TableField("app_id")
    private String appId;

    /**
    * 账号名
    */
    @Schema(description = "账号名")
    @Excel(name = "账号名")
    @TableField("account_name")
    private String accountName;

    /**
    * 密码
    */
    @Schema(description = "密码")
    @Excel(name = "密码")
    @TableField("login_pass")
    private String loginPass;

    /**
    * 状态0正常1锁定2逻辑删除
    */
    @Schema(description = "状态0正常1锁定2逻辑删除")
    @Excel(name = "状态0正常1锁定2逻辑删除")
    @TableField("status")
    private String status;

    /**
    * 登录失败次数
    */
    @Schema(description = "登录失败次数")
    @Excel(name = "登录失败次数")
    @TableField("login_fail_times")
    private Integer loginFailTimes;

    /**
    * 上次登录时间
    */
    @Schema(description = "上次登录时间")
    @Excel(name = "上次登录时间", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("last_login_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
    * 初始密码标识
    */
    @Schema(description = "初始密码标识")
    @Excel(name = "初始密码标识")
    @TableField("initial_password_flag")
    private String initialPasswordFlag;

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
    * 创建人
    */
    @Schema(description = "创建人")
    @Excel(name = "创建人")
    @TableField("created_by")
    private String createdBy;

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

    @Excel(name = "avatar")
    @TableField("avatar")
    private String avatar;

    @Excel(name = "allowWeek")
    @TableField("allow_week")
    private String allowWeek;

    @Excel(name = "endTime")
    @TableField("end_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Excel(name = "allowBeginTime")
    @TableField("allow_begin_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime allowBeginTime;

    @Excel(name = "allowEndTime")
    @TableField("allow_end_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime allowEndTime;

    @Excel(name = "description")
    @TableField("description")
    private String description;

    @Excel(name = "startTime")
    @TableField("start_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @TableField(exist = false)
    private Map<String,String> params;


}
