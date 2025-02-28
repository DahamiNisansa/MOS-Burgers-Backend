package edu.icet.pos.service.impl;

import edu.icet.pos.dto.CategoryDto;
import edu.icet.pos.dto.FoodItemsDto;
import edu.icet.pos.entity.CategoryEntity;
import edu.icet.pos.entity.FoodItemsEntity;
import edu.icet.pos.repository.CategoryRepo;
import edu.icet.pos.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {

    final CategoryRepo repo;
    final ModelMapper mapper;

    @Override
    public void createCategory(CategoryDto category) {
        repo.save(mapper.map(category, CategoryEntity.class));
    }

    @Override
    public void updateCategory(CategoryDto category) {
        repo.save(mapper.map(category, CategoryEntity.class));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryList = new ArrayList<>();
        List<CategoryEntity> all = repo.findAll();

        all.forEach(categoryEntity ->   {
            categoryList.add(mapper.map(categoryEntity, CategoryDto.class));
        });

        return categoryList;
    }

    @Override
    public void deleteCategory(Integer id) {
        repo.deleteById(id);
    }





    @Override
    public List<FoodItemsDto> getFoodItemsByCategory(Integer categoryId) {
        CategoryEntity category = repo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<FoodItemsDto> foodItemsDtoList = new ArrayList<>();

        for (FoodItemsEntity food : category.getFoodItems()) {
            foodItemsDtoList.add(mapper.map(food, FoodItemsDto.class));
        }
        return foodItemsDtoList;
    }


}
