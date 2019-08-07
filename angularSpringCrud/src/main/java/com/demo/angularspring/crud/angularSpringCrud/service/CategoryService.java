package com.demo.angularspring.crud.angularSpringCrud.service;

import java.util.List;
import java.util.Optional;

import com.demo.angularspring.crud.angularSpringCrud.entity.CategoryEntity;

public interface CategoryService {
	
	List<CategoryEntity> getListCategories();
	
	Optional<CategoryEntity> getCategoryById(int id);
	
	void save(CategoryEntity categoryEntity);
	
	void delete(CategoryEntity categoryEntity);
}
