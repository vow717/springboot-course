package com.example.webfluxr2dbcexamples.service;

import com.example.webfluxr2dbcexamples.dox.UserReactive;
import com.example.webfluxr2dbcexamples.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public Mono<Void> init(){
        String account="admin";
        return userRepository.findByAccount(account)
                .switchIfEmpty(Mono.defer(()-> {
                    UserReactive u=UserReactive.builder()
                            .name(account)
                            .account(account)
                            .role(UserReactive.ADMIN)
                            .password(encoder.encode(account))
                            .build();
                    return userRepository.save(u);
                }))
                .then();
    }
}
