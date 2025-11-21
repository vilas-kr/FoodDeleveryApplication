package com.vilas.hungerHub.controllers;

import com.vilas.hungerHub.entity.User;
import com.vilas.hungerHub.serviceInterface.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser =  userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<?> getUser(@RequestBody User reqUser){
        User user;

        //fetch user from database
        try {
            user = userService.getUser(reqUser).orElseThrow(() -> new RuntimeException("user not found"));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        if(allUsers.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyUser(@RequestBody Map<String, String> map){

        Object user = userService.login(map.get("userName"), map.get("password"));

        if(user instanceof User)
            return ResponseEntity.status(HttpStatus.OK).body(user);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", user));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User reqUser){

        User user;

        //fetch user from database
        try {
            user = userService.getUserById(reqUser).orElseThrow(() -> new RuntimeException("user not found"));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }

        //update user and return updated user details
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateAllDetails(user, reqUser));

    }

    @PatchMapping
    public ResponseEntity<?> patchUser(@RequestBody User reqUser){

        User user;

        //fetch user from database
        try {
            user = userService.getUserById(reqUser).orElseThrow(() -> new RuntimeException("user not found"));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }

        //update user and return updated user details
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateDetails(user, reqUser));

    }

    @DeleteMapping
    public ResponseEntity<?> removeUser(@RequestBody User reqUser){

        User user;

        //fetch if the user exist else return not found response
        try{
            user = userService.getUser(reqUser).orElseThrow(() -> new RuntimeException("User Not Found"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }

        //delete the user form the database
        user = userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}
