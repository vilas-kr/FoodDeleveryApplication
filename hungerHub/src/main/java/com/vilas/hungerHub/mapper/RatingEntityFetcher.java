package com.vilas.hungerHub.mapper;

import com.vilas.hungerHub.serviceInterface.MenuService;
import com.vilas.hungerHub.serviceInterface.OrderService;
import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@Component
public class RatingEntityFetcher {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MenuService menuService;

}
