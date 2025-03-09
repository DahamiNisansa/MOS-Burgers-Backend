package edu.icet.pos.dto;

import edu.icet.pos.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDto {

    private Integer id;
    private Integer customerId;
    private String customerName;
    private List<OrderItemDto> orderItems;
    private Integer foodItemId;
    private Integer quantity;

    private Double subtotal;
    private Double discount;
    private Integer loyaltyPointsUsed;
    private Double loyaltyPointsAmount;
    private Double tax;
    private Double finalTotal;
    private String paymentMethod;
    private LocalDateTime orderDate;



    //private OrderStatus orderStatus;
    //private Double totalAmount;
    //private Double discountAmount;
    //private Double finalAmount;
    //private LocalDateTime orderDate;
    //private List<OrderItemDto> orderItems;
    //private PaymentDto payment;
}
