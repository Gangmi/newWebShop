package com.javassem.dao;

import java.util.List;

import com.javassem.domain.LoginVO;


public interface LoginDAO {
	public int insertMember(LoginVO vo);

	public LoginVO signInMember(LoginVO vo);
	
	public LoginVO findId(LoginVO vo);
	
	public LoginVO findPassword(LoginVO vo);
	
	public LoginVO memberInfo(LoginVO vo);
	
	public int updateMember(LoginVO vo);
	
	public int deleteMember(LoginVO vo);
	
	public int subemail(LoginVO vo);
}
