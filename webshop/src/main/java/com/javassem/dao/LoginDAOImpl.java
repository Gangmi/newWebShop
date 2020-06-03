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
		System.out.println("===> Mybatis signinmember() 호출");
//		System.out.println(vo.getMid() + "/" + vo.getMpass());
		LoginVO result = mybatis.selectOne("LoginDAO.signinmember", vo);
//		System.out.println(result.getMid() + "/" + result.getMpass());
		return result;
		
	}

	
	public LoginVO findId(LoginVO vo) {
		System.out.println("===> Mybatis findid() 호출");
//		System.out.println(vo.getMname());
		LoginVO result = mybatis.selectOne("LoginDAO.findid", vo);
//		System.out.println(vo.getMid());
		return  result;
	}

	public LoginVO findPassword(LoginVO vo) {
		System.out.println("===> Mybatis findpassword() 호출");
		LoginVO result = mybatis.selectOne("LoginDAO.findpassword", vo);
		return result;
	}

	

}