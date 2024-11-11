package com.example.redisexamples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RedissionTest {
    @Autowired
    private RedissonClient client;

    @Test
    public void testRedission(){
        var name="wkf";
        var key="users:14";
        //这个RBucket就是Redisson最基本的对象之一,作用就是用来存储数据的,StringCodec.INSTANCE是指定存储的数据类型为String
        RBucket<String> bucket = client.getBucket(key, StringCodec.INSTANCE);
        bucket.set(name);
    }
}
