package org.example.common.domain.monitor;

import java.math.BigDecimal;
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
 *
 * </p>
 *
 * @author lwx
 * @since 2024-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("log_platform_oper")
@Schema(name="_LogPlatformOper对象", description="")
public class LogPlatformOper extends Model<LogPlatformOper> {

    private static final long serialVersionUID=1L;

    @Excel(name = "logId")
    @TableId(value = "log_id", type = IdType.ASSIGN_ID)
    private String logId;

    /**
    * 账号ID
    */
    @Schema(description = "账号ID")
    @Excel(name = "账号ID")
    @TableField("account_id")
    private String accountId;

    /**
    * 用户ID
    */
    @Schema(description = "用户ID")
    @Excel(name = "用户ID")
    @TableField("user_id")
    private String userId;

    /**
    * 1增加 2删除 3修改 4查询 5其他 6登录
    */
    @Schema(description = "1增加 2删除 3修改 4查询 5其他 6登录")
    @Excel(name = "1增加 2删除 3修改 4查询 5其他 6登录")
    @TableField("type")
    private Integer type;

    /**
    * 4极敏感级 3敏感级 2	较敏感级 1低敏感级
    */
    @Schema(description = "4极敏感级 3敏感级 2	较敏感级 1低敏感级")
    @Excel(name = "4极敏感级 3敏感级 2	较敏感级 1低敏感级")
    @TableField("sensitive_level")
    private Integer sensitiveLevel;

    /**
    * 操作内容
    */
    @Schema(description = "操作内容")
    @Excel(name = "操作内容")
    @TableField("operation")
    private String operation;

    /**
    * 执行时间（毫秒）
    */
    @Schema(description = "执行时间（毫秒）")
    @Excel(name = "执行时间（毫秒）")
    @TableField("time")
    private BigDecimal time;

    /**
    * 方法名
    */
    @Schema(description = "方法名")
    @Excel(name = "方法名")
    @TableField("method")
    private String method;

    /**
    * 参数
    */
    @Schema(description = "参数")
    @Excel(name = "参数")
    @TableField("params")
    private String params;

    /**
    * 服务端IP
    */
    @Schema(description = "服务端IP")
    @Excel(name = "服务端IP")
    @TableField("server_ip")
    private String serverIp;

    /**
    * 客户端IP
    */
    @Schema(description = "客户端IP")
    @Excel(name = "客户端IP")
    @TableField("client_ip")
    private String clientIp;

    /**
    * 操作时间
    */
    @Schema(description = "操作时间")
    @Excel(name = "操作时间", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
    * 0:成功  1:失败
    */
    @Schema(description = "0:成功  1:失败")
    @Excel(name = "0:成功  1:失败")
    @TableField("success_flag")
    private String successFlag;

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


}
