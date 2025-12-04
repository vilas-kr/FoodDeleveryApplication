package com.vilas.hungerHub.service;

import com.vilas.hungerHub.dto.OrderDTO;
import com.vilas.hungerHub.entity.Order;
import com.vilas.hungerHub.entity.OrderItem;
import com.vilas.hungerHub.entity.Status;
import com.vilas.hungerHub.exception.OrderNotFoundException;
import com.vilas.hungerHub.mapper.EntityFetcher;
import com.vilas.hungerHub.mapper.OrderMapper;
import com.vilas.hungerHub.repository.OrderRepository;
import com.vilas.hungerHub.serviceInterface.IdSequenceService;
import com.vilas.hungerHub.serviceInterface.MenuService;
import com.vilas.hungerHub.serviceInterface.OrderItemService;
import com.vilas.hungerHub.serviceInterface.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final IdSequenceService sequenceService;

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final OrderMapper orderMapper;

    @Autowired
    private final EntityFetcher entityFetcher;

    @Autowired
    private final OrderItemService orderItemService;

    @Autowired
    private final MenuService menuService;

    @Transactional
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO){

        if(orderDTO.getOrderItems() == null){
            throw new RuntimeException("Empty Orders are Not Accepted");
        }
        Order order = orderMapper.toEntity(orderDTO, entityFetcher);
        Order newOrder = createOrder(order);
        //save order details in each items
        for(OrderItem items : order.getOrderItems())
            items.setOrder(newOrder);
        newOrder.setOrderItems(orderItemService.createOrderItems(order.getOrderItems()));
        calculateTotalAmount(newOrder);
        orderRepository.save(newOrder);
        return orderMapper.toDTO(newOrder);

    }

    @Override
    public Order createOrder(Order order){

        //initialize primarykey
        Order newOrder = new Order(order);
        int val = sequenceService.getSequence("ORDER_seq");
        String prefix = "ORD";
        newOrder.setOrderId(prefix + String.format("%06d", val));
        newOrder.setStatus(Status.RECIVED);
        newOrder.setTotalAmount(BigDecimal.ZERO);
        newOrder.setOrderItems(null);
        return orderRepository.save(newOrder);

    }

    @Override
    public void calculateTotalAmount(Order order) {
        BigDecimal totalAmount = BigDecimal.ZERO; // Better than valueOf(0)

        for (OrderItem item : order.getOrderItems()) {
            BigDecimal itemTotal = item.getMenu().getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(itemTotal); // Reassign the sum
        }

        order.setTotalAmount(totalAmount);
    }

    @Override
    public OrderDTO getOrderDetails(String id) {
        return orderMapper.toDTO(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Invalid Order Number")));
    }


}
