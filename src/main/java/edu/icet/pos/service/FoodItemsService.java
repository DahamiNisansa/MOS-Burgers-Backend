package edu.icet.pos.service;

import edu.icet.pos.dto.FoodItemsDto;

import java.util.List;

public interface FoodItemsService {
    void addFoodItem(FoodItemsDto foodItemsDto);

    void updateFoodItem(FoodItemsDto foodItem);

    void deleteFoodItem(Integer id);

    List<FoodItemsDto> getAllFoodItems();

    FoodItemsDto getFoodItemById(Integer id);

    FoodItemsDto getFoodItemByItemCode(String itemCode);

    List<FoodItemsDto> getFoodItemByName(String name);

    List<FoodItemsDto> getLowStockItems(Integer threshold);

    List<FoodItemsDto> getExpiredFoodItems();
}
