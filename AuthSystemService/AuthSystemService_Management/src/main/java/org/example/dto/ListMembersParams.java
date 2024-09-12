package org.example.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author 刘文轩
 * @Date 2024/9/2 9:52
 */
@Data
public class ListMembersParams {

    private String groupId;

    @NotBlank
    private String appId;

    private Integer pageSize;

    private Integer pageNum;
}
