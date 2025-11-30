package com.vilas.hungerHub.repository;

import com.vilas.hungerHub.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(nativeQuery = true,
    value = "SELECT restaurant_id " +
            "FROM food_order " +
            "GROUP BY restaurant_id " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT 20")
    List<String> findTopRestaurants();
}
