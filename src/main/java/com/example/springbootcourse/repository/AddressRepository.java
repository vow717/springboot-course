package com.example.springbootcourse.repository;

import com.example.springbootcourse.dox.Address;
import com.example.springbootcourse.dto.AddressUserDTO;
import com.example.springbootcourse.mapper.AddressRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
    @Query("select * from address a where a.user_id = :userId")
    List<Address> findByUserId(String userId);

    @Query(value = "select * from address a, user u where a.user_id=u.id and a.id =:aid",
            rowMapperClass= AddressRowMapper.class)
    AddressUserDTO findByAId(String aid);
}