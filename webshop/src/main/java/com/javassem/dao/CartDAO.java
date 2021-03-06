package com.javassem.dao;

import java.util.ArrayList;
import java.util.List;

import com.javassem.domain.CouponVO;
import com.javassem.domain.LoginVO;
import com.javassem.domain.ProductVO;


public interface CartDAO {
	public void insertShop(ProductVO vo);

	public void updateShop(ProductVO vo) ;

	public void deleteShop(ProductVO vo);

	public ProductVO getShop(ProductVO vo) ;

	public List<ProductVO> getShopList(List<ProductVO> seq) ;
	
	public List<ProductVO> selectWishlist(String userId);
	
	void deleteWishlist(String[] array,String userId);
	
	public CouponVO getmembercoupon(LoginVO vo);
	

}
