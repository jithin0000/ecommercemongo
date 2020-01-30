package com.jithin.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    public static final ObjectMapper om = new ObjectMapper();

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCategories() throws Exception {
        when(categoryService.getAll()).thenReturn(mockCategorySuccess());

        mockMvc.perform(get("/api/category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(categoryService, times(1)).getAll();


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

    @Test
    void create() throws Exception {

        Category body = mockCategorySuccess().get(0);
        when(categoryService.create(body)).thenReturn(body);

        mockMvc.perform(post("/api/category")
                .content(om.writeValueAsString(body))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(categoryService, times(1)).create(body);

    }

    @Test
    void createFailureInvalidRequestBody() throws Exception {


        Category body = new Category();
        mockMvc.perform(post("/api/category")
                .content(om.writeValueAsString(body))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.name", containsString("name")));
        verify(categoryService, times(0)).create(body);
    }

    // FIXME: 30-01-2020 need to add unique constraint test


}






