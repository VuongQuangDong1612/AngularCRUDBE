package com.demo.angularspring.crud.angularSpringCrud.entity;

import java.sql.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Product")
public class ProductEntity {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;
	
	@Column(name = "NAME")
	public String name;
	
	@Column(name = "ORIGIN")
	public String origin;
	
	@Column(name = "DATEOFBIRTH")
	public Date dateOfBirth;
	
	@Column(name = "AMOUNT")
	public int amount;
	
	@Column(name = "PRICE")
	public int price;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private CategoryEntity categoryEntity;
	
	@OneToMany(mappedBy="productEntity")
	@JsonIgnoreProperties(value="productEntity")
	private Set<Image> images;
	
	public ProductEntity() {
		
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
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}
	
	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
	@PreRemove
    private void removeProductInImages() {
		Iterator<Image> imagesIte = this.getImages().iterator();
		while(imagesIte.hasNext()) {
			Image img = imagesIte.next();
			if(img.getProductEntity().getId() == this.id) {
				img.setProductEntity(null);
			}
		}
    }
}
