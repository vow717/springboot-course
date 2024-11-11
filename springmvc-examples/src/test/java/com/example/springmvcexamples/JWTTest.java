package com.example.springmvcexamples;

import com.example.springmvcexamples.component.JWTComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.Map;

@SpringBootTest
@Slf4j
public class JWTTest {;
    @Autowired
    private JWTComponent jwtComponent;

    @Test
    public void test_jwt() throws InterruptedException{
        //测试JWT,生成一个token,然后解码这个token
        String token=jwtComponent.encode(Map.of("uid",1839248769875678293L,"role",9));
        log.debug("token:{}",token);
        // .getClaim("uid")是获取JWT的payload部分的uid字段
        long uid=jwtComponent.decode(token).getClaim("uid").asLong();
        log.debug("uid:{}",uid);
        Thread.sleep(15000);
        long uid2=jwtComponent.decode(token).getClaim("uid").asLong();
        log.debug("uid2:{}",uid2);
    }
}
