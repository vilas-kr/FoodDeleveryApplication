package com.vilas.hungerHub.repository;

import com.vilas.hungerHub.entity.Rating;
import com.vilas.hungerHub.entity.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {

}
