package com.vilas.hungerHub.controllers;

import com.vilas.hungerHub.dto.OrderDTO;
import com.vilas.hungerHub.serviceInterface.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }

    @GetMapping
    public OrderDTO getOrder(@RequestBody OrderDTO orderDTO){
        return orderService.getOrderDetails(orderDTO.getId());
    }

}
