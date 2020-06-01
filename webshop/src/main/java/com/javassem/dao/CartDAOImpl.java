package com.javassem.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javassem.domain.ProductVO;
import com.javassem.domain.ShopVO;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO{

	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertShop(ProductVO vo) {
		System.out.println("===> Mybatis insertShop() 호출");
		mybatis.insert("ShopDAO.insertshop", vo);
	}

	public void updateShop(ProductVO vo) {
		System.out.println("===> Mybatis updateShop() 호출");
		mybatis.update("ShopDAO.updateshop", vo);
	}

	public void deleteShop(ProductVO vo) {
		System.out.println("===> Mybatis deleteShop() 호출");
		mybatis.delete("ShopDAO.deleteshop", vo);
	}

	public ProductVO getShop(ProductVO vo) {
		System.out.println("===> Mybatis getShop() 호출");
		System.out.println(vo.getP_id());
		return mybatis.selectOne("CartDAO.getshop", vo);
	}

	public List<ProductVO> getShopList(ProductVO vo) {
		System.out.println("===> Mybatis getShopList() 호출");
		return mybatis.selectList("ShopDAO.getshopList", vo);
	}
}