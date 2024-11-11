package com.example.redisexamples.listener;

import com.example.redisexamples.dto.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
//StreamListener<String, ObjectRecord<String,String>>：监听一个stream，stream的key是String类型，stream的value是ObjectRecord<String,String>类型
public class OrderMessageListener implements StreamListener<String, ObjectRecord<String,String>> {

    //template：RedisTemplate对象,用来操作redis，比如发送消息，接收消息等
    private final RedisTemplate<String, String> template;
    @Override
    public void onMessage(ObjectRecord<String, String> message) {
       log.info("{}", message.getId());
       log.info("{}", message.getValue());

       //确认消息
       template.opsForStream().acknowledge(Order.GROUP_NAME, message);
    }
}
