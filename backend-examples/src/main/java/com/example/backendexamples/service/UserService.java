package com.example.backendexamples.service;

import com.example.backendexamples.dox.User;
import com.example.backendexamples.exception.Code;
import com.example.backendexamples.exception.XException;
import com.example.backendexamples.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User getUser(String account){
        return userRepository.findByAccount(account);
    }
    @Transactional //这个注解表示这个方法是一个事务方法,因为这个方法会对数据库进行操作,所以需要加上这个注解,以便在出现异常的时候进行回滚
    public void updateUserPassword(String account){
        User user=userRepository.findByAccount(account);
        if(user==null){
            throw  XException.builder().codeN(Code.ERROR).message("用户不存在").build();
        }
        user.setPassword(passwordEncoder.encode(account));
        userRepository.save(user);
    }
    @Transactional
    public void updateUserPassword(String uid,String password){
        User user=userRepository.findById(uid).orElse(null);
        if(user==null){
            throw  XException.builder().codeN(Code.ERROR).message("用户不存在").build();
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getAccount()));
        userRepository.save(user);
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }
}
