package com.vilas.hungerHub.entity;

import com.vilas.hungerHub.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Menu {

    @Id
    @Column(name = "menu_id", columnDefinition = "CHAR(10)")
    private String menuId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "available")
    private boolean isAvailable;
    
    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "image", columnDefinition = "TEXT") // max size 64KB
    @Lob
    private String image;

}
