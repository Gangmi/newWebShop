package com.javassem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javassem.dao.LoginDAOImpl;
import com.javassem.domain.LoginVO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDAOImpl loginDAO;

	public void insertMember(LoginVO vo) {

		loginDAO.insertMember(vo);
	}



}