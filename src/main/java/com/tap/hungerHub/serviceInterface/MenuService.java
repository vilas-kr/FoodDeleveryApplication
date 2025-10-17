package com.tap.hungerHub.serviceInterface;

import com.tap.hungerHub.dto.MenuDTO;

public interface MenuService {
    MenuDTO createMenu(MenuDTO dto);

    MenuDTO updateAvailable(MenuDTO menuDTO);

    MenuDTO updateDetails(MenuDTO menuDTO);

    MenuDTO updateAllDetails(MenuDTO menuDTO);

    MenuDTO getMenu(String menuId);

    MenuDTO deleteMenu(String menuId);
}
