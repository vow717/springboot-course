package com.example.springmvcexamples.controller;


import com.example.springmvcexamples.component.JWTComponent;
import com.example.springmvcexamples.dox.User;
import com.example.springmvcexamples.exception.Code;
import com.example.springmvcexamples.service.UserService;
import com.example.springmvcexamples.vo.ResultVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class LoginController {
    //用encoder的matches方法来验证密码是否正确,可以避免明文密码泄露
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final JWTComponent jwtComponent;
    @PostMapping("login")
    public ResultVO login(@RequestBody User user, HttpServletResponse response){
        User userR=userService.getUserByAccount(user.getAccount());
        if (userR==null || !encoder.matches(user.getPassword(),userR.getPassword())){
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        String token= jwtComponent.encode(Map.of("uid",userR.getId(),"role",userR.getRole()));
        response.setHeader("token",token);
        response.setHeader("role",userR.getRole());
        return ResultVO.success(userR);
    }
}
