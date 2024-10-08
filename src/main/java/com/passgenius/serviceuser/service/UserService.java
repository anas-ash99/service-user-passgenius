package com.passgenius.serviceuser.service;

import com.passgenius.serviceuser.exceptions.UserNotFoundException;
import com.passgenius.serviceuser.models.User;
import com.passgenius.serviceuser.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    public User updateUser(User user) throws UserNotFoundException{
        var isPresent = userRepository.existsById(user.getId());
        if (!isPresent){
            throw new UserNotFoundException("User doesn't exist");
        }
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
