package com.javassem.service;

import java.util.List;

import com.javassem.domain.ProductVO;



public interface ShopService {
	// CRUD 기능의 메소드 구현
	// 가격과 제품 id 를 가져오기 위해서 
	List<ProductVO> getProductDetail(ProductVO vo);
	
	ProductVO getOneProduct(ProductVO vo);
	
	int getCatTotal (ProductVO vo);
	
	
	
}
