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
import com.javassem.domain.ProductVO;


@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public List<ProductVO> selectProduct(ProductVO vo) {
		System.out.println("mybatis inventoryList() 호출");
		return mybatis.selectList("AdminDAO.inventoryList",vo);
	}
	
	public void showChart() {
		System.out.println("mybatis showchart() 호출");
		
	}

	public List<MemberVO> selectMember(MemberVO vo) {
		System.out.println("mybatis memberList() 호출");
		return mybatis.selectList("AdminDAO.memberList",vo);
	}

	public List<MemberVO> selectEmployee(MemberVO vo) {
		System.out.println("mybatis employeeList() 호출");
		return mybatis.selectList("AdminDAO.employeeList",vo);
	}

	public List<DeliveryVO> selectDelivery(DeliveryVO vo) {
		System.out.println("mybatis deliveryList() 호출");
		return mybatis.selectList("AdminDAO.deliveryList",vo);
	}

	public List<ProductVO> inventoryInsert(ProductVO vo) {
		System.out.println("mybatis productInsert() 호출");
		return mybatis.selectList("AdminDAO.productInsert",vo);
	}

	public List<ProductVO> inventoryUpdate(ProductVO vo) {
		System.out.println("mybatis productUpdate() 호출");
		return mybatis.selectList("AdminDAO.productUpdate",vo);
	}

	public int memberDelete(MemberVO vo) {
		System.out.println("mybatis memberDelete() 호출");
		return mybatis.delete("AdminDAO.memberDelete",vo);
	}

	// 월별 매출
	public int[] salesMonth() {
		int [] result2 = new int[12] ;
		System.out.println("mybatis salesMonth() 호출");
	
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		
	     for(int i = 1; i<= 12; i++ ){
	     
	     hs.put("month",i);
	     
	     int j = mybatis.selectOne("AdminDAO.salesMonth", hs);
	     System.out.println(j);
	     result2 [i-1] = j;
	     }
	     System.out.println("mybatis salesMonth 끝나고나감");
	     return result2;
	}
	
	// 카테고리 별 매출
	public int[] salesCategory() {
		System.out.println("mybatis salesCategory() 호출");
		int [] result = new int[5] ;
	
		HashMap<String, String> hs = new HashMap<String, String>();
		
		String[]arr = {"table","bed","furniture","dressings","chair"};
		
		System.out.println("");
	     for(int i = 0; i<= 4; i++ ){
	    
	    hs.put("category",arr[i]);
	     
	    int j = mybatis.selectOne("AdminDAO.salesCategory", hs);
	    System.out.println(j);
	    result [i] = j;
	    }
	    System.out.println("mybatis salesCategory 끝나고나감");
	    return result;
	}

	// 최근 30일간 매출
	public int[] recentSales() {
		System.out.println("mybatis recentSales() 호출");
		int [] result = new int[31] ;
	
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		
	     for(int i = 0; i<= 30; i++ ){
	    
	    hs.put("day",i);
	     
	    int j = mybatis.selectOne("AdminDAO.recentSales", hs);
	    System.out.println(j);
	    result [i] = j;
	    }
	     
	    System.out.println("mybatis recentSales 끝나고나감");
	    return result;
	}
	
	// 일일 주문량
	public int orderCount() {
		
		return mybatis.selectOne("AdminDAO.orderCount");
	}

	public int memberCount() {
		
		return mybatis.selectOne("AdminDAO.memberCount");
	}
	
	public int[] viewCount() {
		
		System.out.println("mybatis viewList() 호출");
		int [] result = new int[31] ;
	
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		
	     for(int i = 0; i<= 30; i++ ){
	    
	    hs.put("view",i);
	     
	    int j = mybatis.selectOne("AdminDAO.viewList", hs);
	    System.out.println("j : "+j);
	    result [i] = j;
	    }
	    
	    System.out.println("mybatis viewList 끝나고나감1");
	    return result;
	}
	
	// 세션 방문자 수
    public void setTotalCount() {
    	System.out.println("DAO 도착");
    	
    	mybatis.selectOne("AdminDAO.visitCount");
    	
    	System.out.println("mapper 실행 끝");
    }
	
    // 인벤토리 nextval 가져오기
	public int getNextid() {
		System.out.println("nextval p_id DAO 도착");
		
		int result = mybatis.selectOne("AdminDAO.getNextid");
		
		return result;
	}
	
	
	
	
	

}
