package com.hackathon.task.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.task.globalexceptions.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	

	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public CategoryEntity createCategory(CategoryEntity Category) {
		CategoryEntity categoryEntity = this.categoryRepo.save(Category);
		return categoryEntity;
	}

	@Override
	public CategoryEntity updateCategory(CategoryEntity Category, Integer categoryId) {
		CategoryEntity category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		category.setName(Category.getName());
		category.setDescription(Category.getDescription());
		;
		CategoryEntity updatedCategory = this.categoryRepo.save(category);

		return updatedCategory;
	}

	@Override
	public CategoryEntity getCategoryById(Integer CategoryId) {
		CategoryEntity categoryEntity = this.categoryRepo.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", CategoryId));
		
		return categoryEntity;
	}

	@Override
	public List<CategoryEntity> getAllCategory() {
		List<CategoryEntity> allCategory = this.categoryRepo.findAll();
		return allCategory;
	}

	@Override
	public void deleteCategory(Integer CategoryId) {
		CategoryEntity categoryEntity = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", CategoryId));
		this.categoryRepo.delete(categoryEntity);

	}

}