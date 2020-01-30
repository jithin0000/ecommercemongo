package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<T extends MongoRepository<E, String>, E> {

    private final T repository;

    public BaseService(T repository) {
        this.repository = repository;
    }

    public T getRepository() {
        return repository;
    }

    public List<E> getAll(){
        return repository.findAll();
    }

    public E create(E body) {
        return repository.save(body);
    }

    public Optional<E> getById(String Id) {
        return repository.findById(Id);
    }
}
