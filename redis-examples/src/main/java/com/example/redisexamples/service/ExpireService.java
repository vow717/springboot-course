package com.example.redisexamples.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.FunctionMode;
import org.redisson.api.FunctionResult;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor //这个注解是lombok的，用来自动生成构造函数
public class ExpireService {
    private final RedissonClient client;
    //key:键名,expire:过期时间,count:次数
    //client.getFunction(IntegerCodec.INSTANCE)：获取一个Function对象，指定返回值类型为Integer
    //call(FunctionMode.WRITE,"expireAPI_44",FunctionResult.BOOLEAN,List.of(key),expire,count)：调用一个函数,这个函数是在lua脚本中定义的
    //第一个参数是FunctionMode，指定函数的模式，这里是写操作，第二个参数是函数名，第三个参数是返回值类型，第四个参数是函数的参数
    public boolean expire(String key,int expire ,int count){
        return client.getFunction(IntegerCodec.INSTANCE)
                    .call(FunctionMode.WRITE,
                        "expireAPI_44",
                        FunctionResult.BOOLEAN,
                        List.of(key),
                        expire,count);
    }
}
