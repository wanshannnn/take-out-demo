package com.demo.sky.exception;

import java.util.Map;

/**
 * 密码错误异常
 */
public class PasswordErrorException extends BaseException {

    public PasswordErrorException(Map<String, Object> data) {
        super(ErrorCode.PASSWORD_ERROR, data);
    }

}
