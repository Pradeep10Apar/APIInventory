package productinventoryAPI;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductInventoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindById_thenReturnProduct() {
        // given
        Product product = new Product();
        product.setId(3);
        product.setName("LG");
        product.getCategory().setProductCategory(ProductCategory.ELECTRONICS);
        entityManager.persist(product);
        entityManager.flush();

        // when
        Optional<Product> found = productRepository.findById((long) 3);

        // then
        Assert.assertTrue(found.get().getName().equalsIgnoreCase(product.getName()));
         
    }

}