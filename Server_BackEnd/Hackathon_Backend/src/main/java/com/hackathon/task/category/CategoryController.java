package com.hackathon.task.category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.task.payload.ApiResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("digitalflack/api/Categories")
public class CategoryController {

	@Autowired
	CategoryService categoryServices;

	@GetMapping("/allCategories")
	public ResponseEntity<List<CategoryEntity>> getAllCategories() {

		return ResponseEntity.ok(this.categoryServices.getAllCategory());
	}

	@GetMapping("/getCategoryByID/{categoryId}")
	public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable Integer categoryId) {

		return ResponseEntity.ok(this.categoryServices.getCategoryById(categoryId));
	}

	@PostMapping("/register")
	public ResponseEntity<CategoryEntity> addNewCategory(@RequestBody CategoryEntity categoryDTO) {
		return ResponseEntity.ok(this.categoryServices.createCategory(categoryDTO));
	}

	@PutMapping("/updateCategoryById/{categoryId}")
	public ResponseEntity<CategoryEntity> updateCategory(@RequestBody CategoryEntity categoryDTO,
			@PathVariable Integer categoryId) {
		return new ResponseEntity<CategoryEntity>(this.categoryServices.updateCategory(categoryDTO, categoryId),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteById/{categoryId}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer categoryId) {
		this.categoryServices.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);

	}
}