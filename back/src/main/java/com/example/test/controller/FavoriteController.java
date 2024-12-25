package com.example.test.controller;

import com.example.test.dto.FavotiteDto;
import com.example.test.service.servicelmp.favorit.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add/{customerId}/{itemId}")
    public ResponseEntity<FavotiteDto> addItemToFavorite(@PathVariable Long customerId, @PathVariable Long itemId) {
        FavotiteDto favoriteDTO = favoriteService.addItemToFavorite(customerId, itemId);
        return ResponseEntity.ok(favoriteDTO);
    }

    @DeleteMapping("/remove/{customerId}/{itemId}")
    public ResponseEntity<Void> removeItemFromFavorite(@PathVariable Long customerId, @PathVariable Long itemId) {
        favoriteService.removeItemFromFavorite(customerId, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<FavotiteDto>> getAllFavorites(@PathVariable Long customerId) {
        List<FavotiteDto> favorites = favoriteService.getAllFavorites(customerId);
        return ResponseEntity.ok(favorites);
    }
}
