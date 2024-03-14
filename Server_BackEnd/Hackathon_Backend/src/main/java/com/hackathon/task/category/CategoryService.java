package com.hackathon.task.category;

import java.util.List;
public interface CategoryService {
	 CategoryEntity createCategory(CategoryEntity category);
	 CategoryEntity updateCategory(CategoryEntity category, Integer userId);
	 CategoryEntity getCategoryById(Integer CategoryId);
	 List<CategoryEntity> getAllCategory();
	 void deleteCategory(Integer categoryId);
}
