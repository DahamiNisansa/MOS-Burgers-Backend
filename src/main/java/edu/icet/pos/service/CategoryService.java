package edu.icet.pos.service;

import edu.icet.pos.dto.CategoryDto;
import edu.icet.pos.dto.FoodItemsDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto category);

    void updateCategory(CategoryDto category);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Integer id);

    List<FoodItemsDto> getFoodItemsByCategory(Integer id);
}
