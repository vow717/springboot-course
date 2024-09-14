package com.example.springbootcourse;

import com.example.springbootcourse.dox.User;
import com.example.springbootcourse.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class Experiment01 {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserAdd(){
        User user = User.builder().name("test").build();
        userRepository.save(user);
        log.info("User added: {}", user.getName());
    }

    @Test
    void testUserUpdate(){
        User user = User.builder().name("update").build();
        userRepository.save(user);
        log.info("User added: {}", user.getName());
        user.setName("update2");
        userRepository.save(user);
        log.info("User updated: {}", user.getName());
    }
}
