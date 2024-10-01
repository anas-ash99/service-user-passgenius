package com.passgenius.serviceuser.repository;


import com.passgenius.serviceuser.models.User2;
import com.passgenius.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User2, String> {
    Optional<User> findByUsername(String username);
}
