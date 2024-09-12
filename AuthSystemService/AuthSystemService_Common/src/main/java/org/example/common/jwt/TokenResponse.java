package org.example.common.jwt;

import lombok.Data;

@Data
public class TokenResponse {

    private String accessToken;

    private String expTime;

    private long renewal = 0L;

}
