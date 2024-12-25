package com.example.test.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long oid;
    private String status;
    private Long clientId;
    private List<ItemDTO> items;
}
