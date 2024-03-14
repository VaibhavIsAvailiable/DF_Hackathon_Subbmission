package com.hackathon.task.product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.hackathon.task.category.CategoryEntity;
import com.hackathon.task.category.CategoryRepo;
import com.hackathon.task.globalexceptions.ResourceNotFoundException;

public class ProductServiceImpl implements ProductService {

	@Autowired
	 private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public ProductEntity createPost(ProductEntity product, Integer userId, Integer categoryId) {
		CategoryEntity category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		product.setCategory(category);
		ProductEntity productEntity = this.productRepo.save(product);
		return productEntity;
	}

	@Override
	public void deletePost(Integer Id) {
		ProductEntity categoryEntity = this.productRepo.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", Id));
		this.productRepo.delete(categoryEntity);
	}

	@Override
	public ProductEntity getPostById(Integer id) {
		ProductEntity productEntity = this.productRepo.findById(id).orElseThrow();
		
		return productEntity;
		
	}


	@Override
	public List<ProductEntity> searchPost(String keyword) {
		
		return null;
	}

	@Override
	public ProductEntity updatePost(ProductEntity postDTO, Integer Id) {
		ProductEntity productEntity = this.productRepo.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", Id));
		productEntity.setName(postDTO.getName());
		productEntity.setPackSize(postDTO.getPackSize());
		productEntity.setPrice(postDTO.getPrice());

		return this.productRepo.save(productEntity);
	}

	@Override
	public List<ProductEntity> getAllPost() {
		List<ProductEntity> allCategory = this.productRepo.findAll();
		return allCategory;
	}

	

}
