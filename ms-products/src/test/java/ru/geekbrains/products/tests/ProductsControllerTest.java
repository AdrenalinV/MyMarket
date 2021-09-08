package ru.geekbrains.products.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllProducts() throws Exception {
        mvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].title", is("Product_1")));
    }

    @Test
    public void getIdProduct() throws Exception {
        mvc.perform(get("/api/v1/products/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Product_2")));
    }

    @Test
    public void getProductDtosidIn() throws Exception {
        mvc.perform(get("/api/v1/products/ids")
                        .param("ids", "1")
                        .param("ids", "4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title", is("Product_4")));
    }

    @Test
    public void updateProduct() throws Exception {
        mvc.perform(put("/api/v1/products")
                        .content("{ \"id\": 3,\"title\":\"Product_update\",\"cost\":999.99}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Product_update")));
    }

    @Test
    public void deleteProductById() throws Exception {
        mvc.perform(delete("/api/v1/products/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addProduct() throws Exception {
        mvc.perform(post("/api/v1/products")
                .content("{\"title\":\"Product_add\",\"cost\":9999.99}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title",is("Product_add")));

    }
}
