package productinventoryAPI;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/home")
	public  String welcome(){
		logger.debug("Inside Home");
		return ("<h1> Welcome </h1>")  ;
	}
	
	
	@GetMapping("/product/list")
	public ResponseEntity <List<Product>> getAllProduct(){
		return ResponseEntity.ok().body(productService.getAllProduct());
	}
	
	
	
	@GetMapping("/product/{id}") 
	public ResponseEntity <Product> getProductById(@PathVariable long id){
		return ResponseEntity.ok().body(productService.getProductById(id));
	}
	
	@GetMapping("product/priceRange/{min}/{max}")
	public ResponseEntity <List<Product>> searchByPriceRange(@PathVariable BigDecimal min, @PathVariable BigDecimal max){
		return ResponseEntity.ok().body(productService.searchByPriceRange(min, max));
	}
	
	@GetMapping("product/category/{category}")
	public ResponseEntity <List<Product>> searchByCategory(@PathVariable ProductCategory category){
		return ResponseEntity.ok().body(productService.searchByCategory(category));
	}
	
	
	@PostMapping("/addProduct") 
	public ResponseEntity <Product> addProduct(@RequestBody Product product){
		logger.debug("Inside addProduct :" + product.getId() + product.getDescription() );
		return ResponseEntity.ok().body(this.productService.createProduct(product));
	}
	
	@PutMapping("/updateProduct/{id}")
    public ResponseEntity < Product > updateProduct(@PathVariable long id, @RequestBody Product product) {
		logger.debug("Inside updateProduct :" + product.getId() + product.getDescription() );
        product.setId(id);
        return ResponseEntity.ok().body(this.productService.updateProduct(product));
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable long id) {
    	logger.debug("Deleting the product with id :" +id );
        this.productService.deleteProduct(id);
        return HttpStatus.OK;
    }
    
    
	

}
