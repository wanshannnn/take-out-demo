package com.demo.sky.exception;

import java.util.Map;

public class AddressBookBusinessException extends BaseException {

    public AddressBookBusinessException(Map<String, Object> data) {
        super(ErrorCode.ADDRESS_BOOK_IS_NULL, data);
    }

}
