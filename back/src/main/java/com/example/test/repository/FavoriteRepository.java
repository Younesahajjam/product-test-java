package com.example.test.repository;

import com.example.test.entity.Costumer;
import com.example.test.entity.Favorite;
import com.example.test.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByCostumer(Costumer costumer);


    Optional<Favorite> findByCostumerAndItem(Costumer costumer, Item item);


    void deleteByCostumerAndItem(Costumer costumer, Item item);
}
