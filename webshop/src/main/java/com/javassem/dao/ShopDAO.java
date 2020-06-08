package com.javassem.dao;

import java.util.List;

import com.javassem.domain.ProductVO;

public interface ShopDAO {
	//상품에 표시될 리스트의 정보들을 가져오기
	public List<ProductVO> getProductDetail(ProductVO vo);
	
	public ProductVO getOneProduct(ProductVO vo);
	
	public int getCatTotal(ProductVO vo);
	
	public List<ProductVO>getitembytext(String search);

	
}
