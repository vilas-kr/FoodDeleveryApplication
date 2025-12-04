package com.vilas.hungerHub.controllers;

import com.vilas.hungerHub.dto.FileResponse;
import com.vilas.hungerHub.dto.RestaurantDTO;
import com.vilas.hungerHub.serviceInterface.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    @Autowired
    private final RestaurantService restService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {

        try {
            FileResponse fileName = restService.saveImage(file);
            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable("fileName") String fileName) {

        try {
            byte[] imageData = restService.getImageByName(fileName);

            String contentType = restService.getContentType(fileName);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(imageData);

        } catch (Exception e) {
            return ResponseEntity.status(404).body("Image not found: " + fileName);
        }
    }

    @PostMapping
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO dto) {
        return restService.createRestaurant(dto);
    }

    @GetMapping
    public RestaurantDTO getRestaurant(@RequestBody RestaurantDTO dto){
        return restService.getRestaurant(dto);
    }

    @GetMapping("/popular")
    public List<RestaurantDTO> getPopularRestaurant(){
        return restService.getPopularRestaurant();
    }

    @PostMapping("/status")
    public RestaurantDTO updateStatus(@RequestBody RestaurantDTO dto){
        return restService.updateStatus(dto);
    }

    @PatchMapping
    public RestaurantDTO updateDetails(@RequestBody RestaurantDTO dto){
        return restService.updateDetails(dto);
    }

    @PutMapping
    public RestaurantDTO updateAllDetails(@RequestBody RestaurantDTO dto){
        return restService.updateAllDetails(dto);
    }

    @DeleteMapping
    public RestaurantDTO removeRestaurant(@RequestBody RestaurantDTO dto){
        return  restService.deleteRestaurant(dto);
    }

}
