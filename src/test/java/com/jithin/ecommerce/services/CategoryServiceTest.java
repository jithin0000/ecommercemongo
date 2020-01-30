package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService SUT;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAll() {
        when(categoryRepository.findAll()).thenReturn(mockCategorySuccess());

        List<Category> result = SUT.getAll();
        assertEquals(result.size(), 2);

        verify(categoryRepository, times(1)).findAll();
    }

    private List<Category> mockCategorySuccess() {
        List<Category> categoryList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Category category = new Category();
            category.setId("RANDOMID"+i);
            category.setName("fist name " + i);
            categoryList.add(category);
        }

        return categoryList;
    }
}