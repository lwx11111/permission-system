package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author 刘文轩
 * @Date 2024/9/5 13:56
 */
@Data
public class ListOrgsByCompanyParams {

    @NotBlank
    String appId;

    String accountId;

    String userId;

    @NotBlank
    String companyId;
}
