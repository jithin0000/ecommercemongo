package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.ProductColor;
import com.jithin.ecommerce.repository.ProductColorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductColorServiceTest {

    public static final String RANDOMID = "RANDOMID";
    @Mock
    private ProductColorRepository repository;

    @InjectMocks
    private ProductColorService SUT;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(mockColorList());

        List<ProductColor> result = SUT.getAll();

        assertEquals(result.size(), 2);
        verify(repository, times(1)).findAll();
    }

    private List<ProductColor> mockColorList() {

        List<ProductColor> colorList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ProductColor color = new ProductColor();
            color.setName("name " + i);
            color.setId(RANDOMID + i);
            colorList.add(color);
        }
        return colorList;
    }

    @Test
    void create() {
        ProductColor color = new ProductColor();
        color.setId(RANDOMID);
        color.setName("name");

        when(repository.save(color)).thenReturn(color);

        ProductColor result = SUT.create(color);
        assertEquals(result.getId(), RANDOMID);
        verify(repository, times(1)).save(color);

    }

    @Test
    void getById() {

        ProductColor c = new ProductColor();
        c.setName("name");
        c.setId(RANDOMID);

        Optional<ProductColor> color = Optional.of(c);
        when(repository.findById(RANDOMID)).thenReturn(color);
        Optional<ProductColor> result = SUT.getById(RANDOMID);

        assertEquals(result.get().getId(), RANDOMID);
        verify(repository, times(1)).findById(RANDOMID);
    }
}










