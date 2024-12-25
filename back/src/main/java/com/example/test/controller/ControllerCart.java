package com.example.test.controller;

import com.example.test.dto.CartDTO;
import com.example.test.dto.ItemDTO;
import com.example.test.entity.Cart;
import com.example.test.exeption.ApiRequestException;
import com.example.test.service.servicelmp.cart.CartServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class ControllerCart {

    @Autowired
    private CartServiceImp cartService;



    @PostMapping("/{clientId}/add")
    public CartDTO addProductsToCart(@PathVariable Long clientId, @RequestBody List<ItemDTO> itemDTOs) {
        return cartService.addProductsToCart(clientId, itemDTOs);
    }


    @GetMapping("/getOrCreateActiveCart/{costumerId}")
    public Cart getOrCreateActiveCart(@PathVariable Long costumerId) {
        return cartService.getOrCreateActiveCartForCostumer(costumerId);
    }

    @GetMapping("/{cartId}")
    public CartDTO getCart(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CartDTO>> findAllTest() {
        try {
            List<CartDTO> cartDTOList = cartService.findAll();
            return new ResponseEntity<>(cartDTOList, HttpStatus.OK);
        } catch (Exception e) {
            throw new ApiRequestException("Oops! Cannot get all data.");
        }
    }

    @DeleteMapping("/{cartId}/remove/{itemId}")
public ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
    cartService.deleteProductFromCart(cartId, itemId);
    return new ResponseEntity<>("Product removed from cart successfully.", HttpStatus.OK);
}


}
