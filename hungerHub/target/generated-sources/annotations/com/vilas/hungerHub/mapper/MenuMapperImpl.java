package com.vilas.hungerHub.mapper;

import com.vilas.hungerHub.dto.MenuDTO;
import com.vilas.hungerHub.entity.Menu;
import com.vilas.hungerHub.entity.Restaurant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-24T12:43:36+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public MenuDTO toDto(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setId( menu.getMenuId() );
        menuDTO.setAvailable( menu.isAvailable() );
        menuDTO.setRestaurantId( menuRestaurantRestaurantId( menu ) );
        menuDTO.setName( menu.getName() );
        menuDTO.setDescription( menu.getDescription() );
        menuDTO.setPrice( menu.getPrice() );
        menuDTO.setCuisineType( menu.getCuisineType() );

        menuDTO.setImage( null );

        return menuDTO;
    }

    @Override
    public Menu toEntity(MenuDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setRestaurant( menuDTOToRestaurant( dto ) );
        menu.setMenuId( dto.getId() );
        if ( dto.getAvailable() != null ) {
            menu.setAvailable( dto.getAvailable() );
        }
        menu.setName( dto.getName() );
        menu.setDescription( dto.getDescription() );
        menu.setPrice( dto.getPrice() );
        menu.setCuisineType( dto.getCuisineType() );
        menu.setImage( dto.getImage() );

        return menu;
    }

    private String menuRestaurantRestaurantId(Menu menu) {
        if ( menu == null ) {
            return null;
        }
        Restaurant restaurant = menu.getRestaurant();
        if ( restaurant == null ) {
            return null;
        }
        String restaurantId = restaurant.getRestaurantId();
        if ( restaurantId == null ) {
            return null;
        }
        return restaurantId;
    }

    protected Restaurant menuDTOToRestaurant(MenuDTO menuDTO) {
        if ( menuDTO == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantId( menuDTO.getRestaurantId() );

        return restaurant;
    }
}
