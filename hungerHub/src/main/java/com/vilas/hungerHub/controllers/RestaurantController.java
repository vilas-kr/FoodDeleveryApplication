package com.vilas.hungerHub.controllers;

import com.vilas.hungerHub.dto.RestaurantDTO;
import com.vilas.hungerHub.serviceInterface.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    @Autowired
    private final RestaurantService restService;

    @PostMapping
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO dto) {
        return restService.createRestaurant(dto);
    }

    @GetMapping
    public RestaurantDTO getRestaurant(@RequestBody RestaurantDTO dto){
        return restService.getRestaurant(dto);
    }

    @GetMapping("/popular")
    public List<RestaurantDTO> getPopularRestaurant(){
        return restService.getPopularRestaurant();
    }

    @PostMapping("/status")
    public RestaurantDTO updateStatus(@RequestBody RestaurantDTO dto){
        return restService.updateStatus(dto);
    }

    @PatchMapping
    public RestaurantDTO updateDetails(@RequestBody RestaurantDTO dto){
        return restService.updateDetails(dto);
    }

    @PutMapping
    public RestaurantDTO updateAllDetails(@RequestBody RestaurantDTO dto){
        return restService.updateAllDetails(dto);
    }

    @DeleteMapping
    public RestaurantDTO removeRestaurant(@RequestBody RestaurantDTO dto){
        return  restService.deleteRestaurant(dto);
    }

    @GetMapping("/image/{id}")
    public String getRestaurantImage(@PathVariable("id") String restId){
        return restService.getImage(restId);
    }
}
