package com.example.webfluxr2dbcexamples.controller;

import com.example.webfluxr2dbcexamples.component.JWTComponent;
import com.example.webfluxr2dbcexamples.dox.UserReactive;
import com.example.webfluxr2dbcexamples.exception.Code;
import com.example.webfluxr2dbcexamples.service.UserService;
import com.example.webfluxr2dbcexamples.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/")
public class LoginController {
    private final JWTComponent jwtComponent;
    private final PasswordEncoder encoder;
    private final UserService userService;

    @PostMapping("login")
    public Mono<ResultVO> login(@RequestBody UserReactive user, ServerHttpResponse response){
//ServerHttpResponse代表了服务器端对 HTTP 请求的响应。
// 它提供了一系列方法来设置响应的各种属性以及向客户端发送响应内容，比如设置响应状态码、响应头、写入响应体等操作.
//相比于HttpServletResponse，ServerHttpResponse是一个响应式的接口，它的实现类是ServerHttpResponseDecorator。
        return userService.findByAccount(user.getAccount())
                .filter(userR->encoder.matches(user.getPassword(),userR.getPassword()))
                .map(u->{
                        String token=jwtComponent.encode(Map.of("uid",u.getId(),"role",u.getRole()));
                    response.getHeaders().add("token",token);
                    response.getHeaders().add("role",u.getRole());
                    return ResultVO.success(u);
                })
                .defaultIfEmpty(ResultVO.error(Code.LOGIN_ERROR));
    }
}
