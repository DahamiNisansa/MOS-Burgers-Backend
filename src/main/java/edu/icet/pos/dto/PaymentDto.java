package edu.icet.pos.dto;

import edu.icet.pos.enums.PaymentMethod;
import edu.icet.pos.enums.PaymentStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentDto {

    private Integer id;
    private Integer orderId;
    private PaymentMethod paymentMethod;
    private Double amountPaid;
    private PaymentStatus paymentStatus;
}
