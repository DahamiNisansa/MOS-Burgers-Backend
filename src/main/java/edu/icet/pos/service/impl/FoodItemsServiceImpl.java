package edu.icet.pos.service.impl;

import edu.icet.pos.dto.CustomerDto;
import edu.icet.pos.dto.FoodItemsDto;
import edu.icet.pos.entity.CategoryEntity;
import edu.icet.pos.entity.CustomerEntity;
import edu.icet.pos.entity.FoodItemsEntity;
import edu.icet.pos.repository.CategoryRepo;
import edu.icet.pos.repository.FoodItemsRepo;
import edu.icet.pos.service.FoodItemsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class FoodItemsServiceImpl implements FoodItemsService {

    final CategoryRepo repoCa;
    final FoodItemsRepo repo;
    final ModelMapper mapper;

    @Override
    public void addFoodItem(FoodItemsDto foodItems) {
        //repo.save(mapper.map(foodItems, FoodItemsEntity.class));

        // Convert DTO to entity using ModelMapper
        FoodItemsEntity foodItem = mapper.map(foodItems, FoodItemsEntity.class);

        // Fetch and set the category manually
        if (foodItems.getCategory() != null) {
            CategoryEntity category = repoCa.findById(foodItems.getCategory())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + foodItems.getCategory()));
            foodItem.setCategory(category);
        }

        repo.save(foodItem);
    }



    @Override
    public void updateFoodItem(FoodItemsDto foodItemDto) {
        //repo.save(mapper.map(foodItem, FoodItemsEntity.class));

        // Fetch the existing food item from the database
        FoodItemsEntity existingFoodItem = repo.findById(foodItemDto.getId())
                .orElseThrow(() -> new RuntimeException("Food item not found with ID: " + foodItemDto.getId()));

        // Map the DTO fields to the existing entity (excluding ID to avoid primary key changes)
        mapper.map(foodItemDto, existingFoodItem);

        // Manually set the category if categoryId is provided
        if (foodItemDto.getCategory() != null) {
            CategoryEntity category = repoCa.findById(foodItemDto.getCategory())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + foodItemDto.getCategory()));
            existingFoodItem.setCategory(category);
        }

        repo.save(existingFoodItem);
    }



    @Override
    public void deleteFoodItem(Integer id) {
        repo.deleteById(id);
    }



    @Transactional
    @Override
    public List<FoodItemsDto> getAllFoodItems() {
        List<FoodItemsDto> foodItemList = new ArrayList<>();
        List<FoodItemsEntity> all = repo.findAll();

        all.forEach(foodItemsEntity ->  {
            FoodItemsDto dto = mapper.map(foodItemsEntity, FoodItemsDto.class);

            // Manually set categoryId if category is not null
            if (foodItemsEntity.getCategory() != null) {
                dto.setCategory(foodItemsEntity.getCategory().getId());
            }

            foodItemList.add(dto);
        });

        return foodItemList;
    }



    @Override
    public FoodItemsDto getFoodItemById(Integer id) {
        return mapper.map(repo.findById(id), FoodItemsDto.class);
    }


    @Override
    public FoodItemsDto getFoodItemByItemCode(String itemCode) {
        return mapper.map(repo.findByItemCode(itemCode), FoodItemsDto.class);
    }


    @Override
    public List<FoodItemsDto> getFoodItemByName(String name) {
        List<FoodItemsEntity> byName = repo.findByName(name);
        List<FoodItemsDto> foodItemList = new ArrayList<>();

        byName.forEach(foodItemsEntity -> {
            foodItemList.add(mapper.map(foodItemsEntity, FoodItemsDto.class));
        });

        return foodItemList;
    }



    @Override
    public List<FoodItemsDto> getLowStockItems(Integer threshold) {
        List<FoodItemsEntity> lowStockItems = repo.findByStockQuantityLessThan(threshold);
        return lowStockItems.stream()
                .map(item -> mapper.map(item, FoodItemsDto.class))
                .collect(Collectors.toList());
    }



    @Override
    public List<FoodItemsDto> getExpiredFoodItems() {
        LocalDate today = LocalDate.now();
        List<FoodItemsEntity> expiredItems = repo.findByExpirationDateBefore(today);
        return expiredItems.stream()
                .map(item -> mapper.map(item, FoodItemsDto.class))
                .collect(Collectors.toList());
    }


}
