package org.example.common.domain.management;

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
 * 账号信息表
 * </p>
 *
 * @author lwx
 * @since 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@Schema(name="账号信息表_SysUser对象", description="账号信息表")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID=1L;

    @Excel(name = "userId")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private String userId;

    /**
    * 多租户应用ID
    */
    @Schema(description = "多租户应用ID")
    @Excel(name = "多租户应用ID")
    @TableField("app_id")
    private String appId;

    /**
    * 用户名
    */
    @Schema(description = "用户名")
    @Excel(name = "用户名")
    @TableField("user_name")
    private String userName;

    /**
    * 账号名
    */
    @Schema(description = "账号名")
    @Excel(name = "账号名")
    @TableField("account_name")
    private String accountName;

    /**
    * 用户类型 0内部 1外部
    */
    @Schema(description = "用户类型 0内部 1外部")
    @Excel(name = "用户类型 0内部 1外部")
    @TableField("employee_type")
    private Integer employeeType;

    /**
    * 状态 0正常 1锁定 2删除
    */
    @Schema(description = "状态 0正常 1锁定 2删除")
    @Excel(name = "状态 0正常 1锁定 2删除")
    @TableField("status")
    private String status;

    /**
    * 电子邮件
    */
    @Schema(description = "电子邮件")
    @Excel(name = "电子邮件")
    @TableField("email")
    private String email;

    /**
    * 移动电话
    */
    @Schema(description = "移动电话")
    @Excel(name = "移动电话")
    @TableField("mobile")
    private String mobile;

    /**
    * 公司ID
    */
    @Schema(description = "公司ID")
    @Excel(name = "公司ID")
    @TableField("company_id")
    private String companyId;

    /**
    * 部门ID
    */
    @Schema(description = "部门ID")
    @Excel(name = "部门ID")
    @TableField("org_code")
    private String orgCode;

    /**
    * 公司名称
    */
    @Schema(description = "公司名称")
    @Excel(name = "公司名称")
    @TableField("company_name")
    private String companyName;

    /**
    * 部门名称
    */
    @Schema(description = "部门名称")
    @Excel(name = "部门名称")
    @TableField("org_name")
    private String orgName;

    /**
    * 省ID
    */
    @Schema(description = "省ID")
    @Excel(name = "省ID")
    @TableField("province_id")
    private String provinceId;

    /**
    * 市ID
    */
    @Schema(description = "市ID")
    @Excel(name = "市ID")
    @TableField("city_id")
    private String cityId;

    /**
    * 县ID
    */
    @Schema(description = "县ID")
    @Excel(name = "县ID")
    @TableField("county_id")
    private String countyId;

    /**
    * 省名称
    */
    @Schema(description = "省名称")
    @Excel(name = "省名称")
    @TableField("province_name")
    private String provinceName;

    /**
    * 市名称
    */
    @Schema(description = "市名称")
    @Excel(name = "市名称")
    @TableField("city_name")
    private String cityName;

    /**
    * 县名称
    */
    @Schema(description = "县名称")
    @Excel(name = "县名称")
    @TableField("county_name")
    private String countyName;

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
    * 账号数
    */
    @Schema(description = "账号数")
    @Excel(name = "账号数")
    @TableField("account_number")
    private Integer accountNumber;

    @Excel(name = "sn")
    @TableField("sn")
    private String sn;

    @Excel(name = "facsimileTelephoneNumber")
    @TableField("facsimile_telephone_number")
    private String facsimileTelephoneNumber;

    @Excel(name = "description")
    @TableField("description")
    private String description;

    @Excel(name = "nation")
    @TableField("nation")
    private String nation;

    @Excel(name = "gender")
    @TableField("gender")
    private String gender;

    @Excel(name = "birthday")
    @TableField("birthday")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    @Excel(name = "passwordModifiedDate")
    @TableField("password_modified_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime passwordModifiedDate;

    @Excel(name = "c")
    @TableField("c")
    private String c;

    @Excel(name = "religion")
    @TableField("religion")
    private String religion;

    @Excel(name = "telephoneNumber")
    @TableField("telephone_number")
    private String telephoneNumber;

    @Excel(name = "startTime")
    @TableField("start_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Excel(name = "endTime")
    @TableField("end_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Excel(name = "idCardNumber")
    @TableField("id_card_number")
    private String idCardNumber;

    @Excel(name = "employeeNumber")
    @TableField("employee_number")
    private String employeeNumber;

    /**
    * 填写为数字，要求准确。例如：“12”表示12级
    */
    @Schema(description = "填写为数字，要求准确。例如：“12”表示12级")
    @Excel(name = "填写为数字，要求准确。例如：“12”表示12级")
    @TableField("user_level")
    private Integer userLevel;

    /**
    * 岗位名称。如：IT规划，系统管理员等
    */
    @Schema(description = "岗位名称。如：IT规划，系统管理员等")
    @Excel(name = "岗位名称。如：IT规划，系统管理员等")
    @TableField("level_name")
    private String levelName;

    /**
    * 定义员工套入职级，员工竞聘的岗位职级和实际职级会存在不一致，例如新员工入职后竞聘的岗位为10级，但是新员工实际职级可能只有7级，则7级为其套入职级。
    */
    @Schema(description = "定义员工套入职级，员工竞聘的岗位职级和实际职级会存在不一致，例如新员工入职后竞聘的岗位为10级，但是新员工实际职级可能只有7级，则7级为其套入职级。")
    @Excel(name = "定义员工套入职级，员工竞聘的岗位职级和实际职级会存在不一致，例如新员工入职后竞聘的岗位为10级，但是新员工实际职级可能只有7级，则7级为其套入职级。")
    @TableField("category")
    private Integer category;

    /**
    * 定义用户的业务职责编码，可为多值。例如：“财务”
    */
    @Schema(description = "定义用户的业务职责编码，可为多值。例如：“财务”")
    @Excel(name = "定义用户的业务职责编码，可为多值。例如：“财务”")
    @TableField("functions")
    private String functions;

    @Excel(name = "displayOrder")
    @TableField("display_order")
    private String displayOrder;

    @Excel(name = "duty")
    @TableField("duty")
    private String duty;

    @Excel(name = "positionLevel")
    @TableField("position_level")
    private Integer positionLevel;

    @Excel(name = "supporterCorpName")
    @TableField("supporter_corp_name")
    private String supporterCorpName;

    @Excel(name = "supporterDept")
    @TableField("supporter_dept")
    private String supporterDept;

    @Excel(name = "supporterCorpContact")
    @TableField("supporter_corp_contact")
    private String supporterCorpContact;

    @Excel(name = "supervisor")
    @TableField("supervisor")
    private String supervisor;

    @Excel(name = "post")
    @TableField("post")
    private String post;

    @Excel(name = "userNo")
    @TableField("user_no")
    private String userNo;

    @Excel(name = "user")
    @TableField("user")
    private String user;

    @Excel(name = "userRoleId")
    @TableField("user_role_id")
    private String userRoleId;

    @TableField(exist = false)
    private Map<String,String> params;


}
