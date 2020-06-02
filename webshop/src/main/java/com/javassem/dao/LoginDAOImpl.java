package com.javassem.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javassem.domain.LoginVO;



@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO{

	//회원가입
	@Autowired
	private SqlSessionTemplate mybatis;

	public int insertMember(LoginVO vo) {
		System.out.println("===> Mybatis insertMember() 호출");
		return mybatis.insert("LoginDAO.insertmember", vo);
	}

	public LoginVO signInMember(LoginVO vo) {
		System.out.println("===> Mybatis signInMember() 호출");
		return mybatis.selectOne("LoginDAO.signInMember", vo);
		
	}

	

	

}