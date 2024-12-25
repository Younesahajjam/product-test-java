package com.example.test.repository;

import com.example.test.entity.Cart;
import com.example.test.entity.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCostumerAndStatus(Costumer costumer, String status);

}
