package com.example.springbootcourse.dto;

import com.example.springbootcourse.dox.Address;
import com.example.springbootcourse.dox.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressUserDTO {
    private User user;
    private Address address;
}
