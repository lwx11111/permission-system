package org.example.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.common.annotation.OperationLog;
import org.example.common.domain.monitor.LogPlatformOper;
import org.example.common.entity.MicroInfo;
import org.example.common.enums.Constants;
import org.example.common.jwt.TokenProvider;
import org.example.common.jwt.TokenResponse;
import org.example.service.mointor.ILogPlatformOperService;
import org.example.common.utils.HttpContextUtils;
import org.example.common.utils.LongBigDecimalUtils;
import org.example.common.utils.RequestUtils;
import org.example.common.web.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Aspect
@Component
@ConditionalOnClass(HttpServletRequest.class)
public class OperationLogAspect {

    @Resource
    private ILogPlatformOperService logService;

    @Resource
    private ObjectMapper objectMapper;

    @Autowired
    private TokenProvider tokenProvider;

    @Pointcut("@annotation(org.example.common.annotation.OperationLog)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 默认值
        String successFlag = "0";
        int type = 5;
        // 操作日志
        LogPlatformOper logPlatformOper = new LogPlatformOper();
        // 执行方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        // 方法的返回值
        Object result = point.proceed();
        // 获取注解信息
        OperationLog logAnnotation = method.getAnnotation(OperationLog.class);
        if (logAnnotation != null) {
            type = logAnnotation.type();
            logPlatformOper.setOperation(logAnnotation.value());
            logPlatformOper.setType(type);
            logPlatformOper.setSensitiveLevel(logAnnotation.sensitive());
        }
        if (result instanceof SimpleResponse simpleResponse) {
            if (simpleResponse.getCode() != 200) {
                successFlag = "1";
            } else {
                // 登录操作
                if (type == 6) {
                    // 通过token获取用户信息
                    Map<String, Object> data = (Map) simpleResponse.getData();
                    TokenResponse tokenResponse = (TokenResponse) data.get("token");
                    if (tokenResponse != null) {
                        if (StringUtils.isNotBlank(tokenResponse.getAccessToken())) {
                            Claims claims = tokenProvider.parseToken(tokenResponse.getAccessToken());
                            if (claims != null) {
                                String userId = claims.get(Constants.LOGIN_USER_ID).toString();
                                String accountId = claims.get(Constants.LOGIN_ACCOUNT_ID).toString();
                                String userName = claims.get(Constants.LOGIN_USER_NAME).toString();
                                String accountName = claims.get(Constants.LOGIN_ACCOUNT_NAME).toString();
                                logPlatformOper.setUserId(userId);
                                logPlatformOper.setAccountId(accountId);
                                logPlatformOper.setAccountName(accountName);
                                logPlatformOper.setUserName(userName);
                            }
                        }
                    }
                }
            }
        }

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ip = RequestUtils.getClientIp(request);
        long time = System.currentTimeMillis() - beginTime;
        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        logPlatformOper.setMethod(className + "." + methodName + "()");
        logPlatformOper.setClientIp(ip);
        logPlatformOper.setTime(LongBigDecimalUtils.longToBigDecimal(time));
        logPlatformOper.setCreateTime(LocalDateTime.now());
        logPlatformOper.setSuccessFlag(successFlag);
        logPlatformOper.setServerIp(MicroInfo.getInfo().getIp());
        if (type != 6) {
            // 请求的方法参数值
            Object[] args = point.getArgs();
            // 请求的方法参数名称
            StandardReflectionParameterNameDiscoverer p = new StandardReflectionParameterNameDiscoverer();
            // 被删除
//            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = p.getParameterNames(method);
            if (args != null && paramNames != null) {
                StringBuilder params = new StringBuilder();
                params = handleParams(params, args, Arrays.asList(paramNames));
                logPlatformOper.setParams(params.toString());
            }

            logPlatformOper.setUserId(getUserId(request));
            logPlatformOper.setAccountId(getAccountId(request));
            logPlatformOper.setUserName(getUserName(request));
        }
        logService.save(logPlatformOper);
        return result;
    }

    private String getUserId(HttpServletRequest request) {
        String userId = "";
        try {
            userId = request.getHeader(Constants.LOGIN_USER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    private String getAccountId(HttpServletRequest request) {
        try {
            String accountId = request.getHeader(Constants.LOGIN_ACCOUNT_ID);
            if (StringUtils.isNotBlank(accountId)) {
                return accountId;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getUserName(HttpServletRequest request) {
        String userName = "";
        try {
            String _userName = request.getHeader(Constants.LOGIN_USER_NAME);
            if (StringUtils.isNotBlank(_userName)) {
                userName = URLDecoder.decode(_userName, "UTF-8");
            }
        } catch (Exception e) {
            log.error("getUserName异常:{}", e);
        }
        return userName;
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) throws JsonProcessingException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List<Object> list = new ArrayList<>();
                List<Object> paramList = new ArrayList<>();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params, list.toArray(), paramList);
            } else if (args[i] instanceof Serializable) {
                Class<?> aClass = args[i].getClass();
                try {
                    aClass.getDeclaredMethod("toString", new Class[]{null});
                    // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                    params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                } catch (NoSuchMethodException e) {
                    params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                }
            } else if (args[i] instanceof HttpServletRequest) {
                Map<String, Object> _params = RequestUtils.convertToMap((HttpServletRequest) args[i]);
                log.info("args[i] instanceof HttpServletRequest,_params={}", _params);
                params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(_params));
            } else if (args[i] instanceof HttpServletResponse) {

            } else if (args[i] instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) args[i];
                params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
            } else {
                params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
            }
        }
        return params;
    }
}
