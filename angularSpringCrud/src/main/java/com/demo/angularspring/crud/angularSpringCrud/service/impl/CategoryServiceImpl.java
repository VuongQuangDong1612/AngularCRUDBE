package com.demo.angularspring.crud.angularSpringCrud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.angularspring.crud.angularSpringCrud.entity.CategoryEntity;
import com.demo.angularspring.crud.angularSpringCrud.repository.CategoryRepository;
import com.demo.angularspring.crud.angularSpringCrud.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryEntity> getListCategories() {
		List<CategoryEntity> listCategories = (List<CategoryEntity>)categoryRepository.findAll();
		return listCategories;
	}

	@Override
	public Optional<CategoryEntity> getCategoryById(int id) { 
		return categoryRepository.findById(id);
	}

	@Override
	public void save(CategoryEntity categoryEntity) {
		categoryRepository.save(categoryEntity);
	}

	@Override
	public void delete(CategoryEntity categoryEntity) {
		categoryRepository.delete(categoryEntity);
	}

}
