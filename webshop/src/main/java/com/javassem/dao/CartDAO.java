package com.javassem.dao;

import java.util.List;

import com.javassem.domain.ProductVO;


public interface CartDAO {
	public void insertShop(ProductVO vo);

	public void updateShop(ProductVO vo) ;

	public void deleteShop(ProductVO vo);

	public ProductVO getShop(ProductVO vo) ;

	public List<ProductVO> getShopList(ProductVO vo) ;
}
