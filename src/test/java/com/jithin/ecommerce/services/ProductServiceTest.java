package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.Product;
import com.jithin.ecommerce.model.ProductColor;
import com.jithin.ecommerce.model.ProductSizes;
import com.jithin.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    public static final String RANDOMID = "randomid";
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService SUT;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(mockProductList());
        List<Product> result = SUT.getAll();
        assertEquals(result.size(), 2);
        verify(repository, times(1)).findAll();
        assertEquals(result.get(0).getColors().size(), 2);
        assertEquals(result.get(0).getSizes().size(), 2);
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
    void create() {
        when(repository.save(mockProductList().get(0))).thenReturn(mockProductList().get(0));

        Product result = SUT.create(mockProductList().get(0));
        ArgumentCaptor<Product> ac = ArgumentCaptor.forClass(Product.class);
        verify(repository, times(1)).save(ac.capture());
        assertEquals(result.getId(), ac.getValue().getId());

    }

    @Test
    void getById() {
        Optional<Product> product = Optional.of(mockProductList().get(0));

        when(repository.findById(RANDOMID+0)).thenReturn(product);
        Optional<Product> result = SUT.getById(RANDOMID + 0);
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);

        verify(repository, times(1)).findById(ac.capture());
        assertEquals(result.get().getId(), ac.getValue());

    }
}







