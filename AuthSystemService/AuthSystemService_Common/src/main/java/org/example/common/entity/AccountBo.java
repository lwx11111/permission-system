package org.example.common.entity;


import lombok.Data;
import org.example.common.domain.management.account.SysAccount;
import org.example.common.domain.management.SysUser;

@Data
public class AccountBo {
    private SysUser user;
    private SysAccount account;
    private boolean superAdmin = false;

}
