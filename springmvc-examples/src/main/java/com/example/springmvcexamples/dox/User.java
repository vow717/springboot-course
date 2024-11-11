package com.example.springmvcexamples.dox;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    public static final String User="UnC5";
    public static final String ADMIN="Al2N";

    private String id;
    private String name;
    private String account;
    private String role;
    //这个注解的作用是在序列化的时候忽略这个字段，因为这个字段是密码，不应该被序列化而被别人看到
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private LocalDateTime createdTime;
}
