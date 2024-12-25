package com.example.test.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long oid;

 
    private String code;

    private String name;

    private String description;

    private String image;

    private String category;

    private Double price;


    private Long quantity;

    private String internalReference;

    private Long shellId;

    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

    private Double rating;

    @Column(name = "product_created_at")
    private Long createdAt;

    @Column(name = "product_updated_at")
    private Long updatedAt;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public enum InventoryStatus {
        INSTOCK,
        LOWSTOCK,
        OUTOFSTOCK
    }



    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }
}
