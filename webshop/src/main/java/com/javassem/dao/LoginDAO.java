package com.javassem.dao;

import java.util.List;

import com.javassem.domain.LoginVO;


public interface LoginDAO {
	public int insertMember(LoginVO vo);

	public LoginVO signInMember(LoginVO vo);
}
