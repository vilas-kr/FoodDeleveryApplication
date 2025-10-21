package com.vilas.hungerHub.entity;

import com.vilas.hungerHub.entity.Menu;
import com.vilas.hungerHub.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @Column(name = "restaurant_id", columnDefinition = "CHAR(9)")
    private String restaurantId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "rest_cuisine",
            joinColumns = @JoinColumn(name = "restaurant_id")
    )
    @Column(name = "cuisine_type")
    private List<String> cuisineType;

    @Column(name = "delivery_time", nullable = false)
    private LocalTime deliveryTime;

    @Column(name = "rating", nullable = false)
    @DecimalMax(value = "5.0", message = "Rating value should be below 5")
    @DecimalMin(value = "0.0", message = "Rating should be grater than 0")
    private float rating;

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "image", columnDefinition = "TEXT") // max size 64KB
    @Lob
    private String image;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_user_id")
    private User admin;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> menus;

}
