package com.javassem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javassem.domain.CouponVO;
import com.javassem.domain.LoginVO;
import com.javassem.domain.OrderVO;
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
		//hashmap을 사용하여 다중 값 mapper처리
		wish.put("P_ID", id);
		wish.put("M_ID", userId);
		mybatis.insert("CartDAO.insertWishlist",wish);

	}
	
	public List<ProductVO> selectWishlist(String userId)
	{
		System.out.println("===> Mybatis selectWishlist() 호출");

		// 해당 회원에 대한 wishlist에 표현하기 위한 list 값 select
		List<ProductVO> result = mybatis.selectList("CartDAO.selectWishlist",userId);
		//값을 잘 받아왔는지 개발자가 확인하기 위한 for문
		if(result!=null)
		{
			for(int i=0; i<result.size();i++)
			{
				ProductVO vo = new ProductVO();
				vo = result.get(i);
			}
		}
		// 모든 wishlist 목록 반환
		return result;
	}

	public void deleteWishlist(String[] array,String userId) {
		System.out.println("===> Mybatis deleteWishlist() 호출");
		List<OrderVO> list = new ArrayList();
		// 해당 회원아이디가 선택된 상품아이디를 삭제하기 위해 list에 담음
		for(int i=0; i<array.length;i++)
		{
			OrderVO vo = new OrderVO();
			vo.setP_id(Integer.parseInt(array[i]));
			vo.setM_id(userId);
			list.add(vo);
		}
		// list를 실어 해당 회원아이디의 상품들을 삭제
		int result = mybatis.delete("CartDAO.deleteWishlist",list);
		// 해당 mapper의 실행이 성공적인지 개발자가 확인하기 위한 if문
		if(result!=0)
		{
			System.out.println("위시 삭제ㅔ 성공");
		}
	}
	
	public CouponVO getmembercoupon(LoginVO vo)
	{
		System.out.println("===> Mybatis getmembercoupon() 호출");
		//checkout페이지에서 해당 회원이 25퍼센트 할인 쿠폰을 가지고 있는지 구분
		CouponVO couvo = mybatis.selectOne("CartDAO.selectmemcoupon",vo);
		System.out.println(couvo.getCou_id());
		
		CouponVO resultvo = new CouponVO();
		//반환된 값의 아이디가 있다면
		if(couvo.getCou_id()>0)
		{
			// 쿠폰아이디로 회원의 쿠폰아이디와 사용여부등 select
			resultvo = mybatis.selectOne("CartDAO.selectmemcouponvo",couvo);
		}// 반환된 값의 아이디가 없다면
		else
		{
			//쿠폰 아이디는 0으로 설정
			resultvo.setCou_id(0);
			resultvo.setUsed("0");
		}
		
		return resultvo;
		
		
	}
	


}