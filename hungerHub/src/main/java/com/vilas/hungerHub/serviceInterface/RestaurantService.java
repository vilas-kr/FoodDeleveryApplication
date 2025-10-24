package com.vilas.hungerHub.serviceInterface;

import com.vilas.hungerHub.dto.RestaurantDTO;
import com.vilas.hungerHub.entity.Restaurant;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO dto);

    RestaurantDTO getRestaurant(RestaurantDTO dto);

    RestaurantDTO updateStatus(RestaurantDTO dto);

    RestaurantDTO updateDetails(RestaurantDTO dto);

    RestaurantDTO updateAllDetails(RestaurantDTO dto);

    RestaurantDTO deleteRestaurant(RestaurantDTO dto);

    Restaurant getRestaurant(String restaurantId);
}
