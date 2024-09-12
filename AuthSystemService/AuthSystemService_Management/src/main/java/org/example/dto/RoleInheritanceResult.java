package org.example.dto;

import lombok.Data;
import org.example.common.domain.management.role.SysRole;

import java.util.List;

/**
 * @Author 刘文轩
 * @Date 2024/8/23 14:08
 */
@Data
public class RoleInheritanceResult {

    private List<SysRole> roles;

    private List<String> inheritRoles;


}
