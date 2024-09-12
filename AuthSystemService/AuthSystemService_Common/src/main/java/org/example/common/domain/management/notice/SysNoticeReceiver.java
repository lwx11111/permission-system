package org.example.common.domain.management.notice;

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
 * 通知接收表
 * </p>
 *
 * @author lwx
 * @since 2024-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_notice_receiver")
@Schema(name="通知接收表_SysNoticeReceiver对象", description="通知接收表")
public class SysNoticeReceiver extends Model<SysNoticeReceiver> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 通知ID
    */
    @Schema(description = "通知ID")
    @Excel(name = "通知ID")
    @TableField("notice_id")
    private String noticeId;

    /**
    * 账号ID
    */
    @Schema(description = "账号ID")
    @Excel(name = "账号ID")
    @TableField("account_id")
    private String accountId;

    /**
    * 是否已读
    */
    @Schema(description = "是否已读")
    @Excel(name = "是否已读")
    @TableField("read_flag")
    private String readFlag;

    /**
    * 已读时间
    */
    @Schema(description = "已读时间")
    @Excel(name = "已读时间", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("read_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime readTime;

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
