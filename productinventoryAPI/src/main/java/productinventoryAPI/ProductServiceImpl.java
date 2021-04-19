package productinventoryAPI;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		logger.debug("Creating the product with id :" +product.getId() + product.getDescription());
		productRepository.save(product);
		return product;
	}
	
	
	@Override
	public List<Product> createProduct(List <Product> products) {
		productRepository.saveAll(products);
		return products;
	}

	@Override
	public Product updateProduct(Product product)  {

		Optional<Product> productDb = productRepository.findById(product.getId());
		
		if(productDb.isPresent()){
			Product prod = productDb.get();
			prod.setId(product.getId());
			prod.setDescription(product.getDescription());
			prod.setName(product.getName());
			prod.setPrice(product.getPrice());
			prod.setCategory(product.getCategory());
			return prod;
		}else{
			logger.error("Error while updating the product with id :" +product.getId() + product.getDescription()+ "Error: Product Not Found");
			throw new ResourceNotFoundException("Record not found with id : " + product.getId()) ;
		}

	}

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long productId) {
		Optional<Product> productDb = productRepository.findById(productId);
		if(productDb.isPresent()){
			return productDb.get();
		}else{
			logger.error("Record not found while fetching by Product id :" + productId);
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}
	
	
	
	
	
	@Override
	public List<Product> searchByPriceRange(BigDecimal min, BigDecimal max ) {
		List<Product> list = productRepository.findAll();
		return list.stream().filter(prod-> prod.getPrice().compareTo(min)> 0 && prod.getPrice().compareTo(max)<0 ).collect(Collectors.toList());

	}
	
	@Override
	public List<Product> searchByCategory(ProductCategory category) {
		List<Product> list = productRepository.findAll();
		return list.stream().filter(prod -> category.equals(prod.getCategory().getProductCategory())).collect(Collectors.toList());

	}
	

	@Override
	public void deleteProduct(long id) {
		Optional < Product > productDb = this.productRepository.findById(id);

        if (productDb.isPresent()) {
        	logger.debug("Deleting the product, Product id :" + id);
            this.productRepository.delete(productDb.get());
        } else {
        	logger.error("Error ocuured while deleting the product, Product id :" + id + "not found");
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
		
	}

	

}
