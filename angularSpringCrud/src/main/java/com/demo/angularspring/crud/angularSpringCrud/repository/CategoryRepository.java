package com.demo.angularspring.crud.angularSpringCrud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.angularspring.crud.angularSpringCrud.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

}
