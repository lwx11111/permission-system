package org.example.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author 刘文轩
 * @Date 2024/9/7 11:05
 */
@Data
public class SavePermParams {

    private String accountId;

    private List<String> checkRoles;

    private List<String> checkGroups;

    private List<String> privateMenus;
}
