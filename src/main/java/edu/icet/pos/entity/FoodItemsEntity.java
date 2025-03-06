package edu.icet.pos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "category")

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("foodItems")
    private CategoryEntity category;


}
