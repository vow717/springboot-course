package com.example.redisexamples.service;

import com.example.redisexamples.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    void addItemsTest() {
        List<Item> items = List.of(
                Item.builder().id("01HN7JNHG93N3RSPF60MEG4WE2").total(50).build(),
                Item.builder().id("01HN7JNHG93N3RSPF60MEG4WE3").total(30).build()
        );
        orderService.addItems(items);
    }

    @Test
    void buy() throws InterruptedException {
        var count =200;
        // CountDownLatch是一个同步辅助类，允许一个或多个线程等待其他线程完成操作
        CountDownLatch latch = new CountDownLatch(count);
        var random = new Random();
        // 模拟购买的用户,200个用户同时购买,并发测试
        for (int i = 0; i < 200; i++) {
            Thread.startVirtualThread(() -> {
                long q = orderService.buy("01HN7JNHG93N3RSPF60MEG4WE2",String.valueOf(random.nextInt(count)));
                log.debug("剩余数量：{}", q);

                // 释放一个锁存器，递减其计数，如果计数达到零，则释放所有等待的线程
                latch.countDown();
            });
        }
        latch.await();

        //Thread.sleep(6000);
    }
}