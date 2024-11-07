package com.demo.sky.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    ALREADY_EXISTS(409, HttpStatus.CONFLICT, "已存在"),
    PASSWORD_ERROR(400, HttpStatus.BAD_REQUEST, "密码错误"),
    ACCOUNT_NOT_FOUND(404, HttpStatus.NOT_FOUND, "账号不存在"),
    ACCOUNT_LOCKED(403, HttpStatus.FORBIDDEN, "账号被锁定"),
    UNKNOWN_ERROR(400, HttpStatus.BAD_REQUEST, "未知错误"),
    USER_NOT_LOGIN(401, HttpStatus.UNAUTHORIZED, "用户未登录"),
    CATEGORY_BE_RELATED_BY_SETMEAL(409, HttpStatus.CONFLICT, "当前分类关联了套餐,不能删除"),
    CATEGORY_BE_RELATED_BY_DISH(409, HttpStatus.CONFLICT, "当前分类关联了菜品,不能删除"),
    SHOPPING_CART_IS_NULL(409, HttpStatus.CONFLICT, "购物车数据为空，不能下单"),
    ADDRESS_BOOK_IS_NULL(409, HttpStatus.CONFLICT, "用户地址为空，不能下单"),
    LOGIN_FAILED(400, HttpStatus.BAD_REQUEST, "登录失败"),
    UPLOAD_FAILED(400, HttpStatus.BAD_REQUEST, "文件上传失败"),
    SETMEAL_ENABLE_FAILED(409, HttpStatus.CONFLICT, "套餐内包含未启售菜品，无法启售"),
    PASSWORD_EDIT_FAILED (400, HttpStatus.BAD_REQUEST, "密码修改失败"),
    DISH_ON_SALE(409, HttpStatus.CONFLICT, "起售中的菜品不能删除"),
    SETMEAL_ON_SALE(409, HttpStatus.CONFLICT, "起售中的套餐不能删除"),
    DISH_BE_RELATED_BY_SETMEAL(409, HttpStatus.CONFLICT, "当前菜品关联了套餐,不能删除"),
    ORDER_STATUS_ERROR(400, HttpStatus.BAD_REQUEST, "订单状态错误"),
    ORDER_NOT_FOUND(404, HttpStatus.NOT_FOUND, "订单不存在"),
    ORDER_ALREADY_PAID(404, HttpStatus.NOT_FOUND, "该订单已支付"),
    SHOP_ADDRESS_ANALYSIS_FAILED(400, HttpStatus.BAD_REQUEST, "店铺地址解析失败"),
    DISTRIBUTION_ROUTE_FAILED(400, HttpStatus.BAD_REQUEST, "配送线路规划失败"),
    OUT_OF_DISTRIBUTION_RANGE(400, HttpStatus.BAD_REQUEST, "超出配送范围");

    private final int code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return"ErrorCode{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
