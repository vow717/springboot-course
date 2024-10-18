package com.example.springbootcourse.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @CreatedBy
    private String id;
    private String name;
    private String userId;
    @ReadOnlyProperty
    private LocalDateTime createdTime;
    @ReadOnlyProperty
    private LocalDateTime updatedTime;
}
