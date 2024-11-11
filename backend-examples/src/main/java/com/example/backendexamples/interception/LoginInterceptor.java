package com.example.backendexamples.interception;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backendexamples.component.JWTComponent;
import com.example.backendexamples.exception.Code;
import com.example.backendexamples.exception.XException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final JWTComponent jwtComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token==null){
            throw XException.builder().code(Code.UNAUTHORIZED).build();
        }
        //解码JWT,获取uid和role,并且放到request的属性中,因为后面的controller需要用到,并且request是线程安全的
        DecodedJWT decode =jwtComponent.decode(token);
        String uid = decode.getClaim("uid").asString();
        String role = decode.getClaim("role").asString();
        request.setAttribute("uid",uid);
        request.setAttribute("role",role);
        return true;
    }
}
