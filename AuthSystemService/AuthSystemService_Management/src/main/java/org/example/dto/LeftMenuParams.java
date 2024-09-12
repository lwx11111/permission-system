package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author 刘文轩
 * @Date 2024/8/20 8:58
 */
@Data
public class LeftMenuParams {

    @NotBlank(message = "accountId不能为空")
    private String accountId;

    @NotBlank(message = "appId不能为空")
    private String appId;

}
