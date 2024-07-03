package com.sunbeam.service;

import java.util.List;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.entities.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	Category addNewCategory(Category category);
	Category getCategoryDetails(Long categoryId);
	ApiResponse updateCategoryDetails(Long categoryId,Category existingCategory);
	ApiResponse deleteCategoryDetails(Long categoryId);
}
