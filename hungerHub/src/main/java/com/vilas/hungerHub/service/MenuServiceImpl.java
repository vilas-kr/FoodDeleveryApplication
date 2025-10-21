package com.vilas.hungerHub.service;

import com.vilas.hungerHub.dto.MenuDTO;
import com.vilas.hungerHub.entity.IdSequence;
import com.vilas.hungerHub.entity.Menu;
import com.vilas.hungerHub.entity.Restaurant;
import com.vilas.hungerHub.exception.MenuNotFoundException;
import com.vilas.hungerHub.exception.RestaurantNotFoundException;
import com.vilas.hungerHub.mapper.MenuMapper;
import com.vilas.hungerHub.repository.IdSequenceRepository;
import com.vilas.hungerHub.repository.MenuRepository;
import com.vilas.hungerHub.repository.RestaurantRepository;
import com.vilas.hungerHub.serviceInterface.MenuService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    @Autowired
    private final MenuRepository menuRepository;

    @Autowired
    private final MenuMapper menuMapper;

    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Autowired
    private final IdSequenceRepository idSequenceRepository;

    @Override
    @Transactional
    public MenuDTO createMenu(MenuDTO dto){

        if(dto.getAvailable() == null) //if Boolean value is null then it throws NullPointerException during auto unboxing
            throw new RuntimeException("initialize menu availability");

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException("Enter Valid Restaurant Id"));
        Menu menu = menuMapper.toEntity(dto); //convert DTO to entity

        IdSequence idSequence = idSequenceRepository.findBySeqNameForUpdate("MENU_seq");
        int nextVal = idSequence.getNextVal();
        idSequence.setNextVal(nextVal + 1);
        idSequenceRepository.save(idSequence);

        String prefix = "MENU";
        menu.setMenuId(prefix + String.format("%05d", nextVal));
        menu.setRestaurant(restaurant);

        menu = menuRepository.save(menu);
        return menuMapper.toDto(menu);
    }

    public Menu findMenu(String menuId){
        return menuRepository.findById(menuId).orElseThrow(() -> new MenuNotFoundException("Provide Valid Menu ID"));
    }

    @Override
    public MenuDTO updateAvailable(MenuDTO menuDTO){
        Menu menu = findMenu(menuDTO.getId());
        if(menuDTO.getAvailable() == null)
            throw new RuntimeException("Provide Menu Availability");
        menu.setAvailable(menuDTO.getAvailable());
        menu = menuRepository.save(menu);
        return menuMapper.toDto(menu);
    }

    @Override
    public MenuDTO updateDetails(MenuDTO menuDTO){
        Menu menu = findMenu(menuDTO.getId());
        if(menuDTO.getName() != null)
            menu.setName(menuDTO.getName());
        if(menuDTO.getDescription() != null)
            menu.setDescription(menuDTO.getDescription());
        if(menuDTO.getPrice() != 0.0f)
            menu.setPrice(menuDTO.getPrice());
        if(menuDTO.getAvailable() != null)
            menu.setAvailable(menuDTO.getAvailable());
        if(menuDTO.getCuisineType() != null)
            menu.setCuisineType(menuDTO.getCuisineType());
        if(menuDTO.getImage() != null)
            menu.setImage(menuDTO.getImage());
        if(menuDTO.getRestaurantId() != null){
            Restaurant restaurant = restaurantRepository.findById(menuDTO.getRestaurantId())
                    .orElseThrow(() -> new RestaurantNotFoundException("Provide Valid Restaurant ID"));
            menu.setRestaurant(restaurant);
        }
        menu = menuRepository.save(menu);
        return menuMapper.toDto(menu);
    }

    @Override
    public MenuDTO updateAllDetails(MenuDTO menuDTO){
        Menu menu = findMenu(menuDTO.getId());
        Restaurant restaurant = restaurantRepository.findById(menuDTO.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException("Provide Valid Restaurant"));
        if(menuDTO.getAvailable() == null)
            throw new RuntimeException("Provide Menu Availability");
        menu = menuMapper.toEntity(menuDTO);
        menu.setRestaurant(restaurant);
        menu = menuRepository.save(menu);
        return menuMapper.toDto(menu);
    }

    @Override
    public MenuDTO getMenu(String menuId){
        Menu menu = findMenu(menuId);
        MenuDTO menuDTO = menuMapper.toDto(menu);
        menuDTO.setImage(menu.getImage());
        return menuDTO;
    }

    @Override
    public MenuDTO deleteMenu(String menuId){
        Menu menu = findMenu(menuId);
        menuRepository.delete(menu);
        return menuMapper.toDto(menu);
    }

}
