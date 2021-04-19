package productinventoryAPI;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes={ProductInventoryApplication.class, 
		ProductInventorySecurityConfiguration.class})
public class ProductRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    
    @Test
    public void givenProducts_whenGetProducts_thenReturnJsonArray()
      throws Exception {
        
        Product product = new Product();
        product.setName("LG");
        List<Product> products = Arrays.asList(product);
        //given
        given(service.getAllProduct()).willReturn(products);
        

        mvc.perform(get("/product/list")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].name").value("LG"));
    }
}
