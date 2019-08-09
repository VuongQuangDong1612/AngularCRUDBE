package com.demo.angularspring.crud.angularSpringCrud.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.angularspring.crud.angularSpringCrud.entity.Image;
import com.demo.angularspring.crud.angularSpringCrud.entity.ProductEntity;
import com.demo.angularspring.crud.angularSpringCrud.repository.ImageRepository;
import com.demo.angularspring.crud.angularSpringCrud.repository.ProductRepository;
import com.demo.angularspring.crud.angularSpringCrud.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ImageRepository imageRepository;

	final String UPLOAD_FOLDER = "D:\\imageProduct";

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

	@Override
	public List<String> getImage(int id) {
		ProductEntity productEntity = productRepository.findById(id).get();
		Set<Image> listImage = productEntity.getImages();
		System.out.println("TESSTTTTTTTTTTTTTTTTT" + listImage.size());
		List<String> listImageByteEncode = new ArrayList<>();
		for(Image image : listImage) {
			try {
				File file = new File(image.getImgUrl());
				
				BufferedImage originalImage = ImageIO.read(file);
		        ByteArrayOutputStream baos=new ByteArrayOutputStream();
		        ImageIO.write(originalImage, "jpg", baos);
		        byte[] imageInByte = baos.toByteArray();
		        String encoded = Base64.getEncoder().encodeToString(imageInByte);
		        
		        listImageByteEncode.add(encoded);
			} catch (IOException e) {
				e.printStackTrace();
			
			}
		}
		return listImageByteEncode;
	}

	@Override
	public void uploadSaveImage(MultipartFile[] files, int id) {
		ProductEntity productEntity = productRepository.findById(id).get();
		for(MultipartFile file : files) {
			try {
				String fileName = file.getOriginalFilename();
				File fileUpload = new File(UPLOAD_FOLDER +  File.separator + fileName);
				FileOutputStream fileOutputStream = new FileOutputStream(fileUpload);
				fileOutputStream.write(file.getBytes());
				
				Image image = new Image();
				image.setImgUrl(fileUpload.getPath());
				
				image.setProductEntity(productEntity);
				imageRepository.save(image);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	
	}
	
	
}
