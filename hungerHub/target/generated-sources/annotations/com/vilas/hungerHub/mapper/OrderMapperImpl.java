package com.vilas.hungerHub.mapper;

import com.vilas.hungerHub.dto.OrderDTO;
import com.vilas.hungerHub.dto.OrderItemDTO;
import com.vilas.hungerHub.entity.Menu;
import com.vilas.hungerHub.entity.Order;
import com.vilas.hungerHub.entity.OrderItem;
import com.vilas.hungerHub.entity.Restaurant;
import com.vilas.hungerHub.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-24T12:43:36+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getOrderId() );
        orderDTO.setUserId( orderUserUserId( order ) );
        orderDTO.setRestaurantId( orderRestaurantRestaurantId( order ) );
        orderDTO.setOrderItems( toItemDTOList( order.getOrderItems() ) );
        orderDTO.setTotalAmount( order.getTotalAmount() );
        orderDTO.setStatus( order.getStatus() );
        orderDTO.setOrderDate( order.getOrderDate() );
        orderDTO.setPaymentType( order.getPaymentType() );

        return orderDTO;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orders.size() );
        for ( Order order : orders ) {
            list.add( toDTO( order ) );
        }

        return list;
    }

    @Override
    public Order toEntity(OrderDTO dto, EntityFetcher context) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderId( dto.getId() );
        order.setOrderItems( toItemEntityList( dto.getOrderItems(), context ) );
        order.setOrderDate( dto.getOrderDate() );
        order.setTotalAmount( dto.getTotalAmount() );
        order.setStatus( dto.getStatus() );
        order.setPaymentType( dto.getPaymentType() );

        order.setUser( fetchUser(dto.getUserId(), context) );
        order.setRestaurant( fetchRestaurant(dto.getRestaurantId(), context) );

        return order;
    }

    @Override
    public List<Order> toEntityList(List<OrderDTO> orderDTOs, EntityFetcher context) {
        if ( orderDTOs == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orderDTOs.size() );
        for ( OrderDTO orderDTO : orderDTOs ) {
            list.add( toEntity( orderDTO, context ) );
        }

        return list;
    }

    @Override
    public OrderItemDTO toItemDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setMenuId( orderItemMenuMenuId( orderItem ) );
        orderItemDTO.setOrderId( orderItemOrderOrderId( orderItem ) );

        orderItemDTO.setQuantity( String.valueOf(orderItem.getQuantity()) );
        orderItemDTO.setPrice( orderItem.getMenu().getPrice() );

        return orderItemDTO;
    }

    @Override
    public List<OrderItemDTO> toItemDTOList(List<OrderItem> orderItems) {
        if ( orderItems == null ) {
            return null;
        }

        List<OrderItemDTO> list = new ArrayList<OrderItemDTO>( orderItems.size() );
        for ( OrderItem orderItem : orderItems ) {
            list.add( toItemDTO( orderItem ) );
        }

        return list;
    }

    @Override
    public OrderItem toItemEntity(OrderItemDTO dto, EntityFetcher context) {
        if ( dto == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setMenu( fetchMenu(dto.getMenuId(), context) );
        orderItem.setQuantity( Integer.parseInt(dto.getQuantity()) );

        return orderItem;
    }

    @Override
    public List<OrderItem> toItemEntityList(List<OrderItemDTO> itemDTOs, EntityFetcher context) {
        if ( itemDTOs == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( itemDTOs.size() );
        for ( OrderItemDTO orderItemDTO : itemDTOs ) {
            list.add( toItemEntity( orderItemDTO, context ) );
        }

        return list;
    }

    private String orderUserUserId(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        String userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String orderRestaurantRestaurantId(Order order) {
        if ( order == null ) {
            return null;
        }
        Restaurant restaurant = order.getRestaurant();
        if ( restaurant == null ) {
            return null;
        }
        String restaurantId = restaurant.getRestaurantId();
        if ( restaurantId == null ) {
            return null;
        }
        return restaurantId;
    }

    private String orderItemMenuMenuId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Menu menu = orderItem.getMenu();
        if ( menu == null ) {
            return null;
        }
        String menuId = menu.getMenuId();
        if ( menuId == null ) {
            return null;
        }
        return menuId;
    }

    private String orderItemOrderOrderId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Order order = orderItem.getOrder();
        if ( order == null ) {
            return null;
        }
        String orderId = order.getOrderId();
        if ( orderId == null ) {
            return null;
        }
        return orderId;
    }
}
