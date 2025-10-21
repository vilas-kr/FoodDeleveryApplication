package com.vilas.hungerHub.mapper;

import com.vilas.hungerHub.dto.RestaurantDTO;
import com.vilas.hungerHub.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(source = "restaurantId", target = "id")
    @Mapping(source = "cuisineType", target = "cuisines")
    @Mapping(source = "deliveryTime", target = "cookingTime")
    @Mapping(source = "active", target = "isOpen")
    @Mapping(source = "admin.userId", target = "admin")
    @Mapping(target = "image", expression = "java(null)")
    RestaurantDTO toDto(Restaurant rest);

    @Mapping(source = "id", target = "restaurantId")
    @Mapping(source = "cuisines", target = "cuisineType")
    @Mapping(source = "cookingTime", target = "deliveryTime")
    @Mapping(source = "isOpen", target = "active")
    @Mapping(target = "admin", expression = "java(null)")
    Restaurant toEntity(RestaurantDTO dto);

}
