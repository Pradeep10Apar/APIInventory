package productinventoryAPI;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
	
	Product createProduct(Product product);

    Product updateProduct(Product product);

    List < Product > getAllProduct();

    Product getProductById(long productId);

    void deleteProduct(long id);

    List < Product > searchByPriceRange(BigDecimal min, BigDecimal max);

	List < Product > searchByCategory(ProductCategory category);

	List<Product> createProduct(List<Product> product);

}
