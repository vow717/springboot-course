package com.example.springbootcourse.repository;

import com.example.springbootcourse.dox.Address;
import com.example.springbootcourse.dto.AddressUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;
    @Test
    void findByUserId() {
        for (Address address : addressRepository.findByUserId("1283954270169460736")) {
            log.debug("address:{}",address);
        }
    }

    @Test
    void findByAid() {
        AddressUserDTO addressUserDTO= addressRepository.findByAId("1294571055809818624");
        log.debug("address:{}",addressUserDTO.getAddress());
        log.debug("user:{}",addressUserDTO.getUser());
    }
}