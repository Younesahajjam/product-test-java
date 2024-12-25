package com.example.test.repository;

import com.example.test.entity.Costumer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface CostumerRepo extends JpaRepository<Costumer,Long> {
    Optional<Costumer> findByEmail(String email);

    Page<Costumer> findAll(Specification<Costumer> spec, Pageable pageable);
}
