package com.javassem.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javassem.domain.LoginVO;



@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO{

	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertMember(LoginVO vo) {
		System.out.println("===> Mybatis insertMember() 호출");
		mybatis.insert("LoginDAO.insertmember", vo);
	}

	

	

}