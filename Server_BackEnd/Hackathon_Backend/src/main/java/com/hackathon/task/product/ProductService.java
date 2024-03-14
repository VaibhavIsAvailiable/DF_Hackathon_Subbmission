package com.hackathon.task.product;

import java.util.List;

public interface ProductService {

ProductEntity createPost(ProductEntity postDTO,Integer userId , Integer categoryId);

void deletePost (Integer postId);

ProductEntity getPostById(Integer id);

List<ProductEntity> getAllPost();

List<ProductEntity> searchPost(String keyword);

ProductEntity updatePost(ProductEntity postDTO, Integer postId);	
	}