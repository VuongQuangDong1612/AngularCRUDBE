package com.demo.angularspring.crud.angularSpringCrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.angularspring.crud.angularSpringCrud.entity.ProductEntity;
import com.demo.angularspring.crud.angularSpringCrud.service.CategoryService;
import com.demo.angularspring.crud.angularSpringCrud.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductManagerController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/products/getListProducts")
	public ResponseEntity<Page<ProductEntity>> getListProducts(
			 @RequestParam(name ="page" , required = false, defaultValue ="0") Integer page,
			 @RequestParam(name ="size", required = false, defaultValue ="5") Integer size,
		     @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort){
		Page<ProductEntity> listProducts = productService.getListProducts(page, size, sort);
		if(listProducts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<>(listProducts, HttpStatus.OK);
	}
	
	@GetMapping("/products/getProduct/{id}")
	public ResponseEntity<ProductEntity> getProduct(@PathVariable("id") int id){
		Optional<ProductEntity> product = productService.getProductById(id);
		if(!product.isPresent()) {
			return new ResponseEntity<>(product.get(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product.get(),HttpStatus.OK);
	}
	
	@GetMapping("/products/getProductByCategory/{id}")
	public ResponseEntity<List<ProductEntity>> getProductByCategory(@PathVariable("id") int id){
		List<ProductEntity> listProducts = productService.getProductByCategory(id);
		if(listProducts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		return new ResponseEntity<>(listProducts, HttpStatus.OK);
	}
	
	@PostMapping("/products/saveProduct")
	public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity productEntity){
		if(productEntity.getId() == 0) {
			productService.saveProduct(productEntity);
			return  new ResponseEntity<>(productEntity, HttpStatus.CREATED);
		} else {
			Optional<ProductEntity> productData = productService.getProductById(productEntity.getId());
			if(productData.isPresent()) {
				productService.saveProduct(productEntity);
				return new ResponseEntity<>(productData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(productData.get(), HttpStatus.NOT_FOUND);
			}
		}
		
	}
	
	@DeleteMapping("/products/deleteProduct/{id}")
	public ResponseEntity<ProductEntity> deleteProduct(@PathVariable("id") int id){
		Optional<ProductEntity> product = productService.getProductById(id);
		ProductEntity deletedProductEntity = product.get();
		if(!product.isPresent()) {
			return new ResponseEntity<>(deletedProductEntity, HttpStatus.NOT_FOUND);
		}
		
		productService.removeProduct(deletedProductEntity);
		return new ResponseEntity<>(deletedProductEntity,HttpStatus.OK);
	}
	
}
