package com.javassem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javassem.domain.LoginVO;
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

	public List<ProductVO> getShopList(List<ProductVO> seq) {
		System.out.println("===> Mybatis getShopList() 호출");
		return mybatis.selectList("CartDAO.getshopList", seq);
	}
	@Transactional(rollbackFor=TransException.class)
	public LoginVO getmemberInfo(LoginVO vo)
	{
		System.out.println("===> Mybatis getmemberInfo() 호출");
		return mybatis.selectOne("CartDAO.getmemberInfo",vo);
	}
	public void insertWishlist(String id,String userId)
	{
		System.out.println("===> Mybatis insertWishlist() 호출");
		HashMap wish = new HashMap();

		wish.put("P_ID", id);
		wish.put("M_ID", userId);
		mybatis.insert("CartDAO.insertWishlist",wish);

	}
	
	public List<ProductVO> selectWishlist(String userId)
	{
		System.out.println("===> Mybatis selectWishlist() 호출");

		List<ProductVO> result = mybatis.selectList("CartDAO.selectWishlist",userId);
		if(result!=null)
		{
			for(int i=0; i<result.size();i++)
			{
				ProductVO vo = new ProductVO();
				vo = result.get(i);
				System.out.println(vo.getP_name());
			}
		}
		return result;
	}


}