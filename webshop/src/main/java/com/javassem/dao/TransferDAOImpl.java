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
		// 신용카드와 현금중 선택된 결제 방식과 회원 아이디를 map에 put
		porder.put("M_ID", userId);
		porder.put("O_PAYMENT", pay);
		// 상품 오더 테이블에 등록
		int resultpo = sqlSession.insert("Trans.productorder", porder);
		if( resultpo == 0) throw new TransException();
		if( resultpo > 0)
		{
			System.out.println("productorder 성공");
			//상품오더 테이블 입력이 성공했으면 result 수 증가
			result++;
		}
		// 상품오더 테이블에 입력한 시퀀스 값 가지고 옴
		int o_id = (int) porder.get("productorderid");
		
		List<OrderVO> ordervo = new ArrayList<OrderVO>();
		for(int i=0; i<idlist.size();i++)
		{
			//상품아이디와 수량들을 뽑아냄
			String count = countlist.get(i);
			String id = idlist.get(i);
			OrderVO vo = new OrderVO();
			//vo에 담아
			vo.setCnt(Integer.parseInt(count));
			vo.setP_id(Integer.parseInt(id));
			vo.setO_id(o_id);
			//상품아이디별 재고량 줄이기
			int resultolqty = sqlSession.update("Trans.orderlistqty", vo);
			if( resultolqty == 0) throw new TransException();
			if( resultolqty > 0)
			{
				// 상품아이디별 재고량줄이기 성공했다면 result수 증가
				System.out.println("orderlistqty 성공");
				result++;
			}

			// 오더리스트에 넣기위한 list
			ordervo.add(vo);
			
		}

		// orderlist에 상품별 입력
		int resultol = sqlSession.insert("Trans.orderlist", ordervo);
		if( resultol == 0) throw new TransException();
		if( resultol > 0)
		{
			//orderlist 입력 성공했다면 result수 증가
			System.out.println("orderlist 성공");
			result++;
		}
		// 만약 사용자가 쿠폰을 썻다면
		if(Integer.parseInt(coupon)>0)
		{
			//쿠폰테이블에 사용했단걸 표시하기 위해 쿠폰아이디와 회원아이디 상품오더아이디 hashmap에 put
			HashMap coumap = new HashMap();
			coumap.put("coupon", Integer.parseInt(coupon));
			coumap.put("M_ID", userId);
			coumap.put("o_id", o_id);
			System.out.println(o_id);
			// 사용여부와 어느 오더에 썻는지에 대한 update문
			int resultcou = sqlSession.update("Trans.couponupdate", coumap);
			if( resultcou == 0) throw new TransException();
			if( resultcou > 0)
			{
				//쿠폰사용 여부 update에 성공했다면 result수 증가
				System.out.println("couponupdate 성공");
				result++;
			}
			
		}
		// 만약 회원의 총구매가 50만원 이상이라면 실버등급으로 업그레이드, 100만원 이상이면 골드로 업그레이드
		int resultgrade = sqlSession.update("Trans.gradeup",userId);
		if( resultgrade == 0) throw new TransException();
		if( resultgrade > 0)
		{
			// 등급 update가 성공했다면 result수 증가
			System.out.println("gradeup 성공");
			result++;
		}
		
		//항상 필요한 상품오더 , 재고량, 오더리스트, 등급 result는 4이상이어야 함  
		if(result<3)
		{	//그렇지 않다면 result0으로 처리
			result=0;
		}
		return result;
		
		
	}

}
