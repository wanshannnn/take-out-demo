package com.demo.sky.exception;

import java.util.Map;

public class ShoppingCartBusinessException extends BaseException {

    public ShoppingCartBusinessException(Map<String, Object> data) {
        super(ErrorCode.SHOPPING_CART_IS_NULL, data);
    }

}
