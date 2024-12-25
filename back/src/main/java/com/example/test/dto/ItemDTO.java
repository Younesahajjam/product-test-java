package com.example.test.dto;

import com.example.test.entity.Item;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long oid;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Long quantity;
    private String internalReference;
    private Long shellId;
    private Item.InventoryStatus inventoryStatus;
    private Double rating;
    private Long cartId;
    private Long createdAt;
    private Long updatedAt;
}
