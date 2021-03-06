package com.javassem.service;

import java.util.List;

import javax.servlet.http.HttpSessionEvent;

import com.javassem.domain.DeliveryVO;
import com.javassem.domain.MemberVO;
import com.javassem.domain.OrderVO;
import com.javassem.domain.ProductVO;


public interface AdminService {
	// CRUD 기능의 메소드 구현
	void showChart();
	
	List<ProductVO> selectProduct(ProductVO vo);

	List<MemberVO> selectMember(MemberVO vo);

	List<MemberVO> selectEmployee(MemberVO vo);

	List<OrderVO> selectDelivery(OrderVO vo);

	List<ProductVO> inventoryInsert(ProductVO vo);

	List<ProductVO> inventoryUpdate(ProductVO vo);

	int memberDelete(MemberVO vo);
	
	int[] salesMonth();

	int[] salesCategory();

	int[] recentSales();
	
	int orderCount();

	int memberCount();
	
	int[] viewCount();
		
	void setTotalCount();

	int getNextid();

	int[] salesAge();
	
	int updateDeli(OrderVO vo);
	
}