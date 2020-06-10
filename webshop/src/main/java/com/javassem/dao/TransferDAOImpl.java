package com.javassem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javassem.domain.LoginVO;
import com.javassem.domain.OrderVO;
import com.javassem.domain.ProductVO;



@Repository
public class TransferDAOImpl implements TransferDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//#############################################
	/*
	 *  선언적 트랜잭션에서는 런타임 예외가 발생하면 롤백한지만 예외가 발생하지 않거나 체크 예외가 발생하면 커밋한다.
	 *  그래서 rollbackFor를 이용하여 예외를 지정한다.
	 */

	@Transactional(rollbackFor=TransException.class)
	@Override
	public int insertorder(String pay,String coupon,List<String> idlist,List<String> countlist, String userId) throws TransException{
		System.out.println("===> Mybatis insertorder() 호출");
		System.out.println(coupon);
		HashMap porder = new HashMap();
		int result=0;
		porder.put("M_ID", userId);
		porder.put("O_PAYMENT", pay);
		int resultpo = sqlSession.insert("Trans.productorder", porder);
		if( resultpo == 0) throw new TransException();
		if( resultpo > 0)
		{
			System.out.println("productorder 성공");
			result++;
		}
		int o_id = (int) porder.get("productorderid");
		
		List<OrderVO> ordervo = new ArrayList<OrderVO>();
		for(int i=0; i<idlist.size();i++)
		{
			String count = countlist.get(i);
			String id = idlist.get(i);
			OrderVO vo = new OrderVO();
			vo.setCnt(Integer.parseInt(count));
			vo.setP_id(Integer.parseInt(id));
			vo.setO_id(o_id);
			int resultolqty = sqlSession.update("Trans.orderlistqty", vo);
			if( resultolqty == 0) throw new TransException();
			if( resultolqty > 0)
			{
				System.out.println("orderlistqty 성공");
				result++;
			}

			ordervo.add(vo);
			
		}

		int resultol = sqlSession.insert("Trans.orderlist", ordervo);
		if( resultol == 0) throw new TransException();
		if( resultol > 0)
		{
			System.out.println("orderlist 성공");
			result++;
		}
		if(Integer.parseInt(coupon)>0)
		{
			HashMap coumap = new HashMap();
			coumap.put("coupon", Integer.parseInt(coupon));
			coumap.put("M_ID", userId);
			coumap.put("o_id", o_id);
			System.out.println(o_id);
			int resultcou = sqlSession.update("Trans.couponupdate", coumap);
			if( resultcou == 0) throw new TransException();
			if( resultcou > 0)
			{
				System.out.println("couponupdate 성공");
			}
			result++;
		}
		int resultgrade = sqlSession.update("Trans.gradeup",userId);
		if( resultgrade == 0) throw new TransException();
		if( resultgrade > 0)
		{
			System.out.println("gradeup 성공");
			result++;
		}
		
		if(result<3)
		{	
			result=0;
		}
		return result;
		
		
	}

}
