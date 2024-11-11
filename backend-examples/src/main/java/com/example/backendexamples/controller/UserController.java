package com.example.backendexamples.controller;

import com.example.backendexamples.dox.User;
import com.example.backendexamples.service.UserService;
import com.example.backendexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;
    @PatchMapping("password")
    public ResultVO updatePassword(@RequestBody User user,@RequestAttribute("uid") String uid){
        userService.updateUserPassword(uid,user.getPassword());
        return ResultVO.ok();
    }
}
