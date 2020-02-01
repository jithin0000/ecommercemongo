package com.jithin.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jithin.ecommerce.model.ProductColor;
import com.jithin.ecommerce.services.ProductColorService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductColorControllerTest {

    private static final String RANDOMID = "RANDOMID";
    public static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductColorService colorService;

    @Test
    void getAllColors() throws Exception {
        when(colorService.getAll()).thenReturn(mockColorList());

        mockMvc.perform(get("/api/color"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(colorService, times(1)).getAll();
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
    void createColor() throws Exception {

        ProductColor body = mockColorList().get(0);
        when(colorService.create(body)).thenReturn(body);

        mockMvc.perform(post(("/api/color")).content(om.writeValueAsString(body))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(RANDOMID + 0)));
        verify(colorService, times(1)).create(body);
    }

}

