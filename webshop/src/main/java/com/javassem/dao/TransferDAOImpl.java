package com.javassem.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javassem.domain.OrderVO;



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
	public void transfer(int subtotal) throws TransException{
//		int resultSend = sqlSession.update("trans.withdraw", send);
//		if( resultSend == 0) throw new TransException();
//		System.out.println("인출");
//		int resultRecv = sqlSession.update("trans.deposit", recv);
//		if( resultRecv == 0) throw new TransException();
//		System.out.println("입금");
	}
	@Transactional(rollbackFor=TransException.class)
	@Override
	public void insertorder(String pay,ArrayList<String> idlist, ArrayList<String> countlist, String userId) throws TransException{
		System.out.println("===> Mybatis insertorder() 호출");
		for(int i=0; i<idlist.size();i++)
		{
			String count = countlist.get(i);
			String id = idlist.get(i);
	
		}
		HashMap porder = new HashMap();

		porder.put("M_ID", userId);
		porder.put("O_PAYMENT", pay);
		int resultSend = sqlSession.insert("Trans.productorder", porder);
		if( resultSend == 0) throw new TransException();
		int o_id = (int) porder.get("productorderid");
		
		
		

		
		
	}

}
