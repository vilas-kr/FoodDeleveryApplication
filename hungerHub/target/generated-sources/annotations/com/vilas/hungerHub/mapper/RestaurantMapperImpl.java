package com.vilas.hungerHub.mapper;

import com.vilas.hungerHub.dto.RestaurantDTO;
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
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public RestaurantDTO toDto(Restaurant rest) {
        if ( rest == null ) {
            return null;
        }

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setId( rest.getRestaurantId() );
        List<String> list = rest.getCuisineType();
        if ( list != null ) {
            restaurantDTO.setCuisines( new ArrayList<String>( list ) );
        }
        restaurantDTO.setCookingTime( rest.getDeliveryTime() );
        restaurantDTO.setIsOpen( rest.isActive() );
        restaurantDTO.setAdmin( restAdminUserId( rest ) );
        restaurantDTO.setName( rest.getName() );
        restaurantDTO.setAddress( rest.getAddress() );
        restaurantDTO.setRating( rest.getRating() );

        restaurantDTO.setImage( null );

        return restaurantDTO;
    }

    @Override
    public Restaurant toEntity(RestaurantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantId( dto.getId() );
        List<String> list = dto.getCuisines();
        if ( list != null ) {
            restaurant.setCuisineType( new ArrayList<String>( list ) );
        }
        restaurant.setDeliveryTime( dto.getCookingTime() );
        if ( dto.getIsOpen() != null ) {
            restaurant.setActive( dto.getIsOpen() );
        }
        restaurant.setName( dto.getName() );
        restaurant.setAddress( dto.getAddress() );
        restaurant.setRating( dto.getRating() );
        restaurant.setImage( dto.getImage() );

        restaurant.setAdmin( null );

        return restaurant;
    }

    private String restAdminUserId(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }
        User admin = restaurant.getAdmin();
        if ( admin == null ) {
            return null;
        }
        String userId = admin.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
