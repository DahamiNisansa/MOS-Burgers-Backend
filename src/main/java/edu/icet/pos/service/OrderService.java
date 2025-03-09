package edu.icet.pos.service;

import edu.icet.pos.dto.OrderDto;

public interface OrderService {
    OrderDto processNewOrder(OrderDto order);
}
