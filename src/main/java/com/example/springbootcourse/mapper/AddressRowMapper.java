package com.example.springbootcourse.mapper;

import com.example.springbootcourse.dox.Address;
import com.example.springbootcourse.dox.User;
import com.example.springbootcourse.dto.AddressUserDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

// mapper是用来将数据库查询结果映射到对象的工具，这里是将查询结果映射到AddressUserDTO对象
public class AddressRowMapper implements RowMapper<AddressUserDTO> {
    @Override
    public AddressUserDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        return AddressUserDTO.builder()
                .user(User.builder()
                        .id(resultSet.getString("u.id"))
                        .name(resultSet.getString("name"))
                        .createdTime(resultSet.getObject("u.created_time", LocalDateTime.class))
                        .build())
                .address(Address.builder()
                        .id(resultSet.getString("a.id"))
                        .name(resultSet.getString("name"))
                        .userId(resultSet.getString("a.user_id"))
                        .createdTime(resultSet.getObject("a.created_time", LocalDateTime.class))
                        .build())
                .build();
    }
}
