package com.demo.angularspring.crud.angularSpringCrud.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "Category")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "NAME")
	private String name;

	@OneToMany(mappedBy="categoryEntity")
	@JsonIgnore
	private Set<ProductEntity> productEntities;
	
	
	public CategoryEntity() {
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductEntity> getProductEntities() {
		return productEntities;
	}

	public void setProductEntities(Set<ProductEntity> productEntities) {
		this.productEntities = productEntities;
	}
	
	

}
