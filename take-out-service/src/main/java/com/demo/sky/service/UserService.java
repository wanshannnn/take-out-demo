package com.demo.sky.service;

import com.demo.sky.dto.UserLoginDTO;
import com.demo.sky.dao.User;

public interface UserService {
    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
