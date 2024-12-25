package com.example.test.mapper;

import com.example.test.dto.CartDTO;
import com.example.test.dto.ItemDTO;
import com.example.test.entity.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CartMapper {

    public  CartDTO toCartDTO(Cart cart) {
        List<ItemDTO> itemDTOs = cart.getItems().stream()
                .map(item -> ItemDTO.builder()
                        .oid(item.getOid())
                        .name(item.getName())
                        .quantity(item.getQuantity())
                        .image(item.getImage())
                        .price(item.getPrice())
                        .code(item.getCode())
                        .category(item.getCategory())
                        .description(item.getDescription())
                        .inventoryStatus(item.getInventoryStatus())
                        .shellId(item.getShellId())
                        .rating(item.getRating())
                        .internalReference(item.getInternalReference())
                        .updatedAt(item.getUpdatedAt())
                        .createdAt(item.getCreatedAt())
                        .cartId(item.getCart().getOid())
                        .build())
                .collect(Collectors.toList());

        return CartDTO.builder()
                .oid(cart.getOid())
                .status(cart.getStatus())
                .clientId(cart.getCostumer().getOid())
                .items(itemDTOs)
                .build();
    }

    public static Cart toCartEntity(CartDTO cartDTO) {
        Cart cart = Cart.builder()
                .oid(cartDTO.getOid())
                .status(cartDTO.getStatus())
                .build();

        return cart;
    }
}
