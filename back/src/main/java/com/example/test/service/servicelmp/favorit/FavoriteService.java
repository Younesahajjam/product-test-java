package com.example.test.service.servicelmp.favorit;

import com.example.test.dto.FavotiteDto;
import com.example.test.entity.Costumer;
import com.example.test.entity.Favorite;
import com.example.test.entity.Item;
import com.example.test.exeption.ApiRequestException;
import com.example.test.repository.CostumerRepo;
import com.example.test.repository.FavoriteRepository;
import com.example.test.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private CostumerRepo costumerRepository;

    @Autowired
    private ItemRepository itemRepository;

    public FavotiteDto addItemToFavorite(Long customerId, Long itemId) {
        Costumer costumer = costumerRepository.findById(customerId)
                .orElseThrow(() -> new ApiRequestException("Customer not found"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApiRequestException("Item not found"));

        Optional<Favorite> existingFavorite = favoriteRepository.findByCostumerAndItem(costumer, item);
        if (existingFavorite.isPresent()) {
            throw new ApiRequestException("Item is already in favorites");
        }

        Favorite favorite = new Favorite();
        favorite.setCostumer(costumer);
        favorite.setItem(item);

        Favorite savedFavorite = favoriteRepository.save(favorite);

        return new FavotiteDto(savedFavorite.getId(), costumer.getOid(), item.getOid(), item.getName());
    }
    @Transactional
    public void removeItemFromFavorite(Long customerId, Long itemId) {
        Costumer costumer = costumerRepository.findById(customerId)
                .orElseThrow(() -> new ApiRequestException("Customer not found"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApiRequestException("Item not found"));

        favoriteRepository.deleteByCostumerAndItem(costumer, item);
    }

    public List<FavotiteDto> getAllFavorites(Long customerId) {
        Costumer costumer = costumerRepository.findById(customerId)
                .orElseThrow(() -> new ApiRequestException("Customer not found"));

        List<Favorite> favorites = favoriteRepository.findByCostumer(costumer);

        return favorites.stream()
                .map(fav -> new FavotiteDto(fav.getId(), costumer.getOid(), fav.getItem().getOid(), fav.getItem().getName()))
                .collect(Collectors.toList());
    }
}