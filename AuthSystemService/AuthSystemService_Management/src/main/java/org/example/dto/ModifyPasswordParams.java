package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author 刘文轩
 * @Date 2024/8/22 16:14
 */
@Data
public class ModifyPasswordParams {

    @NotBlank
    String accountId;

    @NotBlank
    String oldPass;

    @NotBlank
    String newPass;

    @NotBlank(message = "应用标识不为空")
    private String appId;

}
