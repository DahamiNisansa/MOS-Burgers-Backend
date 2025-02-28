package edu.icet.pos.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CategoryDto {

    private Integer id;
    private String name;

    //private List<FoodItemsEntity> foodItems;
}
