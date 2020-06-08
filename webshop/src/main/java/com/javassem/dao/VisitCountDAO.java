package com.javassem.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class VisitCountDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis; 
	 
	
    public void setTotalCount() {
    	System.out.println("DAO 도착");
    	
    	mybatis.insert("AdminDAO.visitCount");
    	
    	System.out.println("mapper 실행 끝");
        
    }
    
}
