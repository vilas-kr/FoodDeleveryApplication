package com.vilas.hungerHub.mapper;

import com.vilas.hungerHub.serviceInterface.MenuService;
import com.vilas.hungerHub.serviceInterface.RestaurantService;
import com.vilas.hungerHub.serviceInterface.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


//Helper class for object injection to mapper interface
@Component
@RequiredArgsConstructor
@Getter
public class EntityFetcher {

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final MenuService menuService;

}
