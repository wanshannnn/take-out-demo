package com.demo.sky.controller.user;

import com.demo.sky.constant.JwtClaimsConstant;
import com.demo.sky.dto.UserLoginDTO;
import com.demo.sky.dao.User;
import com.demo.sky.properties.JwtProperties;
import com.demo.sky.result.Result;
import com.demo.sky.service.UserService;
import com.demo.sky.utils.JwtUtil;
import com.demo.sky.vo.UserLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 微信登录
 */
@RestController
@RequestMapping("/user/user")
@Tag(name = "C端-用户相关接口")
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtProperties jwtProperties;

    public UserController(UserService userService, JwtProperties jwtProperties) {
        this.userService = userService;
        this.jwtProperties = jwtProperties;
    }

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @Operation(description = "微信登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("微信用户登录：{}",userLoginDTO.getCode());

//        微信登录
        User user = userService.wxLogin(userLoginDTO);

//        为微信用户生成jwt令牌
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }

}
