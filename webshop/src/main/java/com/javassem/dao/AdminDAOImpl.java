package com.javassem.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSessionEvent;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javassem.domain.DeliveryVO;
import com.javassem.domain.MemberVO;
import com.javassem.domain.OrderVO;
import com.javassem.domain.ProductVO;


@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 제품 리스트
	public List<ProductVO> selectProduct(ProductVO vo) {
		System.out.println("mybatis inventoryList() 호출");
		return mybatis.selectList("AdminDAO.inventoryList",vo);
	}
	
	public void showChart() {
		System.out.println("mybatis showchart() 호출");
	}
	
	// 회원 리스트
	public List<MemberVO> selectMember(MemberVO vo) {
		System.out.println("mybatis memberList() 호출");
		return mybatis.selectList("AdminDAO.memberList",vo);
	}
	// 사원 리스트
	public List<MemberVO> selectEmployee(MemberVO vo) {
		System.out.println("mybatis employeeList() 호출");
		return mybatis.selectList("AdminDAO.employeeList",vo);
	}
	// 주문 리스트 (delivery에서 수정)
	public List<OrderVO> selectDelivery(OrderVO vo) {
		System.out.println("mybatis deliveryList() 호출");
		return mybatis.selectList("AdminDAO.deliveryList",vo);
	}
	// 재고 인썰트
	public List<ProductVO> inventoryInsert(ProductVO vo) {
		System.out.println("mybatis productInsert() 호출");
		return mybatis.selectList("AdminDAO.productInsert",vo);
	}
	// 재고 업데이트
	public List<ProductVO> inventoryUpdate(ProductVO vo) {
		System.out.println("mybatis productUpdate() 호출");
		return mybatis.selectList("AdminDAO.productUpdate",vo);
	}

	// 회원 삭제 버튼
	public int memberDelete(MemberVO vo) {
		System.out.println("mybatis memberDelete() 호출");
		return mybatis.delete("AdminDAO.memberDelete",vo);
	}

	// 월별 매출
	public int[] salesMonth() {
		int [] result2 = new int[12] ;
		System.out.println("mybatis salesMonth() 호출");
		// HashMap으로 하나씩 매퍼로 전송
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
	     for(int i = 1; i<= 12; i++ ){
	     hs.put("month",i);
	     int j = mybatis.selectOne("AdminDAO.salesMonth", hs);
	     result2 [i-1] = j;
	     }
	     return result2;
	}
	
	// 카테고리 별 매출
	public int[] salesCategory() {
		System.out.println("mybatis salesCategory() 호출");
		int [] result = new int[5];
		// HashMap으로 하나씩 매퍼로 전송
		HashMap<String, String> hs = new HashMap<String, String>();
		String[]arr = {"table","bed","furniture","dressings","chair"};
	     for(int i = 0; i<= 4; i++ ){
	    hs.put("category",arr[i]);
	    int j = mybatis.selectOne("AdminDAO.salesCategory", hs);
	    result [i] = j;
	    }
	    return result;
	}

	// 최근 30일간 매출
	public int[] recentSales() {
		System.out.println("mybatis recentSales() 호출");
		int [] result = new int[31] ;
		// HashMap으로 하나씩 매퍼로 전송
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
	     for(int i = 0; i<= 30; i++ ){
	    hs.put("day",i);
	    int j = mybatis.selectOne("AdminDAO.recentSales", hs);
	    result [i] = j;
	    }
	     System.out.println("recentSales 나감");
	    return result;
	}
	
	// 일일 주문건 수
	public int orderCount() {
		return mybatis.selectOne("AdminDAO.orderCount");
	// 1. 주문시 sysdate로 DB에 입력 
	// 2. sysdate의 count로 갯수를 뽑아 리턴 
	}
	// 일일 회원가입 수
	public int memberCount() {
		return mybatis.selectOne("AdminDAO.memberCount");
	// 1. 회원가입시 sysdate로 DB에 입력 
	// 2. sysdate의 count로 갯수를 뽑아 리턴 
	}
	
	// 대시보드 방문자 수
	public int[] viewCount() {
		System.out.println("mybatis viewList() 호출");
		int [] result = new int[31] ;
		// HashMap으로 하나씩 매퍼로 전송
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
	    for(int i = 0; i<= 30; i++ ){
	    hs.put("view",i);
	    // sysdate-#{view} 를 함으로써 -30일 까지의 방문자수 추출
	    int j = mybatis.selectOne("AdminDAO.viewList", hs);
	    // 각각 날짜의 방문자수를 배열로 만들어 리턴
	    result [i] = j;
	    }
	    return result;
	}
	
	// 세션 방문자 수
    public void setTotalCount() {
    	mybatis.selectOne("AdminDAO.visitCount");
    }
	
    // 인벤토리 nextval 가져오기
	public int getNextid() {
		System.out.println("nextval p_id DAO 도착");
		int result = mybatis.selectOne("AdminDAO.getNextid");
		return result;
	}

	// 나이대 매출
	public int[] salesAge() {
		System.out.println("salesAge DAO 도착");
		int [] result = new int[6] ;
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
	    for(int i = 0; i< 6; i++ ){
	    hs.put("age",i+1);
	    int j = mybatis.selectOne("AdminDAO.salesAge", hs);
	    result [i] = j;
	    }
	    return result;
	}
	
	// 배송상태 변경
	public int updateDeli(OrderVO vo) {
		System.out.println("updateDeli DAO 도착");
		return mybatis.update("AdminDAO.updateDeli",vo);
	}
	
	
}
