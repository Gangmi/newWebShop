package com.javassem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javassem.dao.LoginDAOImpl;
import com.javassem.domain.LoginVO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDAOImpl loginDAO;

	//회원가입
	public int insertMember(LoginVO vo) {

		return loginDAO.insertMember(vo);
	}
	
	public LoginVO signInMember(LoginVO vo) {
		return loginDAO.signInMember(vo);
	}

	public LoginVO findId(LoginVO vo) {
		return loginDAO.findId(vo);
	}
	
	public LoginVO findPassword(LoginVO vo) {
		return loginDAO.findPassword(vo);
	}

	public LoginVO memberInfo(LoginVO vo) {
		return loginDAO.memberInfo(vo);
	}
	
	public int updateMember(LoginVO vo) {
		return loginDAO.updateMember(vo);
	}

	
	public int deleteMember(LoginVO vo) {
		return loginDAO.deleteMember(vo);
	}
	
	public int subemail(LoginVO vo) {
		return loginDAO.subemail(vo);
	}
	
}