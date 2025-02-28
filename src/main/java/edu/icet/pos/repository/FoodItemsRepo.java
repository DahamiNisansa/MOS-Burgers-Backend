package edu.icet.pos.repository;

import edu.icet.pos.entity.CustomerEntity;
import edu.icet.pos.entity.FoodItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodItemsRepo extends JpaRepository<FoodItemsEntity,Integer> {

    Object findByItemCode(String itemCode);

    List<FoodItemsEntity> findByName(String name);

    List<FoodItemsEntity> findByStockLessThan(Integer threshold);

    List<FoodItemsEntity> findByExpirationDateBefore(LocalDate today);
}
