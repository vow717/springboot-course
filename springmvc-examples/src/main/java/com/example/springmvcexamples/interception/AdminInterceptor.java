package com.example.springmvcexamples.interception;

import com.example.springmvcexamples.dox.User;
import com.example.springmvcexamples.exception.Code;
import com.example.springmvcexamples.exception.XException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(User.ADMIN.equals(request.getAttribute("role"))){
            return true;
        }
        throw XException.builder().code(Code.FORBIDDEN).build();
    }
}
