package com.demo.sky.exception;

import java.util.Map;

/**
 * 密码修改失败异常
 */
public class PasswordEditFailedException extends BaseException{

    public PasswordEditFailedException(Map<String, Object> data){
        super(ErrorCode.PASSWORD_EDIT_FAILED, data);
    }

}
