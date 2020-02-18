package com.jithin.ecommerce.repository;

import com.jithin.ecommerce.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    User getById(String id);


}
