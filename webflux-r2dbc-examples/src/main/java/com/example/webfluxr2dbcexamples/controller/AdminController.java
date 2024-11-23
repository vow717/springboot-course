package com.example.webfluxr2dbcexamples.controller;

import com.example.webfluxr2dbcexamples.dox.UserReactive;
import com.example.webfluxr2dbcexamples.exception.XException;
import com.example.webfluxr2dbcexamples.service.AdminService;
import com.example.webfluxr2dbcexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/")
public class AdminController {
    private final AdminService AdminService;

    @PutMapping("password/{account}")
    public Mono<ResultVO> changePassword(@PathVariable String account){
        return AdminService.changePassword(account)
                .thenReturn(ResultVO.ok());
    }

    //根据account，创建个密码也是account的用户
    @PostMapping("add")
    public Mono<ResultVO> addUser(@RequestBody UserReactive user){
        return AdminService.addUser(user)
                .thenReturn(ResultVO.ok());
    }



}
