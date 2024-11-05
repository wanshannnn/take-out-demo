package com.demo.sky.exception;

import java.util.Map;

public class UserNotLoginException extends BaseException {

    public UserNotLoginException(Map<String, Object> data) {
        super(ErrorCode.USER_NOT_LOGIN, data);
    }

}
