package org.example.dto;

import lombok.Data;

/**
 * @Author 刘文轩
 * @Date 2024/9/5 13:23
 */
@Data
public class ListCompanyParams {

    private String companyId;

    private String companyName;

    private String status;

    private String appId;
}
