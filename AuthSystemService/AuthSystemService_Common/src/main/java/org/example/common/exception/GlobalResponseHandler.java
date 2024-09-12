package org.example.common.exception;

import org.example.common.web.SimpleResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @Author 刘文轩
 * @Date 2024/4/11 0:14
 */
//@ControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice {

    /**
     * 判断是否要执行beforeBodyWrite方法,true为执行,false不执行
     * 通过该方法可以选择哪些类或那些方法的response要进行处理, 其他的不进行处理.
     * @param returnType 方法返回的类型
     * @param converterType 参数类型装换
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        System.out.println("supports");
        if (returnType.getMethod() == null) {
            return false;
        }
        ResponseBody responseBody = returnType.getMethod().getAnnotation(ResponseBody.class);
        if (responseBody != null) {
            return true;
        }
        // 只拦截返回结果为 SimpleResponse 类型
        return returnType.getMethod().getReturnType() == SimpleResponse.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        System.out.println("beforeBody");
        ResponseBody responseBody = returnType.getMethod().getAnnotation(ResponseBody.class);
        if (responseBody != null || selectedContentType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)) {
            if (body instanceof SimpleResponse ajaxBody) {
                if (!Objects.equals(ajaxBody.getCode(), "500")) {
                    response.setStatusCode(HttpStatus.valueOf(ajaxBody.getCode()));
                }
            } else {
                return new SimpleResponse();
            }
        }
        return body;
    }
}
