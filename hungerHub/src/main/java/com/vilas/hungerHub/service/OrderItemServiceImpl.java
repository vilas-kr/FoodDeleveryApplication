package com.vilas.hungerHub.service;

import com.vilas.hungerHub.entity.OrderItem;
import com.vilas.hungerHub.repository.OrderItemRepository;
import com.vilas.hungerHub.serviceInterface.IdSequenceService;
import com.vilas.hungerHub.serviceInterface.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private final IdSequenceService sequenceService;

    @Autowired
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem){

        int seq = sequenceService.getSequence("ORDERITEM_seq");
        orderItem.setOrderItemId(String.format("%09d", seq));
        return orderItemRepository.save(orderItem);

    }

    @Override
    public List<OrderItem> createOrderItems(List<OrderItem> orderItems){

        List<OrderItem> itemList = new ArrayList<>();
        for(OrderItem item : orderItems)
            itemList.add(createOrderItem(item));
        return itemList;

    }

}
