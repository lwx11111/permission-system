package org.example.common.jwt;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.example.common.enums.Constants;
import org.example.common.utils.KeyUtils;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Component
@Slf4j
public class TokenProvider {
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS512;
    private SecretKeySpec signingKey = null;
    private PrivateKey privateKey = null;
    private PublicKey publicKey = null;

    @PostConstruct
    public void init() {

    }

    public TokenResponse createToken(JwtAccessToken jwtAccessToken, long TTLMills, Map<String, Object> params) {
        System.out.println("jwtAccessToken:");
        try {
            publicKey = KeyUtils.getPublicKey(Constants.RS512_PUBLICKEY);
            privateKey = KeyUtils.getPrivateKey(Constants.RS512_PRIVATEKEY);
        } catch (Exception e) {
            log.error("获取PublicKey，PrivateKey失败", e.getMessage());
        }

        TokenResponse tokenResponse = new TokenResponse();
        Map<String, Object> claims = new HashMap<>();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        claims.put(Claims.ID, "1.0");
        claims.put(Claims.ISSUER, jwtAccessToken.getIss());
        claims.put(Claims.SUBJECT, "Inspur-Auth-Manager");
        claims.put(Claims.AUDIENCE, jwtAccessToken.getSub());
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                claims.put(key, params.get(key));
            }
        }

        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);
        if (TTLMills >= 0) {
            long expMills = nowMillis + TTLMills;
            Date exp = new Date(expMills);
            jwtBuilder.setExpiration(exp).setNotBefore(now);
            tokenResponse.setExpTime(DateFormatUtils.format(exp, "yyyy-MM-dd HH:mm:ss"));
        }
        String value = signatureAlgorithm.getValue();
        //加密方式兼容
        String type1 = "HS512";
        String type2 = "RS512";
        if (type1.equals(value)) {
            jwtBuilder.signWith(signatureAlgorithm, signingKey);
        }
        if (type2.equals(value)){
            jwtBuilder.signWith(signatureAlgorithm, privateKey);
        }
        String token = jwtBuilder.compact();
        tokenResponse.setAccessToken(token);
        tokenResponse.setRenewal(TTLMills);
        return tokenResponse;
    }

    public Claims parseToken(String jsonWebToken) throws RuntimeException {
        Claims claims = null;
        try {
            if(privateKey==null){
                privateKey = KeyUtils.getPrivateKey(Constants.RS512_PRIVATEKEY);
            }
            claims = Jwts.parser().setAllowedClockSkewSeconds(60).setSigningKey(privateKey).parseClaimsJws(jsonWebToken).getBody();
        } catch (ExpiredJwtException ex) {
            log.error("登录凭证已经过期", ex);
//            throw new AuthException("登录凭证已经过期");
        } catch (Exception ex) {
            log.error("登录凭证不合法", ex);
//            throw new AuthException("登录凭证不合法");
        }
        return claims;
    }

}
