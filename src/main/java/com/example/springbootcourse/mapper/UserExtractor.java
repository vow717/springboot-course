package com.example.springbootcourse.mapper;

import com.example.springbootcourse.dox.Address;
import com.example.springbootcourse.dox.User;
import com.example.springbootcourse.dto.UserAddressDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserExtractor implements ResultSetExtractor<UserAddressDTO> {
    @Override
    public UserAddressDTO extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        User user =null;
        List<Address> addresses = new ArrayList<>();
        while (resultSet.next()) {
            if(user == null){
                user = User.builder()
                        .id(resultSet.getString("u.id"))
                        .name(resultSet.getString("name"))
                        .createdTime(resultSet.getObject("u.created_time", LocalDateTime.class))
                        .build();
            }
            Address address = Address.builder()
                    .id(resultSet.getString("a.id"))
                    .name(resultSet.getString("name"))
                    .userId(resultSet.getString("a.user_id"))
                    .createdTime(resultSet.getObject("a.created_time", LocalDateTime.class))
                    .build();
            addresses.add(address);

        }
        return UserAddressDTO.builder()
                .user(user)
                .addresses(addresses)
                .build();

    }
}
