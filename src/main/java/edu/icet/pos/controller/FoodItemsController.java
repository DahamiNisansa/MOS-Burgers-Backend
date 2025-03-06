package edu.icet.pos.controller;

import edu.icet.pos.dto.CustomerDto;
import edu.icet.pos.dto.FoodItemsDto;
import edu.icet.pos.service.FoodItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class FoodItemsController {

    final FoodItemsService serviceFI;

    @PostMapping("/food-items/add")
    public void addFoodItem(@RequestBody FoodItemsDto foodItemsDto){
        serviceFI.addFoodItem(foodItemsDto);
    }


    @PutMapping("/food-items/update")
    public void updateFoodItem(@RequestBody FoodItemsDto foodItem){
        serviceFI.updateFoodItem(foodItem);
    }


    @DeleteMapping("/food-items/delete/{id}")
    public void deleteFoodItem(@PathVariable Integer id) {
        serviceFI.deleteFoodItem(id);
    }


    @GetMapping("/food-items/get-all")
    public List<FoodItemsDto> getAllFoodItems(){
        return serviceFI.getAllFoodItems();
    }


    @GetMapping("/food-items/getItem-by-id")
    public FoodItemsDto getFoodItemById(@RequestParam(name = "query") Integer id){
        return serviceFI.getFoodItemById(id);
    }


    @GetMapping("/food-items/getItem-by-itemCode")
    public FoodItemsDto getFoodItemByItemCode(@RequestParam(name = "query") String itemCode){
        return serviceFI.getFoodItemByItemCode(itemCode);
    }


    @GetMapping("/food-items/getItem-by-name")
    public List<FoodItemsDto> getFoodItemByName(@RequestParam(name = "query") String name){
        return serviceFI.getFoodItemByName(name);
    }


    @GetMapping("/food-items/low-stock/{threshold}")
    public ResponseEntity<List<FoodItemsDto>> getLowStockItems(@PathVariable Integer threshold) {
        return ResponseEntity.ok(serviceFI.getLowStockItems(threshold));
    }


    @GetMapping("/food-items/expired")
    public List<FoodItemsDto> getExpiredFoodItems() {
        return serviceFI.getExpiredFoodItems();
    }



}
