package edu.icet.pos.controller;

import edu.icet.pos.dto.FoodItemsDto;
import edu.icet.pos.dto.OrderDto;
import edu.icet.pos.entity.OrderEntity;
import edu.icet.pos.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class OrderController {

    final OrderService orderS;

    @PostMapping("/order/process-newOrder")
    public ResponseEntity<OrderDto> processNewOrder(@RequestBody OrderDto order){
       return ResponseEntity.ok(orderS.processNewOrder(order));
    }
}
