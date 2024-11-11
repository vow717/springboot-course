package com.example.springbootcourse.repository;

import com.example.springbootcourse.dto.UserAddressDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Test
    void findByUid() {
        UserAddressDTO userAddressDTO = userRepository.findByUid("1283954270169460736");
        log.debug("user:{}", userAddressDTO.getUser());
        log.debug("addresses:{}", userAddressDTO.getAddresses());
    }
}