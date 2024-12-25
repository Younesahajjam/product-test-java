package com.example.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class CostumerDto {
    private Long oid;
    private String name;
    private String email;
    private String password;
    private Long age;
    private String role;
}
