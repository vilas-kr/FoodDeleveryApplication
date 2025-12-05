package com.vilas.hungerHub.mapper;

import com.vilas.hungerHub.dto.RatingDTO;
import com.vilas.hungerHub.entity.Menu;
import com.vilas.hungerHub.entity.Order;
import com.vilas.hungerHub.entity.Rating;
import com.vilas.hungerHub.entity.RatingId;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    //DTO -> Entity
    @Mapping(target = "id", expression = "java(createId(dto))")
    @Mapping(target = "order", expression = "java(fetchOrder(dto.getOrderId(), context))")
    @Mapping(target = "menu", expression = "java(fetchMenu(dto.getMenuId(), context))")
    @Mapping(source = "rating", target = "rating")
    Rating toEntity(RatingDTO dto, @Context RatingEntityFetcher context);

    List<Rating> toEntity(List<RatingDTO> dto, @Context RatingEntityFetcher context);

    //Entity -> DTO
    @Mapping(target = "orderId", expression = "java(rating.getOrder().getOrderId())")
    @Mapping(target = "menuId", expression = "java(rating.getMenu().getMenuId())")
    @Mapping(source = "rating", target = "rating")
    RatingDTO toDto(Rating rating);

    List<RatingDTO> toDto(List<Rating> rating);

    //Helper Methods for entity fetching
    default Order fetchOrder(String orderId, RatingEntityFetcher context){
        return context.getOrderService().getOrderById(orderId);
    }
    
    default Menu fetchMenu(String menuId, RatingEntityFetcher context){
        return context.getMenuService().findMenu(menuId);
    }

    // Create Composite Key
    default RatingId createId(RatingDTO dto) {
        return new RatingId(dto.getOrderId(), dto.getMenuId());
    }

}
