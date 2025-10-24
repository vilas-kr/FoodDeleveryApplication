package com.vilas.hungerHub.serviceInterface;

import com.vilas.hungerHub.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User updateLoginDate(User user);

    Object login(String username, String password);

    Optional<User> getUser(User User);

    Optional<User> getUserById(User user);

    User updateAllDetails(User oldUser, User newUser);

    User updateDetails(User existUser, User updateUser);

    User deleteUser(User user);

    User getUser(String userId);
}