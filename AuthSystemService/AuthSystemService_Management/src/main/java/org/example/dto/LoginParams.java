package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author 刘文轩
 * @Date 2024/8/20 14:13
 */
@Data
public class LoginParams {

    @NotBlank(message = "用户名不为空")
    private String username;

    @NotBlank(message = "密码不为空")
    private String password;

    @NotBlank(message = "应用标识不为空")
    private String appId;

    @NotBlank(message = "验证码不为空")
    private String verify;

    @NotBlank(message = "uuid不为空")
    private String uuid;

    private Boolean singleLogin;

}
