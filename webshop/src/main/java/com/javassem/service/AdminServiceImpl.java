package com.javassem.service;

import java.util.List;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javassem.dao.AdminDAO;
import com.javassem.dao.AdminDAOImpl;
import com.javassem.domain.DeliveryVO;
import com.javassem.domain.MemberVO;
import com.javassem.domain.ProductVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAOImpl adminDAO;
	
	public void showChart() {
		adminDAO.showChart();
	}
	
	public List<ProductVO> selectProduct(ProductVO vo) {
		return adminDAO.selectProduct(vo);
	}

	public List<MemberVO> selectMember(MemberVO vo) {
		return adminDAO.selectMember(vo);
	}

	public List<MemberVO> selectEmployee(MemberVO vo) {
		return adminDAO.selectEmployee(vo);
	}

	public List<DeliveryVO> selectDelivery(DeliveryVO vo) {
		return adminDAO.selectDelivery(vo);
	}

	public List<ProductVO> inventoryInsert(ProductVO vo) {
		return adminDAO.inventoryInsert(vo);
	}

	public List<ProductVO> inventoryUpdate(ProductVO vo) {
		return adminDAO.inventoryUpdate(vo);
	}

	public int memberDelete(MemberVO vo) {
		return adminDAO.memberDelete(vo);
	}
	
	public int[] salesMonth() {
		return adminDAO.salesMonth();
	}
	
	public int[] salesCategory() {
		return adminDAO.salesCategory();
	}

	public int[] recentSales() {
		return adminDAO.recentSales();
	}

	public int orderCount() {
		return adminDAO.orderCount();
	}
	
	public int memberCount() {
		return adminDAO.memberCount();
	}
	
	public int[] viewCount() {
		return adminDAO.viewCount();
	}

	public void setTotalCount() {
		adminDAO.setTotalCount();
		return;
	}
	
//	public int[] viewList() {
//		return adminDAO.viewList();
//	}

	
}
