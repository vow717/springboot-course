package com.example.backendexamples.service;

import com.example.backendexamples.dox.User;
import com.example.backendexamples.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InitService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        String account="admin";
        long count=userRepository.count();
        if(count>0) {
            log.info("数据库已经初始化过了");
            return;
        }
        User u= User.builder()
                .name(account)
                .account(account)
                .password(passwordEncoder.encode(account))
                .role(User.ADMIN)
                .build();
        log.info("初始化数据库");
        userRepository.save(u);
    }
}
