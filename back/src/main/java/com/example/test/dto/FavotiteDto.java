package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FavotiteDto {

    private Long id;
    private Long customerId;
    private Long itemId;
    private String itemName;
}
