package com.demo.sky.exception;

import java.util.Map;

public class DeletionNotAllowedException extends BaseException {

    public DeletionNotAllowedException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode, data);
    }

}
