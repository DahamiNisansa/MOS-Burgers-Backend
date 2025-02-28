package edu.icet.pos.repository;

import edu.icet.pos.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {
}
