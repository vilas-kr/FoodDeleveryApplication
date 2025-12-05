package com.vilas.hungerHub.serviceInterface;


import com.vilas.hungerHub.dto.RatingDTO;

import java.util.List;

public interface RatingService {
    RatingDTO uploadRating(RatingDTO dto);

    List<RatingDTO> uploadRating(List<RatingDTO> dto);
}
