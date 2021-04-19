package productinventoryAPI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="categories")
public class Category {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private ProductCategory productCategory;
	


    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public ProductCategory getProductCategory() {
		return productCategory;
	}


	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}



	
	
	
	
	
}
