package com.vilas.hungerHub.service;

import com.vilas.hungerHub.entity.IdSequence;
import com.vilas.hungerHub.entity.User;
import com.vilas.hungerHub.exception.UserNotFoundException;
import com.vilas.hungerHub.repository.IdSequenceRepository;
import com.vilas.hungerHub.repository.UserRepository;
import com.vilas.hungerHub.serviceInterface.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final IdSequenceRepository sequenceRepo;

    @Autowired
    private final UserRepository userRepo;

    private static final Map<String, String> PREFIX_MAP = Map.of(
            "USER", "USER",
            "ADMIN", "ADMIN",
            "SUPERADMIN", "SUPR",
            "DELIVERYAGENT", "AGENT"
    );

    @Transactional
    @Override
    public User createUser(User user) {

        String prefix = PREFIX_MAP.get(user.getRole().name());
        String seqName = user.getRole().name() + "_seq";

        // Fetch sequence with row-level lock
        IdSequence sequence = sequenceRepo.findBySeqNameForUpdate(seqName);

        // Increment sequence
        int nextVal = sequence.getNextVal();
        sequence.setNextVal(nextVal + 1);
        sequenceRepo.save(sequence);

        // Set user ID
        String formatLength = "%0" + (9-prefix.length()) + "d";
        user.setUserId(prefix + String.format(formatLength, nextVal));

//        if(prefix.length() == 4)
//            user.setUserId(prefix + String.format("%05d", nextVal));
//        else if (prefix.length() == 5)
//            user.setUserId(prefix + String.format("%04d", nextVal));
//        else
//            user.setUserId(prefix + String.format("%04d", nextVal)); //dummy

        return userRepo.save(user);
    }


    @Override
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public User updateLoginDate(User user){

        //update login date
        user.setLastLoginDate(LocalDate.now());

        return userRepo.save(user);
    }

    @Override
    public Object login(String userName, String password) {

        User user;
        try{
            user = userRepo.findByUserName(userName)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!user.getPassword().equals(password))
                throw new RuntimeException("Invalid credentials");
        }
        catch (RuntimeException e){
            return e.getMessage();
        }

        // update last login date
        user.setLastLoginDate(LocalDate.now());
        userRepo.save(user);

        return user;
    }

    @Override
    public Optional<User> getUser(User user) {
        if(user.getUserId() != null)
            return userRepo.findById(user.getUserId());
        else if(user.getUserName() != null)
            return userRepo.findByUserName(user.getUserName());
        else if(user.getEmail() != null)
            return userRepo.findByEmail(user.getEmail());
        else
            return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(User user){
        if(user.getUserId() != null)
            return userRepo.findById(user.getUserId());
        else
            return Optional.empty();
    }

    @Transactional
    @Override
    public User updateAllDetails(User existUser, User updateUser){

        existUser.setUserName(updateUser.getUserName());
        existUser.setName(updateUser.getName());
        existUser.setAddress(updateUser.getAddress());
        existUser.setEmail(updateUser.getEmail());
        existUser.setPassword(updateUser.getPassword());
        existUser.setPhoneNumber(updateUser.getPhoneNumber());
        if( !existUser.getRole().name().equals(updateUser.getRole().name()) ){

            //if role is modified then new id generated
            userRepo.delete(existUser);
            userRepo.flush();

            existUser.setRole(updateUser.getRole());

            return createUser(existUser);
        }

        return userRepo.save(existUser);

    }

    @Transactional
    @Override
    public User updateDetails(User existUser, User updateUser){

        if(updateUser.getUserName() != null)
            existUser.setUserName(updateUser.getUserName());
        
        if(updateUser.getName() != null)
            existUser.setName(updateUser.getName());
        
        if(updateUser.getAddress() != null)
            existUser.setAddress(updateUser.getAddress());
        
        if(updateUser.getEmail() != null)
            existUser.setEmail(updateUser.getEmail());
        
        if(updateUser.getPassword() != null)
            existUser.setPassword(updateUser.getPassword());

        if (updateUser.getPhoneNumber() != null)
            existUser.setPhoneNumber(updateUser.getPhoneNumber());
        
        if(updateUser.getRole() != null){

            if( !existUser.getRole().name().equals(updateUser.getRole().name()) ){
                //if role is modified then new id generated
                userRepo.delete(existUser);
                userRepo.flush();

//                User user = new User();
//                user.setName(existUser.getName());
//                user.setUserName(existUser.getUserName());
//                user.setAddress(existUser.getAddress());
//                user.setEmail(existUser.getEmail());
//                user.setPassword(existUser.getPassword());
//                user.setRole(updateUser.getRole());
//                user.setPhoneNumber(existUser.getPhoneNumber());

                existUser.setRole(updateUser.getRole());

                return createUser(existUser);
            }
        }
        
        return userRepo.save(existUser);
    }

    @Override
    public User deleteUser(User user){
        userRepo.delete(user);
        return user;
    }

    @Override
    public User getUser(String userId){
        return userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("Invalid User Id"));
    }

}
