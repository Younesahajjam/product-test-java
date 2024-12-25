package com.example.test.service.servicelmp.cart;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.test.dto.CartDTO;
import com.example.test.dto.ItemDTO;
import com.example.test.entity.Cart;
import com.example.test.entity.Costumer;
import com.example.test.entity.Item;
import com.example.test.exeption.ApiRequestException;
import com.example.test.mapper.CartMapper;
import com.example.test.mapper.ItemMapper;
import com.example.test.repository.CartRepository;
import com.example.test.repository.CostumerRepo;
import com.example.test.repository.ItemRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CartServiceImp {


    private final  CostumerRepo costumerRepo;
    private final  CartRepository cartRepository;


    private final CartMapper cartMapper;

    private final ItemRepository itemRepository;
    public CartDTO addProductsToCart(Long clientId, List<ItemDTO> itemDTOs) {

        Costumer client = costumerRepo.findById(clientId)
                .orElseThrow(() -> new ApiRequestException("Client not found"));

        System.out.println("Client found: " + client.getName());

        Cart cart = cartRepository.findByCostumerAndStatus(client, "ACTIVE")
                .orElseThrow(() -> new ApiRequestException("Client has no active cart"));

        System.out.println("Retrieved Cart: " + cart);

        if (!cart.getStatus().equals("ACTIVE")) {
            cart.setStatus("ACTIVE");
            cartRepository.save(cart);
        }

        List<Item> items = itemDTOs.stream()
                  .map(itemDTO -> ItemMapper.toItemEntity(itemDTO, cart))
                  .collect(Collectors.toList());
                  itemRepository.saveAll(items);

        cart.getItems().addAll(items);

        cartRepository.save(cart);

        return cartMapper.toCartDTO(cart);
    }
    public CartDTO getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ApiRequestException("Cart not found"));
        return cartMapper.toCartDTO(cart);
    }
    public Cart getOrCreateActiveCartForCostumer(Long costumerId) {
        Costumer costumer = costumerRepo.findById(costumerId)
                .orElseThrow(() -> new ApiRequestException("Customer not found"));

        Optional<Cart> activeCart = cartRepository.findByCostumerAndStatus(costumer, "ACTIVE");

        if (activeCart.isPresent()) {
            return activeCart.get();
        } else {
            Cart newCart = new Cart();
            newCart.setCostumer(costumer);
            newCart.setStatus("ACTIVE");
            return cartRepository.save(newCart);
        }

    }

    public List<CartDTO> findAll() {

        List<Cart> testList=cartRepository.findAll();

        return testList.stream().map(cartMapper::toCartDTO)
                .collect(Collectors.toList());
    }

    public void deleteProductFromCart(Long cartId, Long itemId) {
        // Récupérer le panier actif
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ApiRequestException("Cart not found"));
    
        // Vérifier que le panier est actif
        if (!cart.getStatus().equals("ACTIVE")) {
            throw new ApiRequestException("Only active carts can have items removed.");
        }
    
        // Trouver l'élément à supprimer
        Item itemToRemove = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApiRequestException("Item not found"));
    
        // Vérifier si l'élément appartient bien au panier
        if (!cart.getItems().contains(itemToRemove)) {
            throw new ApiRequestException("Item is not in the cart.");
        }
    
        // Retirer l'élément du panier
        cart.getItems().remove(itemToRemove);
    
        // Sauvegarder les modifications
        cartRepository.save(cart);
        itemRepository.delete(itemToRemove);  // Supprimer l'élément de la base de données si nécessaire
    }
    
}