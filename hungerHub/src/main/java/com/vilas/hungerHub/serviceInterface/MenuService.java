package com.vilas.hungerHub.serviceInterface;

import com.vilas.hungerHub.dto.MenuDTO;
import com.vilas.hungerHub.entity.Menu;

public interface MenuService {
    MenuDTO createMenu(MenuDTO dto);

    Menu findMenu(String menuId);

    MenuDTO updateAvailable(MenuDTO menuDTO);

    MenuDTO updateDetails(MenuDTO menuDTO);

    MenuDTO updateAllDetails(MenuDTO menuDTO);

    MenuDTO getMenu(String menuId);

    MenuDTO deleteMenu(String menuId);

}
