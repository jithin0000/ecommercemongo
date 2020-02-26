package com.jithin.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@Document
public class EOrder {

    @Id
    private String id;

    private String order_random_id;
    @NotNull(message = "user is required field")
    private User user;
    @NotNull(message = "cart is required field")
    private Cart cart;

    private double shipping_charge = 10.5;
    private double other_charges = 2.5;
    private double order_total = 0;

    public double getOrder_total() {
        return order_total + getShipping_charge() + getOther_charges();
    }
}
