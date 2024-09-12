package org.example.common.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JWT Token
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAccessToken {
    //该JWT的签发者
    private String iss;

    //在什么时候签发的
    private String iat;

    //什么时候过期，这里是一个Unix时间戳
    private Long exp;

    //接收该JWT的一方
    private String aud;

    //该JWT所面向的用户
    private String sub;
}
