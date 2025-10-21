package com.vilas.hungerHub.controllers;

import com.vilas.hungerHub.dto.MenuDTO;
import com.vilas.hungerHub.serviceInterface.MenuService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private final MenuService menuService;

    @PostMapping
    public MenuDTO createMenu(@RequestBody MenuDTO menuDTO){
        return menuService.createMenu(menuDTO);
    }

    @GetMapping("/{id}")
    public MenuDTO findMenu(@PathVariable("id") String menuId){
        return menuService.getMenu(menuId);
    }

    @PutMapping
    public MenuDTO replace(@RequestBody MenuDTO menuDTO){
        return menuService.updateAllDetails(menuDTO);
    }

    @PatchMapping
    public MenuDTO update(@RequestBody MenuDTO menuDTO){
        return menuService.updateDetails(menuDTO);
    }

    @DeleteMapping("/{id}")
    public MenuDTO delete(@PathVariable("id") String menuId){
        return menuService.deleteMenu(menuId);
    }


}
