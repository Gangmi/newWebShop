package com.javassem.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javassem.domain.LoginVO;
import com.javassem.domain.OrderVO;



@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO{

	//회원가입
	@Autowired
	private SqlSessionTemplate mybatis;

	public int insertMember(LoginVO vo) {
		System.out.println("===> Mybatis insertMember() 호출");
		
		int result= mybatis.insert("LoginDAO.insertmember", vo);
		
		return result;
	}

	public LoginVO signInMember(LoginVO vo) {
		System.out.println("===> Mybatis signinmember() 호출");

		LoginVO result = mybatis.selectOne("LoginDAO.signinmember", vo);

		return result;
		
	}

	
	public LoginVO findId(LoginVO vo) {
		System.out.println("===> Mybatis findid() 호출");

		LoginVO result = mybatis.selectOne("LoginDAO.findid", vo);

		return  result;
	}

	public LoginVO findPassword(LoginVO vo) {
		System.out.println("===> Mybatis findpassword() 호출");
		LoginVO result = mybatis.selectOne("LoginDAO.findpassword", vo);
		
		return result;
	}

	public LoginVO memberInfo(LoginVO vo) {
		
		System.out.println("===> Mybatis memberInfo() 호출");
		LoginVO result = mybatis.selectOne("LoginDAO.memberInfo", vo);
		return result;
	}

	public int updateMember(LoginVO vo) {
		System.out.println("===> Mybatis memberInfo() 호출");
		int result = mybatis.update("LoginDAO.updatemember", vo);
		return result;
	}

	public int deleteMember(LoginVO vo) {
		System.out.println("===> Mybatis deleteMember() 호출");
		int result = mybatis.delete("LoginDAO.deleteMember", vo);
		return result;
	}

	public int subemail(LoginVO vo) {
		System.out.println("===> Mybatis subemail() 호출");
		System.out.println("===> Mybatis insertcoupon() 호출");
		 mybatis.insert("LoginDAO.insertcoupon", vo);
		int result = mybatis.update("LoginDAO.subemail", vo);
		return result;
	}

	public LoginVO checkcoupon(LoginVO vo) {
		System.out.println("===> Mybatis checkcoupon() 호출");
		LoginVO result = mybatis.selectOne("LoginDAO.checkcoupon", vo);
		return result;
	}

	public List<OrderVO> myorder(OrderVO vo) {
		System.out.println("===> Mybatis myorder() 호출");
		List<OrderVO> result = mybatis.selectList("LoginDAO.myorder", vo);
		return result;
	}

	

}