package com.vilas.hungerHub.service;

import com.vilas.hungerHub.dto.RatingDTO;
import com.vilas.hungerHub.entity.Rating;
import com.vilas.hungerHub.mapper.RatingEntityFetcher;
import com.vilas.hungerHub.mapper.RatingMapper;
import com.vilas.hungerHub.repository.RatingRepository;
import com.vilas.hungerHub.serviceInterface.RatingService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    @Autowired
    private final RatingRepository ratingRepository;

    @Autowired
    private final RatingEntityFetcher ratingContext;

    @Autowired
    private final RatingMapper mapper;

    @Override
    public RatingDTO uploadRating(RatingDTO dto){
        Rating rating = mapper.toEntity(dto, ratingContext);
        return mapper.toDto(ratingRepository.save(rating)) ;
    }

    @Override
    public List<RatingDTO> uploadRating(List<RatingDTO> dto){
        List<Rating> rating = mapper.toEntity(dto, ratingContext);
        return mapper.toDto(ratingRepository.saveAll(rating));
    }

}
