package com.vilas.hungerHub.service;

import com.vilas.hungerHub.dto.RestaurantDTO;
import com.vilas.hungerHub.entity.IdSequence;
import com.vilas.hungerHub.entity.Restaurant;
import com.vilas.hungerHub.entity.User;
import com.vilas.hungerHub.exception.RestaurantNotFoundException;
import com.vilas.hungerHub.exception.UserNotFoundException;
import com.vilas.hungerHub.mapper.RestaurantMapper;
import com.vilas.hungerHub.repository.IdSequenceRepository;
import com.vilas.hungerHub.repository.RestaurantRepository;
import com.vilas.hungerHub.repository.UserRepository;
import com.vilas.hungerHub.serviceInterface.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private final RestaurantRepository restRepo;

    @Autowired
    private final UserRepository userRepo;

    @Autowired
    private final IdSequenceRepository idRepo;

    @Autowired
    private final RestaurantMapper mapper;

    @Transactional
    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO dto) {

        Restaurant rest = mapper.toEntity(dto);
        //fetch specified user object
        if(dto.getAdmin() != null) {
            User user = userRepo.findById(dto.getAdmin()).orElseThrow(() -> new UserNotFoundException("Admin user id not exist"));
            rest.setAdmin(user);
        }

        IdSequence idSequence = idRepo.findBySeqNameForUpdate("RESTAURANT_seq");
        String prefix = "REST";
        int nextVal = idSequence.getNextVal();

        idSequence.setNextVal(nextVal + 1);
        idRepo.save(idSequence);

        rest.setRestaurantId(prefix + String.format("%05d", nextVal));
        return mapper.toDto(restRepo.save(rest));

    }

    @Override
    public RestaurantDTO getRestaurant(RestaurantDTO dto) {

        if (dto.getId() != null) {
            Restaurant rest = restRepo.findById(dto.getId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));
            return mapper.toDto(rest);
        } else if (dto.getAdmin() != null) {
            Restaurant rest = restRepo.findByAdmin_UserId(dto.getAdmin()).orElseThrow(() -> new UserNotFoundException("Wrong admin Id"));
            return mapper.toDto(rest);
        } else {
            throw new RestaurantNotFoundException("Invalid information");
        }

    }

    @Override
    public RestaurantDTO updateStatus(RestaurantDTO dto) {

        Restaurant rest = restRepo.findById(dto.getId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));
        rest.setActive(dto.getIsOpen());

        return mapper.toDto(restRepo.save(rest));
    }

    @Override
    public RestaurantDTO updateDetails(RestaurantDTO dto) {

        Restaurant rest = restRepo.findById(dto.getId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        //update details
        if(dto.getAdmin() != null){
            User user = userRepo.findById(dto.getAdmin()).orElseThrow(() -> new UserNotFoundException("Wrong admin id"));
            rest.setAdmin(user);
        }

        if(dto.getName() != null)
            rest.setName(dto.getName());

        if(dto.getAddress() != null)
            rest.setAddress(dto.getAddress());

        if(dto.getCookingTime() != null)
            rest.setDeliveryTime(dto.getCookingTime());

        if(dto.getImage() != null)
            rest.setImage(dto.getImage());

        if(dto.getCuisines() != null)
            rest.setCuisineType(dto.getCuisines());

        if(dto.getRating() != 0.0)
            rest.setRating(dto.getRating());

        if(dto.getImage() != null)
            rest.setImage(dto.getImage());

        if(dto.getIsOpen() != null)
            rest.setActive(dto.getIsOpen());

        return mapper.toDto(restRepo.save(rest));

    }

    @Override
    public RestaurantDTO updateAllDetails(RestaurantDTO dto) {

        restRepo.findById(dto.getId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));
        Restaurant rest = mapper.toEntity(dto);

        if(dto.getAdmin() == null) {
            throw new UserNotFoundException("Wrong admin Id");
        }

        User user = userRepo.findById(dto.getAdmin()).orElseThrow(() -> new UserNotFoundException("Wrong admin Id"));
        rest.setAdmin(user);

        return mapper.toDto(restRepo.save(rest));

    }

    @Override
    public RestaurantDTO deleteRestaurant(RestaurantDTO dto) {

        Restaurant rest = restRepo.findById(dto.getId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));
        restRepo.delete(rest);
        return mapper.toDto(rest);

    }

    @Override
    public Restaurant getRestaurant(String restaurantId){
        return restRepo.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException("Invalid Restaurant Id"));
    }

}
