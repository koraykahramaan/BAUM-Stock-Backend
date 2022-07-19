package tr.edu.anadolu.productlistapp.ProductControllerTest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    // Get the products in the database
    @Test
    public void shouldGetAllProductsFromTheDatabase_andReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Urun")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Add a product to the database
    @Test
    public void shouldAddProductToTheDatabase_andReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/Urun")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\":\"cay\",\"productPrice\":\"5\",\"productType\":\"ICECEK\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
