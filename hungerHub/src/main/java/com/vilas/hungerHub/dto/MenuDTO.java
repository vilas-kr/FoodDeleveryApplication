package com.vilas.hungerHub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private String restaurantId;
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String cuisineType;
    private String image;
    private Float rating;
    private Integer totalRatings;

}
