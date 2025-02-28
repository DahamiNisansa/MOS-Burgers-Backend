package edu.icet.pos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "food_items")

public class FoodItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String itemCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    private Integer stockQuantity;
    private LocalDate expirationDate;
    private Double discountPercentage;
    //private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id") //, nullable = false
    private CategoryEntity category;
}
