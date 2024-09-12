package org.example.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;

/**
 * @Author 刘文轩
 * @Date 2024/5/14 0:43
 */
public class AppIdUtil {

    public static String getAppId(HttpServletRequest request) throws Exception {
//        Constants.LOGIN_APP_ID
        String appId = request.getHeader("APPID");
        if (StringUtils.isNotBlank(appId)) {
            return appId;
        }
        throw new Exception("无效的应用标识-appId-BaseRest.java");
    }

    public static String getUserName(HttpServletRequest request) throws Exception {
        String userName = URLDecoder.decode(request.getHeader("USER_NAME"), "UTF-8");
        if (StringUtils.isNotBlank(userName)) {
            return userName;
        }
        throw new Exception("无效的用户名");
    }
}
