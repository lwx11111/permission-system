package org.example.common.aop;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.common.domain.SysLog;
import org.example.common.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Date 2024/3/25 11:47
 */
@Aspect
@Component
@Slf4j
public class LogsAspect {

    private static final String SUCCESS = "成功";
    private static final String FAIL = "失败";

    private static final ThreadLocal<SysLog> invokeThreadLocal = new ThreadLocal<>();

    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("@annotation(org.example.common.annotation.SysLog)")
    public void logService() {
        System.out.println("logService");
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void startDateService() {
        System.out.println("startDateService");
    }


    /**
     * 进入方法前校验
     *
     * @param joinPoint pointcut
     */
    @Before("startDateService()")
    public void beforeStartInvoke(JoinPoint joinPoint) throws Exception {
        SysLog log = new SysLog();
        invokeThreadLocal.set(log);
    }

    /**
     * 正常返回
     * @param joinPoint pointcut
     * @param ret       返回结果
     */
    @AfterReturning(value = "startDateService()", returning = "ret")
    public void afterReturn(JoinPoint joinPoint, Object ret) {
        invokeThreadLocal.remove();
    }

    /**
     * 正常返回
     *
     * @param joinPoint pointcut
     * @param returnValue       返回结果
     */
    @AfterReturning(value = "logService()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        String receiveMsg = JSONUtil.toJsonStr(returnValue);
        saveInvokeLog(joinPoint, receiveMsg, SUCCESS);
    }

    /**
     * 抛出异常
     *
     * @param joinPoint pointcut
     * @param ex        异常
     */
    @AfterThrowing(value = "logService()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        // 调用class 全名
        String className = joinPoint.getTarget().getClass().getName();
        // 调用方法名
        String methodName = joinPoint.getSignature().getName();
        log.error("Error encountered while invoking {}.{}", className, methodName, ex);
    }

    /**
     * 抛出异常
     *
     * @param joinPoint pointcut
     * @param ex        异常
     */
    @AfterThrowing(value = "startDateService()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Throwable ex) {
        invokeThreadLocal.remove();
    }

    /**
     * 存服务调用日志
     * 可以获取swagger注解或者新注解信息来写入调用信息
     *
     * @param joinPoint   pointcut
     * @param receiveMsg  接收消息，方法返回值
     * @param invokeState 执行状态
     */
    private void saveInvokeLog(JoinPoint joinPoint, String receiveMsg, String invokeState) {
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作，参数为自定义注解的字节码，返回注解上name的值
        org.example.common.annotation.SysLog apiOperation = method.getAnnotation(org.example.common.annotation.SysLog.class);

        // 调用class 全名
        String className = joinPoint.getTarget().getClass().getName();
        // 调用方法名
        String methodName = joinPoint.getSignature().getName();
        // 入参
        Object[] args = joinPoint.getArgs();
        String[] test = Arrays.stream(args).toArray(String[]::new);
        StringBuilder parameter = new StringBuilder();
        for (int i = 0; i < test.length; i++){
            parameter.append(test[i]);
        }
        // 接口异常写入invoke_log
        SysLog log = new SysLog();
        log.setParameter(parameter.toString());
        // 操作名称
        if (null != apiOperation) {
            log.setName(apiOperation.name());
        }
        log.setTime(LocalDateTime.now());
        log.setResult(receiveMsg);
        sysLogService.save(log);
    }



}
