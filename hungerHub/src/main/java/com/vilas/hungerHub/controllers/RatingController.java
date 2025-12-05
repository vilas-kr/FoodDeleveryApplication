package com.vilas.hungerHub.controllers;

import com.vilas.hungerHub.dto.RatingDTO;
import com.vilas.hungerHub.serviceInterface.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rating")
@RequiredArgsConstructor
public class RatingController {

    @Autowired
    private final RatingService ratingService;

    @PostMapping
    public List<RatingDTO> uploadRating(@RequestBody List<RatingDTO> dto){
        return ratingService.uploadRating(dto);
    }

}
