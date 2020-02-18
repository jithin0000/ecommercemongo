package com.jithin.ecommerce.repository;

import com.jithin.ecommerce.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
