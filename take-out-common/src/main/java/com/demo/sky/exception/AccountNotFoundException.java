package com.demo.sky.exception;

import java.util.Map;

/**
 * 账号不存在异常
 */
public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException(Map<String, Object> data) {
        super(ErrorCode.ACCOUNT_NOT_FOUND, data);
    }

}
