package com.vilas.hungerHub.mapper;

import com.vilas.hungerHub.dto.OrderDTO;
import com.vilas.hungerHub.dto.OrderItemDTO;
import com.vilas.hungerHub.entity.*;
import com.vilas.hungerHub.repository.*;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // === Entity -> DTO ===
    @Mapping(source = "orderId", target = "id")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "restaurant.restaurantId", target = "restaurantId")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderDTO toDTO(Order order);

    List<OrderDTO> toDTOList(List<Order> orders);

    // === DTO -> Entity ===
    @Mapping(source = "id", target = "orderId")
    @Mapping(target = "user", expression = "java(fetchUser(dto.getUserId(), context))")
    @Mapping(target = "restaurant", expression = "java(fetchRestaurant(dto.getRestaurantId(), context))")
    @Mapping(source = "orderItems", target = "orderItems")
    Order toEntity(OrderDTO dto, @Context EntityFetcher context);

    List<Order> toEntityList(List<OrderDTO> orderDTOs, @Context EntityFetcher context);

    // === OrderItem Mapping ===
    @Mapping(source = "menu.menuId", target = "menuId")
    @Mapping(target = "quantity", expression = "java(String.valueOf(orderItem.getQuantity()))")
    @Mapping(target = "price", expression = "java(orderItem.getMenu().getPrice())")
    @Mapping(source = "order.orderId", target = "orderId")
    OrderItemDTO toItemDTO(OrderItem orderItem);

    List<OrderItemDTO> toItemDTOList(List<OrderItem> orderItems);

    @Mapping(target = "menu", expression = "java(fetchMenu(dto.getMenuId(), context))")
    @Mapping(target = "quantity", expression = "java(Integer.parseInt(dto.getQuantity()))")
    @Mapping(target = "order", ignore = true) // will be set in @AfterMapping
    OrderItem toItemEntity(OrderItemDTO dto, @Context EntityFetcher context);

    List<OrderItem> toItemEntityList(List<OrderItemDTO> itemDTOs, @Context EntityFetcher context);

    // === Helper methods for entity fetching ===
    default User fetchUser(String userId, EntityFetcher context) {
        return context.getUserService().getUser(userId);
    }

    default Restaurant fetchRestaurant(String restaurantId, EntityFetcher context) {
        return context.getRestaurantService().getRestaurant(restaurantId);
    }

    default Menu fetchMenu(String menuId, EntityFetcher context) {
        return context.getMenuService().findMenu(menuId);
    }
}
