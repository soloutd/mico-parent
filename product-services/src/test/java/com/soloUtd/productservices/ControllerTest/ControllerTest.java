package com.soloUtd.productservices.ControllerTest;


import com.soloUtd.productservices.Dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.6");

    @Autowired
    private MockMvc mockMvc;
    
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.Url", mongoDBContainer::getReplicaSetUrl);
    }

   // @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = createProductRequest();
        String json = productRequest.toString();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.
                        post("/pro/save").
                        contentType(MediaType.APPLICATION_JSON).
                        content(json)).andExpect(status().isCreated());
    }

    private ProductRequest createProductRequest() {
        return ProductRequest.builder().name("TEST NAME").description("TEST DESCRIPTION").price(100).build();
    }
}
