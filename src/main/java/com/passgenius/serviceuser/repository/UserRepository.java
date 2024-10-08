package com.passgenius.serviceuser.repository;




import com.passgenius.serviceuser.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

}
