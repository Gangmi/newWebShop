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
	public void insertorder(String pay,List<String> idlist,List<String> countlist, String userId) throws TransException{
		System.out.println("===> Mybatis insertorder() 호출");
		
		HashMap porder = new HashMap();

		porder.put("M_ID", userId);
		porder.put("O_PAYMENT", pay);
		int resultpo = sqlSession.insert("Trans.productorder", porder);
		if( resultpo == 0) throw new TransException();
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

			ordervo.add(vo);
			
		}
		
		
		int resultol = sqlSession.insert("Trans.orderlist", ordervo);
		if( resultol == 0) throw new TransException();
		
		
		int resultolqty = sqlSession.update("Trans.orderlistqty", ordervo);
		if( resultolqty == 0) throw new TransException();
		if( resultolqty > 0)
		{
			System.out.println("요건 성공");
		}
		sqlSession.update("Trans.gradeup",userId);
		
		

		
		
	}

}
