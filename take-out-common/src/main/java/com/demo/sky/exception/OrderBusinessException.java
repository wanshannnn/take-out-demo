package com.demo.sky.exception;

import java.util.Map;

public class OrderBusinessException extends BaseException {

    public OrderBusinessException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode, data);
    }

}
