package edu.icet.pos.repository;

import edu.icet.pos.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {
}
