package edu.icet.pos.controller;


import edu.icet.pos.dto.CategoryDto;
import edu.icet.pos.dto.FoodItemsDto;
import edu.icet.pos.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class CategoryController {

    final CategoryService serviceC;

    @PostMapping("/category/create")
    public void createCategory(@RequestBody CategoryDto category){
        serviceC.createCategory(category);
    }


    @PutMapping("/category/update")
    public void updateCategory(@RequestBody CategoryDto category){
        serviceC.updateCategory(category);
    }


    @GetMapping("/category/get-all")
    public List<CategoryDto> getAllCategories(){
        return serviceC.getAllCategories();
    }


    @DeleteMapping("/category/delete/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        serviceC.deleteCategory(id);
    }


    @GetMapping("/category/get-foods-by-category/{id}")
    public ResponseEntity<List<FoodItemsDto>> getFoodItemsByCategory(@PathVariable Integer id) {
        List<FoodItemsDto> foodItems = serviceC.getFoodItemsByCategory(id);
        return ResponseEntity.ok(foodItems);
    }


}
