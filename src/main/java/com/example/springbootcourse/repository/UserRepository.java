package com.example.springbootcourse.repository;

import com.example.springbootcourse.dox.User;
import com.example.springbootcourse.dto.AddressUserDTO;

import com.example.springbootcourse.dto.UserAddressDTO;
import com.example.springbootcourse.mapper.UserExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    @Query(value="select * from address a ,user u where a.user_id=u.id and u.id=:uid",
            resultSetExtractorClass = UserExtractor.class)
    UserAddressDTO findByUid(String uid);

}
