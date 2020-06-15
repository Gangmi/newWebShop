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
		//입력한 회원정보를 member db에 저장한다.
		int result= mybatis.insert("LoginDAO.insertmember", vo);
		
		return result;
	}

	//로그인
	public LoginVO signInMember(LoginVO vo) {
		System.out.println("===> Mybatis signinmember() 호출");
		//아이디와 비밀번호를 member db에 넘겨서 맞다면 아이디를 받아온다.
		LoginVO result = mybatis.selectOne("LoginDAO.signinmember", vo);

		return result;
		
	}

	//아이디 찾기
	public LoginVO findId(LoginVO vo) {
		System.out.println("===> Mybatis findid() 호출");
		//이름과 전화번호를 member db에 넘겨서 그에 맞는 아이디를 받아온다.
		LoginVO result = mybatis.selectOne("LoginDAO.findid", vo);

		return  result;
	}

	//비밀번호 찾기
	public LoginVO findPassword(LoginVO vo) {
		System.out.println("===> Mybatis findpassword() 호출");
		//아이디를 member db에 넘겨서 이메일주소와 아이디 비밀번호를 받아온다.
		LoginVO result = mybatis.selectOne("LoginDAO.findpassword", vo);
		
		return result;
	}

	//회원정보
	public LoginVO memberInfo(LoginVO vo) {
		
		System.out.println("===> Mybatis memberInfo() 호출");
		//로그인한 아이디를 가져와 member db에 넣고 그와 관련된 정보드를 받아온다.
		LoginVO result = mybatis.selectOne("LoginDAO.memberInfo", vo);
		return result;
	}

	//회원정보수정
	public int updateMember(LoginVO vo) {
		System.out.println("===> Mybatis memberInfo() 호출");
		//수정한 내용을 넘긴다.
		int result = mybatis.update("LoginDAO.updatemember", vo);
		return result;
	}

	//회원정보 삭제
	public int deleteMember(LoginVO vo) {
		System.out.println("===> Mybatis deleteMember() 호출");
		//로그인한 아이디에 맞는 개인정보들을 삭제한다.
		int result = mybatis.delete("LoginDAO.deleteMember", vo);
		return result;
	}
	
	//구독
	public int subemail(LoginVO vo) {
		System.out.println("===> Mybatis subemail() 호출");
		System.out.println("===> Mybatis insertcoupon() 호출");
		 mybatis.insert("LoginDAO.insertcoupon", vo);//구독을 안했다면 쿠폰을 넣음
		int result = mybatis.update("LoginDAO.subemail", vo);//구독을 x를 o로 바꿈
		return result;
	}

	//구독여부
	public LoginVO checkcoupon(LoginVO vo) {
		System.out.println("===> Mybatis checkcoupon() 호출");
		//로그인한 아이디가 구독을 했는지 안했는지 
		LoginVO result = mybatis.selectOne("LoginDAO.checkcoupon", vo);
		return result;
	}

	//주문내역을 가져옴
	public List<OrderVO> myorder(OrderVO vo) {
		System.out.println("===> Mybatis myorder() 호출");
		List<OrderVO> result = mybatis.selectList("LoginDAO.myorder", vo);
		return result;
	}

	//주문번호에 맞는 가격 총합 구함
	public List<OrderVO> myorder_sum(OrderVO vo) {
		System.out.println("===> Mybatis myorder_sum() 호출");
		List<OrderVO> result = mybatis.selectList("LoginDAO.myorder_sum", vo);
		return result;
	}

	

}