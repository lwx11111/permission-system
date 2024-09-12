package org.example.dto;

import lombok.Data;
import org.example.common.domain.management.group.SysGroup;
import org.example.common.domain.management.group.SysGroupMembers;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 刘文轩
 * @Date 2024/9/2 11:13
 */
@Data
public class SaveSysGroupParams {

    private SysGroup group;

    private SysGroupMembers groupMembers;

    /**
     * 成员信息相关
     */

    private String[] accountIds;

    private List<String> functions;

    private List<String> dataset;

    private LocalDateTime createTime;

    private String createdBy;
}
