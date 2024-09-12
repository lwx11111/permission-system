package org.example.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class RequestUtils {
    public static String requestBody(HttpServletRequest request) {
        String json = "";
        try (DataInputStream in = new DataInputStream(request.getInputStream());) {
            int bytes = request.getContentLength();
            byte[] dataOrgin = new byte[bytes];
            in.readFully(dataOrgin);
            json = new String(dataOrgin,"UTF-8");
        } catch (IOException e) {
            log.error("获取请求内容异常{}", e.getMessage());
        }
        return json;
    }


    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                try {
                    ip = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException unknownhostexception) {
                    log.error("获取IP地址异常", unknownhostexception);
                }
            }
        } else {
            if (StringUtils.isNotBlank(ip)) {
                String[] split = ip.split(",");
                ip = split[0];
            }
        }
        return ip;
    }

    public static Map<String, Object> convertToMap(HttpServletRequest request) {
        Map<String, Object> params = null;
        String contentType = request.getContentType();
        if (StringUtils.equalsIgnoreCase(contentType, "application/json")) {//json
            String json = RequestUtils.requestBody(request);
        } else {
            Map<String, String[]> map = request.getParameterMap();
            params = map.keySet().stream().collect(Collectors.toMap(key -> key, key -> {
                String[] values = map.getOrDefault(key, new String[]{""});
                if (values.length > 0) {
                    String vv = values[0];
                    try {
                        return values[0];
                    } catch (Exception e) {
                        log.error("参数转换类型异常", e);
                        return values[0];
                    }
                }
                return "";
            }));
        }
        return params;
    }
}
