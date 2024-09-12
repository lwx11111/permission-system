package org.example.dto;

import lombok.Data;

/**
 * @Author 刘文轩
 * @Date 2024/9/7 10:15
 */
@Data
public class ListFunctionsByAccountParams {

    String roleId;

    String accountId;

    String appId;
}
