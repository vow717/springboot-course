package com.example.webfluxr2dbcexamples.service;

import com.example.webfluxr2dbcexamples.dox.UserReactive;
import com.example.webfluxr2dbcexamples.exception.Code;
import com.example.webfluxr2dbcexamples.exception.XException;
import com.example.webfluxr2dbcexamples.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public Mono<Void> addUser(UserReactive user) {
        return userRepository.findByAccount(user.getAccount())
                .flatMap(u->Mono.error(XException.builder().codeNum(Code.ERROR).message("用户已存在").build()))
                .switchIfEmpty(Mono.defer(()->{
                    user.setPassword(encoder.encode(user.getAccount()));
                    user.setRole(UserReactive.USER);
                    return userRepository.save(user);
                })).then();

    }

    @Transactional
    public Mono<Void> changePassword(String account){
        return userRepository.changePasswordByAccount("1001",encoder.encode(account));

    }

}
