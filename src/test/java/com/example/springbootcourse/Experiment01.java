package com.example.springbootcourse;

import com.example.springbootcourse.dox.Address;
import com.example.springbootcourse.dox.User;
import com.example.springbootcourse.repository.AddressRepository;
import com.example.springbootcourse.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class Experiment01 {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Test
    void testUserAdd(){
        User user = User.builder().name("test").build();
        userRepository.save(user);
        log.info("User added: {}", user.getName());
    }

    @Test
    void testUserUpdate(){
        User user = User.builder().name("update").build();
        userRepository.save(user);
        log.info("User added: {}", user.getName());
        user.setName("update2");
        userRepository.save(user);
        log.info("User updated: {}", user.getName());
    }

    @Test
    void testAddressAdd(){
        Address address= Address.builder().name("shanghai").userId("1283954270169460736").build();
        addressRepository.save(address);
        log.info("Address added: {}", address.getName());
        Address address2= Address.builder().name("beijing").userId("1283954270169460736").build();
        addressRepository.save(address2);
        log.info("Address added: {}", address2.getName());
    }
}
