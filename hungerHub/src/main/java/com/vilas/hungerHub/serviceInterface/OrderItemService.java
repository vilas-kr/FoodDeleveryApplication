package com.vilas.hungerHub.serviceInterface;

import com.vilas.hungerHub.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItem orderItem);

    List<OrderItem> createOrderItems(List<OrderItem> orderItems);
}
