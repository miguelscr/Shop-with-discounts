package com.tcs.ProductService.controller;
import com.tcs.ProductService.models.ProductModel;
import com.tcs.ProductService.services.ProductService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

    private MockMvc mockMvc;
    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void whenInsertingCustomers_thenCustomersAreCreated() throws Exception {
        this.mockMvc.perform(post("/api/product/post"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPostProduct() throws Exception {
        String json = "{{\n" +
                "  \"name\":\"Hat\",\n" +
                "  \"unitPrice\":80\n" +
                "}}";

        //when(productService.saveProduct(product)).thenReturn(product);
        this.mockMvc.perform(get("/api/product/getAll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Hat")))
                .andExpect(jsonPath("$.unitPrice", Matchers.is(80)))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
}