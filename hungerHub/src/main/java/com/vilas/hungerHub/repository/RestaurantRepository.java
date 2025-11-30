package com.vilas.hungerHub.repository;

import com.vilas.hungerHub.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    Optional<Restaurant> findByAdmin_UserId(String userId);

    @Query(value = "SELECT r.image FROM Restaurant r WHERE r.restaurantId  = :restId")
    String findImageById(String restId);
}
