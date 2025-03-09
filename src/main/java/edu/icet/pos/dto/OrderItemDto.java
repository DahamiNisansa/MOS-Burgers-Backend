package edu.icet.pos.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderItemDto {

    private Integer foodItemId;
    private String foodItemName;
    private Integer quantity;
    private Double price;
    private Double total;




    //private Double totalPrice;

    //private Integer foodItemId;
    //private Integer quantity;
    //private Double unitPrice;
    //private Double totalPrice;
}
