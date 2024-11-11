package com.example.backendexamples.interception;

import com.example.backendexamples.dox.User;
import com.example.backendexamples.exception.Code;
import com.example.backendexamples.exception.XException;
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
