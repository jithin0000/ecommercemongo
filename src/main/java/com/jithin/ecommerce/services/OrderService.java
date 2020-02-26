package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.EOrder;
import com.jithin.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseService<OrderRepository, EOrder> {
    public OrderService(OrderRepository repository) {
        super(repository);
    }

    public Object updateById(String id, EOrder body) {
        return null;
    }
}
