package com.example.redisexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    public static final String PRE_KEY="order:";
    public static final String STREAM_KEY = "orders:process";
    public static final String GROUP_NAME = "consumer-group";
    public static final String GROUP_CONSUMER = "consumer-1";
    private String id;
    private String userId;
    private String itemId;
    //假设每个人每次只能买一个商品
}
