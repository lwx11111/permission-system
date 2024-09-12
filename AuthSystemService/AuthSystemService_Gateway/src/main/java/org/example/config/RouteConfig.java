package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class RouteConfig {

    private String ALLOWED_HEADERS = "x-requested-with,Content-Type,Authorization,credential,X-XSRF-TOKEN,token,client,TINY-REQUEST-ID";
    private String ALLOWED_METHODS = "*";
    private String ALLOWED_ORIGIN = "*";
    private String ALLOWED_Expose = "*";
    private String MAX_AGE = "18000L";
    private static final String URI = "http://127.0.0.1:8921";

    @Bean
    WebFilter corsFilter() {
        return (serverWebExchange, webFilterChain) -> {
            ServerHttpRequest request = serverWebExchange.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = serverWebExchange.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                headers.add("Access-Control-Max-Age", MAX_AGE);
                headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                headers.add("Access-Control-Expose-Headers", ALLOWED_Expose);
                headers.add("Access-Control-Allow-Credentials", "true");
                headers.add("X-Xss-Protection", "1 ; mode=block");
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return webFilterChain.filter(serverWebExchange);
        };
    }
}
