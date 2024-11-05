package com.demo.sky.exception;

import java.util.Map;

/**
 * 账号被锁定异常
 */
public class AccountLockedException extends BaseException {

    public AccountLockedException(Map<String, Object> data) {
        super(ErrorCode.ACCOUNT_LOCKED, data);
    }

}
