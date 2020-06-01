package com.javassem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javassem.dao.ShopDAOImpl;
import com.javassem.domain.ProductVO;



@Service("shopService")
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopDAOImpl shopDAO;

	/*
	 * public void insertShop(ShopVO vo) {
	 * 
	 * shopDAO.insertShop(vo); }
	 * 
	 * public void updateShop(ShopVO vo) { shopDAO.updateShop(vo); }
	 * 
	 * public void deleteShop(ShopVO vo) { shopDAO.deleteShop(vo); }
	 * 
	 * public ShopVO getShop(ShopVO vo) { return shopDAO.getShop(vo); }
	 * 
	 * public List<ShopVO> getShopList(ShopVO vo) { return shopDAO.getShopList(vo);
	 * }
	 */

	@Override
	public List<ProductVO> getProductDetail(ProductVO vo) {
		
		return shopDAO.getProductDetail(vo);
	}


}