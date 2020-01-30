package com.jithin.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jithin.ecommerce.model.Product;
import com.jithin.ecommerce.model.ProductColor;
import com.jithin.ecommerce.model.ProductSizes;
import com.jithin.ecommerce.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    public static final ObjectMapper om = new ObjectMapper();
    private static final String RANDOMID = "randomid";

    @MockBean
    private ProductService service;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllProducts() throws Exception {
        when(service.getAll()).thenReturn(mockProductList());

        mockMvc.perform(get("/api/product"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));
        verify(service, times(1)).getAll();
    }
    private List<Product> mockProductList() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Product product = new Product();
            product.setId(RANDOMID+i);
            product.setName("name");
            product.setDescription("any description");
            product.setPrice(89);
            product.setQuantity(45);
            List<ProductColor> colors = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                ProductColor color = new ProductColor();
                color.setId("colorid"+i);
                color.setName("name"+i);
                colors.add(color);
            }
            product.setColors(colors);
            List<ProductSizes> sizes = Arrays.asList(ProductSizes.MEDIUM,ProductSizes.LARGE);
            product.setSizes(sizes);
            productList.add(product);
        }
        return productList;
    }

    @Test
    void create() throws Exception {

        when(service.create(mockProductList().get(0))).thenReturn(mockProductList().get(0));
        mockMvc.perform(post("/api/product")
                .content(om.writeValueAsString(mockProductList().get(0)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(RANDOMID + 0)));
        verify(service, times(1)).create(mockProductList().get(0));

    }

    @Test
    void createInvalidBody() throws Exception {

        Product invalidProduct = new Product();

        when(service.create(invalidProduct)).thenReturn(invalidProduct);
        mockMvc.perform(post("/api/product")
                .content(om.writeValueAsString(invalidProduct))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", containsString("name is required")));
        verify(service, times(0)).create(invalidProduct);

    }
}











