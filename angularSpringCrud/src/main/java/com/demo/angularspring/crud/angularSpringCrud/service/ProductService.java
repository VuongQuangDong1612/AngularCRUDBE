package com.demo.angularspring.crud.angularSpringCrud.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.demo.angularspring.crud.angularSpringCrud.entity.ProductEntity;


public interface ProductService {
	
	Page<ProductEntity> getListProducts(Integer page,  Integer size, String sort);
	
	Optional<ProductEntity> getProductById(int id);
	
	List<ProductEntity> getProductByCategory(int id);
	
	void saveProduct (ProductEntity productEntity);
	
	void removeProduct(ProductEntity productEntity);
	
	List<String> getImage (int id);
	
	void uploadSaveImage (MultipartFile[] files, int id);
	
	void removeManyProduct(int id);
}
