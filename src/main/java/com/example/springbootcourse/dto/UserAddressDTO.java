package com.example.springbootcourse.dto;

import com.example.springbootcourse.dox.Address;
import com.example.springbootcourse.dox.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDTO {
    private User user;
    private List<Address> addresses;
}
