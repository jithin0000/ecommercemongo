package com.jithin.ecommerce.repository;

import com.jithin.ecommerce.model.EOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<EOrder, String> {

}
