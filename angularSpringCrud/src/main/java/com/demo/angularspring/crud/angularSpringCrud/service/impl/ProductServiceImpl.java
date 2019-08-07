package com.demo.angularspring.crud.angularSpringCrud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.angularspring.crud.angularSpringCrud.entity.ProductEntity;
import com.demo.angularspring.crud.angularSpringCrud.repository.ProductRepository;
import com.demo.angularspring.crud.angularSpringCrud.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;


	@Override
	public Page<ProductEntity> getListProducts(Integer page,  Integer size, String sort) {
		Sort sortable = null;
		 if (sort.equals("ASC")) {
		      sortable = Sort.by("id").ascending();
		    }
	    if (sort.equals("DESC")) {
	      sortable = Sort.by("id").descending();
	    }  
	    Pageable pageable = PageRequest.of(page, size, sortable);
		Page<ProductEntity> listProducts = (Page<ProductEntity>) productRepository.findAll(pageable);
		return listProducts;
	}

	@Override
	public Optional<ProductEntity> getProductById(int id) {
		Optional<ProductEntity> productEntity =  productRepository.findById(id);
		return productEntity;
	}

	@Override
	public void saveProduct(ProductEntity productEntity) {
		if(productEntity.getId() == 0) {
			int maxId = productRepository.getMaxId();
			productEntity.setId(maxId + 1);
		}
		productRepository.save(productEntity);	
	}

	@Override
	public void removeProduct(ProductEntity productEntity) {
		productRepository.delete(productEntity);
	}

	@Override
	public List<ProductEntity> getProductByCategory(int id) {
		List<ProductEntity> listProducts = (List<ProductEntity>)productRepository.findByCategoryEntityId(id);
		return listProducts;
	}
}
