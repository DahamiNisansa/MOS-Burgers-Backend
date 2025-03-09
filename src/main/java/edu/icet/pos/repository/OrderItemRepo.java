package edu.icet.pos.repository;

import edu.icet.pos.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItemEntity, Integer> {
}
