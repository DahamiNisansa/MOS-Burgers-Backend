package edu.icet.pos.service.impl;

import edu.icet.pos.dto.OrderDto;
import edu.icet.pos.dto.OrderItemDto;
import edu.icet.pos.entity.CustomerEntity;
import edu.icet.pos.entity.FoodItemsEntity;
import edu.icet.pos.entity.OrderEntity;
import edu.icet.pos.entity.OrderItemEntity;
import edu.icet.pos.enums.OrderStatus;
import edu.icet.pos.repository.CustomerRepo;
import edu.icet.pos.repository.FoodItemsRepo;
import edu.icet.pos.repository.OrderItemRepo;
import edu.icet.pos.repository.OrderRepo;
import edu.icet.pos.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {

    final OrderRepo repoOrder;
    final OrderItemRepo repoOrderItem;
    final CustomerRepo repoCustomer;
    final FoodItemsRepo repoFoodItems;
    final ModelMapper mapper;

    @Override
    public OrderDto processNewOrder(OrderDto orderDto) {
        //repoO.save(mapper.map(order, OrderEntity.class));
        //OrderDto finalOrder;

        // Fetch customer, create if not found
        //OrderEntity orders = mapper.map(orderDto, OrderEntity.class);
        //if (orders.getId() != null) {


            CustomerEntity customer = repoCustomer.findById(orderDto.getCustomerId())
                    .orElseGet(() -> {
                        CustomerEntity newCustomer = new CustomerEntity();
                        newCustomer.setName(orderDto.getCustomerName());
                        return repoCustomer.save(newCustomer);
                    });


            // Create new order
            OrderEntity order = new OrderEntity();
            order.setCustomer(customer);
            order.setOrderStatus(OrderStatus.PENDING);
            order.setOrderDate(new Date());

            //final double[] sub = {0};
            double subTotal = 0;
            double discount;
            double loyaltyPointsAmount = 0;
            double tax;


            // Process Order Items
            List<OrderItemEntity> orderItems = orderDto.getOrderItems().stream().map(itemDto -> {
                FoodItemsEntity foodItem = repoFoodItems.findById(itemDto.getFoodItemId()).orElseThrow(() -> new RuntimeException("Food item not found"));

                OrderItemEntity orderItem = new OrderItemEntity();
                orderItem.setOrder(order);
                orderItem.setFoodItemId(foodItem);
                orderItem.setQuantity(itemDto.getQuantity());
                orderItem.setPrice(foodItem.getPrice());
                orderItem.setTotalPrice(foodItem.getPrice() * itemDto.getQuantity());

                //sub[0] += orderItem.getTotalPrice();
                return orderItem;
            }).collect(Collectors.toList());

        subTotal = orderItems.stream().mapToDouble(OrderItemEntity::getTotalPrice).sum();


            // Apply Discounts, Loyalty Points, Tax
            if (customer.getLoyaltyPoints() > 0) {
                loyaltyPointsAmount = customer.getLoyaltyPoints() * 0.1; // 10% value per point
                customer.setLoyaltyPoints(0);
                repoCustomer.save(customer);
            }
            discount = subTotal * 0.05; // 5% discount
            tax = subTotal * 0.07; // 7% tax

            double finalAmount = subTotal - discount - loyaltyPointsAmount + tax;


            // Save Order
            order.setSubtotal(subTotal);
            order.setDiscount(discount);
            order.setTax(tax);
            order.setLoyaltyPointsAmount(loyaltyPointsAmount);
            order.setFinalTotal(finalAmount);
            order.setOrderItems(orderItems);
            repoOrder.save(order);

            // ** Convert OrderEntity to OrderDto for Response **
            //finalOrder = new OrderDto();
            OrderDto finalOrder = mapper.map(order, OrderDto.class);
            finalOrder.setCustomerName(customer.getName());
            finalOrder.setSubtotal(subTotal);
            finalOrder.setDiscount(discount);
            finalOrder.setTax(tax);
            finalOrder.setLoyaltyPointsUsed(customer.getLoyaltyPoints());
            finalOrder.setLoyaltyPointsAmount(loyaltyPointsAmount);
            finalOrder.setFinalTotal(finalAmount);

            // Convert OrderItemEntity List to OrderItemDto List
            List<OrderItemDto> orderItemDtos = orderItems.stream().map(orderItem -> {
                OrderItemDto dto = new OrderItemDto();
                dto.setFoodItemName(orderItem.getFoodItemId().getName()); // Get food item name
                dto.setQuantity(orderItem.getQuantity());
                dto.setPrice(orderItem.getPrice());
                dto.setTotal(orderItem.getTotalPrice());
                return dto;
            }).collect(Collectors.toList());

            finalOrder.setOrderItems(orderItemDtos);

            return finalOrder;


            //finalOrder = mapper.map(order, OrderDto.class);

        //}

        //return finalOrder;
    }
}
