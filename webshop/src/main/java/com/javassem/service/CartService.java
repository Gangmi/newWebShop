package com.javassem.service;

import java.util.ArrayList;
import java.util.List;

import com.javassem.domain.CouponVO;
import com.javassem.domain.LoginVO;
import com.javassem.domain.ProductVO;




public interface CartService {
	// CRUD 기능의 메소드 구현
	// 글 등록
	void insertShop(ProductVO vo);

	// 글 수정
	void updateShop(ProductVO vo);

	// 글 삭제
	void deleteShop(ProductVO vo);

	// 글 상세 조회
	ProductVO getShop(ProductVO vo);

	// 글 목록 조회
	List<ProductVO> getShopList(List<ProductVO> seq);
	
	LoginVO getmemberInfo(LoginVO vo);

	void insertWishlist(String id, String userId);
	
	List<ProductVO> selectWishlist(String userId);
	
	void deleteWishlist(String[] array,String userId);
	
	CouponVO getmembercoupon(LoginVO vo);

	
}
