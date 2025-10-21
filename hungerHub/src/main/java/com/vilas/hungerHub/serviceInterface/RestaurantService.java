package com.vilas.hungerHub.serviceInterface;

import com.vilas.hungerHub.dto.RestaurantDTO;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO dto);

    RestaurantDTO getRestaurant(RestaurantDTO dto);

    RestaurantDTO updateStatus(RestaurantDTO dto);

    RestaurantDTO updateDetails(RestaurantDTO dto);

    RestaurantDTO updateAllDetails(RestaurantDTO dto);

    RestaurantDTO deleteRestaurant(RestaurantDTO dto);

}
