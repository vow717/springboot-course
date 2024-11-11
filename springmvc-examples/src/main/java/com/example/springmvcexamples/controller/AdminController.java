package com.example.springmvcexamples.controller;

import com.example.springmvcexamples.service.UserService;
import com.example.springmvcexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/")
public class AdminController {
    private final UserService userService;

    @GetMapping("users")
    public Object getUsers(){
        return ResultVO.success(userService.listUsers());
    }

    @GetMapping("user/{account}")
    public ResultVO getUser(@PathVariable String account){
        return ResultVO.success(userService.getUserByAccount(account));
    }
}
