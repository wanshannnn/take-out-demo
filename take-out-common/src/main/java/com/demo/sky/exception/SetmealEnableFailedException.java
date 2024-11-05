package com.demo.sky.exception;

import java.util.Map;

/**
 * 套餐启用失败异常
 */
public class SetmealEnableFailedException extends BaseException {

    public SetmealEnableFailedException(Map<String, Object> data){
        super(ErrorCode.SETMEAL_ENABLE_FAILED, data);
    }
}
