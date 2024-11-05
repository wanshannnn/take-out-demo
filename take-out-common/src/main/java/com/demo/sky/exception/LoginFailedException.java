package com.demo.sky.exception;

import java.util.Map;

/**
 * 登录失败
 */
public class LoginFailedException extends BaseException{
    public LoginFailedException(Map<String, Object> data){
        super(ErrorCode.LOGIN_FAILED, data);
    }
}
