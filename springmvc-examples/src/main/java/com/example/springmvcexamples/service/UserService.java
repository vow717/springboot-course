package com.example.springmvcexamples.service;


import com.example.springmvcexamples.dox.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static List<User> USERS = create();
    private static List<User> create(){
        User u =User.builder()
                .id("1")
                .name("zhangsan")
                .account("1001")
                .role(User.ADMIN)
                .password("$2a$10$XPz7Kp1kF8NU3vewqqPGn.feT7UPvhoZolvJ1JRi57s16XKMWz9OW").build();
        User u2=User.builder()
                .id("2")
                .name("lisi")
                .account("1002")
                .role(User.User)
                .password("$2a$10$XPz7Kp1kF8NU3vewqqPGn.feT7UPvhoZolvJ1JRi57s16XKMWz9OW").build();

        return List.of(u,u2);
    }

    public List<User> listUsers(){
        return USERS;
    }

//    public User getUserByAccount(String account,String password){
//        return USERS.stream()
//                .filter(u->u.getAccount().equals(account))
//                .filter(u->u.getPassword().equals(password))
//                .findFirst().orElse(null);
//    }

    public User getUserByAccount(String account){
        return USERS.stream()
                .filter(u->u.getAccount().equals(account))
                .findFirst().orElse(null);
    }
}
