package com.vilas.hungerHub.serviceInterface;

import com.vilas.hungerHub.dto.OrderDTO;
import com.vilas.hungerHub.entity.Order;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    @Transactional
    OrderDTO createOrder(OrderDTO orderDTO);

    Order createOrder(Order order);

    void calculateTotalAmount(Order order);
}
