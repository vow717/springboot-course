package com.example.redisexamples.service;

import com.example.redisexamples.component.ULID;
import com.example.redisexamples.dto.Item;
import com.example.redisexamples.dto.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.api.stream.StreamAddArgs;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final RedissonClient client;
    private final ULID ulid;

    public void addItems(List<Item> items) {
        //创建一个批处理对象.批处理对象可以将多个操作合并成一个请求发送给redis服务器
        //RBatch是Redisson提供的一个批处理对象，createBatch()方法用来创建一个批处理对象
        RBatch batch = client.createBatch();
        for(Item item: items) {
            //获取一个RMapAsync对象，这个对象是一个异步对象，可以在不阻塞当前线程的情况下进行操作,batch.getMap(Item.PRE_KEY+item.getId(), StringCodec.INSTANCE)：获取一个map对象
            //putAsync("total", item.getTotal())：向map中添加一个键值对，这个操作是异步的
            RMapAsync<String,Integer> map= batch.getMap(Item.PRE_KEY+item.getId(), StringCodec.INSTANCE);
            map.putAsync("total", item.getTotal());
        }
        //执行批处理
        batch.execute();
    }


    public long buy(String itemId,String userId){
         long q= client.getFunction()
                .call(FunctionMode.WRITE,
                        "buy_44",
                        FunctionResult.LONG,
                        List.of(Item.PRE_KEY+itemId));
        if(q == -1){
            log.debug("库存不足");
            return -1;
        }
        var id=ulid.nextULID();
        Order o= Order.builder()
                .id(id)
                .userId(userId)
                .itemId(itemId)
                .build();
        client.getBucket(Order.PRE_KEY+id)
                .set(o);
        sendMessage(o);
        return q;
    }

    public void sendMessage(Order order){
        client.getStream(Order.STREAM_KEY,StringCodec.INSTANCE)
                .add(StreamAddArgs.entry("orderid",order.getId()));
    }
}
