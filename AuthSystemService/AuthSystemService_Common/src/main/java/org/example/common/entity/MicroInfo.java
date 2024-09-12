package org.example.common.entity;

import lombok.Data;

@Data
public class MicroInfo {
    private String ip;

    private static final MicroInfo info = new MicroInfo();

    public static MicroInfo getInfo() {
        return info;
    }

}
