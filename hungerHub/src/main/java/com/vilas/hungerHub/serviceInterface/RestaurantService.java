package com.vilas.hungerHub.serviceInterface;

import java.io.IOException;
import java.util.List;

import com.vilas.hungerHub.dto.FileResponse;
import com.vilas.hungerHub.dto.RestaurantDTO;
import com.vilas.hungerHub.entity.Restaurant;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO dto);

    RestaurantDTO getRestaurant(RestaurantDTO dto);

    RestaurantDTO updateStatus(RestaurantDTO dto);

    RestaurantDTO updateDetails(RestaurantDTO dto);

    RestaurantDTO updateAllDetails(RestaurantDTO dto);

    RestaurantDTO deleteRestaurant(RestaurantDTO dto);

    Restaurant getRestaurant(String restaurantId);

    List<RestaurantDTO> getPopularRestaurant();

    FileResponse saveImage(MultipartFile file) throws IOException;

    byte[] getImageByName(String fileName) throws IOException;

    String getContentType(String fileName);
}
