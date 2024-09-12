package org.example.common.domain.management;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Map;

/**
 * <p>
 * 公司表
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_company")
@Schema(name="公司表_SysCompany对象", description="公司表")
public class SysCompany extends Model<SysCompany> {

    private static final long serialVersionUID=1L;

    @Excel(name = "companyId")
        @TableId(value = "company_id", type = IdType.ASSIGN_ID)
    private String companyId;

    @Excel(name = "address")
    @TableField("address")
    private String address;

    @Excel(name = "companyName")
    @TableField("company_name")
    private String companyName;

    @Excel(name = "abbreviation")
    @TableField("abbreviation")
    private String abbreviation;

    @Excel(name = "description")
    @TableField("description")
    private String description;

    @Excel(name = "createdBy")
    @TableField("created_by")
    private String createdBy;

    @Excel(name = "createTime")
    @TableField("create_time")
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Excel(name = "updatedBy")
    @TableField("updated_by")
    private String updatedBy;

    @Excel(name = "updatedTime")
    @TableField("updated_time")
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    /**
    * 0-正常
            1-删除
    */
    @Schema(description = "0-正常 ")
    @Excel(name = "0-正常 ")
    @TableField("status")
    private String status;

    @Excel(name = "postalCode")
    @TableField("postal_code")
    private String postalCode;

    @Excel(name = "appId")
    @TableField("app_id")
    private String appId;

    @TableField(exist = false)
    private Map<String,String> params;


}
