package com.jithin.ecommerce.bootstrap;

import com.github.javafaker.Faker;
import com.jithin.ecommerce.controller.CategoryController;
import com.jithin.ecommerce.controller.ProductController;
import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.model.Product;
import com.jithin.ecommerce.model.ProductColor;
import com.jithin.ecommerce.model.Role;
import com.jithin.ecommerce.services.CategoryService;
import com.jithin.ecommerce.services.ProductColorService;
import com.jithin.ecommerce.services.ProductService;
import com.jithin.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BootStrap implements CommandLineRunner {
    @Autowired
    private CategoryService service;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductColorService productColorService;
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
//        generateCategory(faker);
//        generateProductColors(faker);
//        generateProduct(faker);
//        generateRole();
    }

    private void generateRole() {

        List<String> roleNames = new ArrayList<>();
        roleNames.add("ROLE_MEMBER");
        roleNames.add("ROLE_ADMIN");
        roleNames.add("ROLE_MANAGER");

        for (int i = 0; i < 3; i++) {
            Role role = new Role();
            role.setName(roleNames.get(i));
            roleService.create(role);
        }
    }

    private void generateProductColors(Faker faker) {

        for (int i = 0; i < 11; i++) {
            ProductColor productColor = new ProductColor();
            productColor.setName(faker.color().name());
            productColorService.create(productColor);
        }

    }

    private void generateProduct(Faker faker) {
        for (int i = 0; i < 50; i++) {
            Product product = new Product();
            product.setName(faker.commerce().productName());
            product.setDescription(faker.lorem().sentence(15));
            product.setPrice(faker.number().numberBetween(78, 987));
            product.setQuantity(faker.number().randomDigit());
            product.setCategory(service.getAll().get(faker.number().numberBetween(1,10)));
            product.setColors(Arrays.asList(productColorService.getAll().get(faker.number().numberBetween(1,11
            ))));

            for (int j = 0; j < 5; j++) {
                product.getImage_url().add(faker.internet().image());
            }

            productService.create(product);
        }
    }

    private List<Category> generateCategory(Faker faker) {

        List<Category> categorylist = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setName(faker.commerce().department() + i);
            Calendar previous_date = Calendar.getInstance();
            previous_date.set(Calendar.YEAR, 2019);
            previous_date.set(Calendar.MONTH, 5);
            previous_date.set(Calendar.DATE, 1);


            category.setCreated(faker.date().between(previous_date.getTime(), new Date()));
            service.create(category);

            categorylist.add(category);

        }
        return categorylist;
    }
}
