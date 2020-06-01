package com.javassem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javassem.dao.CartDAOImpl;
import com.javassem.dao.ShopDAOImpl;
import com.javassem.domain.ProductVO;
import com.javassem.domain.ShopVO;


@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDAOImpl CartDAO;

	public void insertShop(ProductVO vo) {

		CartDAO.insertShop(vo);
	}

	public void updateShop(ProductVO vo) {
		CartDAO.updateShop(vo);
	}

	public void deleteShop(ProductVO vo) {
		CartDAO.deleteShop(vo);
	}

	public ProductVO getShop(ProductVO vo) {
		return CartDAO.getShop(vo);
	}

	public List<ProductVO> getShopList(ProductVO vo) {
		return CartDAO.getShopList(vo);
	}


}