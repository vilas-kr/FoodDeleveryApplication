package com.vilas.hungerHub.mapper;

import com.vilas.hungerHub.dto.RestaurantDTO;
import com.vilas.hungerHub.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(source = "restaurantId", target = "id")
    @Mapping(source = "cuisineType", target = "cuisines")
    @Mapping(source = "active", target = "isOpen")
    @Mapping(source = "cookingTime", target = "cookingTime")
    @Mapping(source = "admin.userId", target = "admin")
    @Mapping(source = "image", target = "image")
    RestaurantDTO toDto(Restaurant rest);

    @Mapping(source = "id", target = "restaurantId")
    @Mapping(source = "cuisines", target = "cuisineType")
    @Mapping(source = "isOpen", target = "active")
    @Mapping(source = "cookingTime", target = "cookingTime")
    @Mapping(target = "admin", expression = "java(null)")
    @Mapping(source = "image", target = "image")
    Restaurant toEntity(RestaurantDTO dto);

}
