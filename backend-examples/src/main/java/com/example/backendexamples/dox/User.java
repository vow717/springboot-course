package com.example.backendexamples.dox;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{
    public static final String USER="a34v";
    public static final String ADMIN="O7CS";
    @Id
    @CreatedBy
    private String id;
    private String name;
    private String account;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String role;

    @ReadOnlyProperty
    private LocalDateTime createTime;
    @ReadOnlyProperty
    private LocalDateTime updateTime;
}