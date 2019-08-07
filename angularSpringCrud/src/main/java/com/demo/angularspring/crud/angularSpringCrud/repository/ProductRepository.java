package com.demo.angularspring.crud.angularSpringCrud.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.angularspring.crud.angularSpringCrud.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
	Page<ProductEntity> findAll(Pageable pageable);
	
	List<ProductEntity> findByCategoryEntityId(int id);
	
	@Query(nativeQuery = true, value= "SELECT coalesce(max(pro.id), 0) FROM Product pro")
	int getMaxId();
}
