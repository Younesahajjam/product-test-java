package com.example.test.mapper;

import com.example.test.dto.ItemDTO;
import com.example.test.entity.Cart;
import com.example.test.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO toItemDTO(Item item) {
        if (item == null) {
            return null;
        }
        return ItemDTO.builder()
                .oid(item.getOid())
                .code(item.getCode())
                .name(item.getName())
                .description(item.getDescription())
                .image(item.getImage())
                .category(item.getCategory())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .internalReference(item.getInternalReference())
                .shellId(item.getShellId())
                .inventoryStatus(item.getInventoryStatus())
                .rating(item.getRating())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .cartId(item.getCart() != null ? item.getCart().getOid() : null)
                .build();
    }

    public static Item toItemEntity(ItemDTO itemDTO, Cart cart) {
        if (itemDTO == null) {
            return null;
        }
        return Item.builder()

                .code(itemDTO.getCode())
                .name(itemDTO.getName())
                .description(itemDTO.getDescription())
                .image(itemDTO.getImage())
                .category(itemDTO.getCategory())
                .price(itemDTO.getPrice())
                .quantity(itemDTO.getQuantity())
                .internalReference(itemDTO.getInternalReference())
                .shellId(itemDTO.getShellId())
                .inventoryStatus(itemDTO.getInventoryStatus())
                .rating(itemDTO.getRating())
                .createdAt(itemDTO.getCreatedAt())
                .updatedAt(itemDTO.getUpdatedAt())
                .cart(cart)
                .build();
    }

    public static Item toItemEntityWithoutCart(ItemDTO itemDTO) {
        if (itemDTO == null) {
            return null;
        }
        return Item.builder()

                .code(itemDTO.getCode())
                .name(itemDTO.getName())
                .description(itemDTO.getDescription())
                .image(itemDTO.getImage())
                .category(itemDTO.getCategory())
                .price(itemDTO.getPrice())
                .quantity(itemDTO.getQuantity())
                .internalReference(itemDTO.getInternalReference())
                .shellId(itemDTO.getShellId())
                .inventoryStatus(itemDTO.getInventoryStatus())
                .rating(itemDTO.getRating())
                .build();
    }


}
