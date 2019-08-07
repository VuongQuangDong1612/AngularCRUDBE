package com.demo.angularspring.crud.angularSpringCrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Image")
public class Image {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	@Column(name = "IMGURL")
	public String imgUrl;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private ProductEntity productEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	
	

}
