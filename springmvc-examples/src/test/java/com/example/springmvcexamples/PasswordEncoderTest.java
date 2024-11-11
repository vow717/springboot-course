package com.example.springmvcexamples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
public class PasswordEncoderTest {
    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void test_password(){
        String pwd="123456";
        log.debug("password:{}",encoder.encode(pwd));
        log.debug("password:{}",encoder.encode(pwd));
        String result= encoder.encode(pwd);
        log.debug("result:{}",result);
        log.debug("matches:{}",encoder.matches(pwd,result));
    }
}
