package edu.icet.pos.entity;

import edu.icet.pos.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "orders")

public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer; // Link to customer

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL /*, fetch = FetchType.LAZY*/ )
    private List<OrderItemEntity> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    private Double subtotal;
    private Double discount;
    private Integer loyaltyPointsUsed;
    private Double loyaltyPointsAmount;
    private Double tax;
    private Double finalTotal;
    private Date orderDate;



    //@Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    //private PaymentMethod paymentMethod;


    //private Double totalAmount;
    //private Double discountAmount;
    //private Double finalAmount;
    //private LocalDateTime orderDate;

    //@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    //private PaymentEntity payment;
}
