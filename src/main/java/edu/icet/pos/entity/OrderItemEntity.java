package edu.icet.pos.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "order_items")

public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "food_item_id", nullable = false)
    private FoodItemsEntity foodItemId;

    //private String foodItemName;
    private Integer quantity;
    private Double price;
    private Double totalPrice;



    //private Integer quantity;
    //private Double unitPrice;
    //private Double totalPrice;
    //
}
