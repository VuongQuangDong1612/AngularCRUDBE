package com.demo.angularspring.crud.angularSpringCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.angularspring.crud.angularSpringCrud.entity.CategoryEntity;
import com.demo.angularspring.crud.angularSpringCrud.service.CategoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategoryManagerController {
	
	@Autowired
	private CategoryService categoryService; 
	
	@GetMapping("/categories/getListCategories")
	public ResponseEntity<List<CategoryEntity>> getListCategories(){
		List<CategoryEntity> listCategories = categoryService.getListCategories();
		if(listCategories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<>(listCategories, HttpStatus.OK);
	}
}
