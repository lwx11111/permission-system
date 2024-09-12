package org.example.filters;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.common.domain.SysTokenBlackList;
import org.example.common.enums.Constants;
import org.example.common.jwt.TokenProvider;
import org.example.common.service.ISysTokenBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *  @author lwx
 */
@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Resource
    private TokenProvider tokenProvider;

    @Autowired
    private ISysTokenBlackListService tokenBlackListService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    //token前缀
    String BEARER_STARTSTR = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uri = exchange.getRequest().getURI().getPath();

        // 不需要拦截的路径/**/anon/**
        if (!antPathMatcher.match("/**/anon/**", uri)) {
            // 前端header里的token
            String token = exchange.getRequest().getHeaders().getFirst(Constants.TOKEN_NAME);

            if (StringUtils.isNotBlank(token)) {
                token = token.replace(BEARER_STARTSTR, "");
                // 解析token获取claims 储存基本信息
                Claims claims = tokenProvider.parseToken(token);
                if (claims != null) {
                    // 查看token是否在黑名单中
                    final SysTokenBlackList tokenBlackList = tokenBlackListService.getByToken(token);
                    if (tokenBlackList == null) {
                        String userId = claims.get(Constants.LOGIN_USER_ID).toString();
                        String accountId = claims.get(Constants.LOGIN_ACCOUNT_ID).toString();
                        String loginName = claims.get(Constants.LOGIN_ACCOUNT_NAME).toString();
                        String appId = claims.get(Constants.LOGIN_APP_ID).toString();
                        String userName = "";
                        try {
                            // 解决中文乱码问题
                            userName = URLEncoder.encode(claims.get(Constants.LOGIN_USER_NAME).toString(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            log.error("userName encode exception:{}", e);
                        }
                        // 将解密的用户信息存入header
                        ServerHttpRequest httpRequest = null;
                        httpRequest = exchange.getRequest().mutate().header(Constants.LOGIN_USER_ID, new String[]{userId})
                                .header(Constants.LOGIN_ACCOUNT_ID, new String[]{accountId})
                                .header(Constants.LOGIN_USER_NAME, new String[]{userName})
                                .header(Constants.LOGIN_APP_ID, new String[]{appId})
                                .header(Constants.LOGIN_ACCOUNT_NAME, new String[]{loginName})
                                .build();

                        ServerWebExchange webExchange = exchange.mutate().request(httpRequest).build();
                        return chain.filter(webExchange);
                    } else {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                } else {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -400;
    }
}
