package com.example.backendexamples.controller;



import com.example.backendexamples.component.JWTComponent;
import com.example.backendexamples.dox.User;
import com.example.backendexamples.exception.Code;
import com.example.backendexamples.service.UserService;
import com.example.backendexamples.vo.ResultVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        User userR=userService.getUser(user.getAccount());
        if (userR==null || !encoder.matches(user.getPassword(),userR.getPassword())){
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        String token= jwtComponent.encode(Map.of("uid",userR.getId(),"role",userR.getRole()));
        response.setHeader("token",token);
        response.setHeader("role",userR.getRole());
        return ResultVO.success(userR);
    }
}
