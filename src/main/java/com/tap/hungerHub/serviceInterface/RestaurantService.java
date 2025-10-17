package com.tap.hungerHub.serviceInterface;

import com.tap.hungerHub.dto.RestaurantDTO;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO dto);

    RestaurantDTO getRestaurant(RestaurantDTO dto);

    RestaurantDTO updateStatus(RestaurantDTO dto);

    RestaurantDTO updateDetails(RestaurantDTO dto);

    RestaurantDTO updateAllDetails(RestaurantDTO dto);

    RestaurantDTO deleteRestaurant(RestaurantDTO dto);

}
