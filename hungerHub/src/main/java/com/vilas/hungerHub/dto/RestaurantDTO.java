package com.vilas.hungerHub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private String id;
    private String name;
    private String address;
    private List<String> cuisines;
    private Integer cookingTime;
    private float rating;
    private Boolean isOpen;
    private String image;
    private String admin;

}
