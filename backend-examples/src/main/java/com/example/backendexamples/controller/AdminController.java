package com.example.backendexamples.controller;

import com.example.backendexamples.dox.User;
import com.example.backendexamples.service.UserService;
import com.example.backendexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    // 管理员添加用户
    @PostMapping("users")
    public ResultVO postUser(@RequestBody User user){
        userService.addUser(user);
        return ResultVO.ok();
    }

    // 管理员重置用户密码
    @PutMapping("users/{account}/password")
    public ResultVO putPassword(@PathVariable String account){
        userService.updateUserPassword(account);
        return ResultVO.ok();
    }

    // 管理员获取全部用户
    @GetMapping("users")
    public ResultVO getUsers(){
        return ResultVO.success(userService.listUsers());
    }
}
