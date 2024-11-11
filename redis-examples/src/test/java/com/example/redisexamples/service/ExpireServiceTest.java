package com.example.redisexamples.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ExpireServiceTest {
    @Autowired
    private ExpireService expireService;
    @Test
    void expire() {
        for(int i=0;i<5;i++){
            boolean expire=expireService.expire("api:521",10,2);
            log.debug("expire:{}",expire);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}