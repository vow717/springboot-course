package com.example.redisexamples.listener;

import com.example.redisexamples.dto.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.redisson.api.stream.StreamCreateGroupArgs;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CreateOrderStreamListener {
    private final RedissonClient client;

    //监听ApplicationReadyEvent事件，当应用启动完成后执行
    @EventListener(ApplicationReadyEvent.class)
    public void listen(){
        RStream<String,String> stream=client.getStream(Order.STREAM_KEY);
        if(!stream.isExists()){
            //StreamCreateGroupArgs.name(Order.GROUP_NAME)：创建一个StreamCreateGroupArgs(意思是创建一个分组)对象，指定分组的名字

            stream.createGroup(StreamCreateGroupArgs.name(Order.GROUP_NAME).makeStream());
            stream.createConsumer(Order.GROUP_NAME,Order.GROUP_CONSUMER);
        }
    }
}
