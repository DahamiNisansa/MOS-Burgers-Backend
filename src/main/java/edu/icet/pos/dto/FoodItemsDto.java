package edu.icet.pos.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class FoodItemsDto {
    private Integer id;

    private String itemCode;
    private String name;
    private Double price;
    private Integer stockQuantity;
    private LocalDate expirationDate;
    private Double discountPercentage;

    private Integer category;
}
