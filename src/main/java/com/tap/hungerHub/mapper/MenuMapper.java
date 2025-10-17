package com.tap.hungerHub.mapper;

import com.tap.hungerHub.dto.MenuDTO;
import com.tap.hungerHub.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(source = "menuId", target = "id")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "restaurant.restaurantId", target = "restaurantId")
    @Mapping(target = "image", expression = "java(null)")
    MenuDTO toDto(Menu menu);

    @Mapping(source = "id", target = "menuId")
    @Mapping(source = "restaurantId", target = "restaurant.restaurantId")
    @Mapping(source = "available", target = "available")
    Menu toEntity(MenuDTO dto);

}
