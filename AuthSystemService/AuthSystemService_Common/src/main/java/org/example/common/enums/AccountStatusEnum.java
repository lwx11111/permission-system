package org.example.common.enums;

public enum AccountStatusEnum {
    NORMAL("0", "正常"),
    LOCKED("1", "锁定"),
    DELETED("2", "逻辑删除");
    private String value;
    private String desc;

    private AccountStatusEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
