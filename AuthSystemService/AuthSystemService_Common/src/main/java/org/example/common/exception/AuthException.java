package org.example.common.exception;


import org.example.common.enums.ErrCode;

/**
 * Created by wang.yangtx on 2018/8/18.
 */
public class AuthException extends RuntimeException {
    private String code = ErrCode.E700;

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
