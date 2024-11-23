package com.example.webfluxr2dbcexamples.controller;

import com.example.webfluxr2dbcexamples.dox.UserReactive;
import com.example.webfluxr2dbcexamples.repository.UserRepository;
import com.example.webfluxr2dbcexamples.service.UserService;
import com.example.webfluxr2dbcexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;

    @PatchMapping("password")
    public Mono<ResultVO> changePassword(@RequestAttribute String uid, @RequestBody UserReactive user){
        return userService.changePassword(uid,user.getPassword())
                .thenReturn(ResultVO.ok());

    }
}
