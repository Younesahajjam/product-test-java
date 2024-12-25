package com.example.test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long oid;
    private String status;

    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Item> items;


}
