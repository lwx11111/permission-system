package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author 刘文轩
 * @Date 2024/8/23 14:07
 */
@Data
public class RoleInheritanceParams {


    @NotBlank(message = "应用标识不为空")
    private String appId;


    @NotBlank(message = "角色ID不为空")
    private String roleId;
}
