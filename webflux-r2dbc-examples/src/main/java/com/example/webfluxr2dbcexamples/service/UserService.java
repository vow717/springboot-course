package com.example.webfluxr2dbcexamples.service;

import com.example.webfluxr2dbcexamples.dox.UserReactive;
import com.example.webfluxr2dbcexamples.exception.Code;
import com.example.webfluxr2dbcexamples.exception.XException;
import com.example.webfluxr2dbcexamples.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public Mono<UserReactive> findByAccount(String account) {
        return userRepository.findByAccount(account);
    }


    @Transactional
    public Mono<Void> changePassword(String uid, String password){
        return userRepository.changePasswordById(uid,encoder.encode(password));
    }
}
