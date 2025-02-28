package edu.icet.pos.service.impl;

import edu.icet.pos.dto.CustomerDto;
import edu.icet.pos.dto.FoodItemsDto;
import edu.icet.pos.entity.CategoryEntity;
import edu.icet.pos.entity.CustomerEntity;
import edu.icet.pos.entity.FoodItemsEntity;
import edu.icet.pos.repository.FoodItemsRepo;
import edu.icet.pos.service.FoodItemsService;
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

    final FoodItemsRepo repo;
    final ModelMapper mapper;

    @Override
    public void addFoodItem(FoodItemsDto foodItems) {
        repo.save(mapper.map(foodItems, FoodItemsEntity.class));

        /* Map DTO to Entity
        FoodItemsEntity foodEntity = mapper.map(foodItems, FoodItemsEntity.class);

        // Fetch category from the database
        FoodItemsEntity category = repo.findById(foodItems.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Set category to food item
        foodEntity.setCategory(mapper.map(category, CategoryEntity.class));

        // Save food item with assigned category
        repo.save(foodEntity); */
    }


    @Override
    public void updateFoodItem(FoodItemsDto foodItem) {
        repo.save(mapper.map(foodItem, FoodItemsEntity.class));
    }


    @Override
    public void deleteFoodItem(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<FoodItemsDto> getAllFoodItems() {
        List<FoodItemsDto> foodItemList = new ArrayList<>();
        List<FoodItemsEntity> all = repo.findAll();

        all.forEach(foodItemsEntity ->  {
            foodItemList.add(mapper.map(foodItemsEntity, FoodItemsDto.class));
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
        List<FoodItemsEntity> lowStockItems = repo.findByStockLessThan(threshold);
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
