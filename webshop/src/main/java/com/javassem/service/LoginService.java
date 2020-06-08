package com.javassem.service;

import com.javassem.domain.LoginVO;

public interface LoginService {

	int insertMember(LoginVO vo);

	LoginVO signInMember(LoginVO vo);

	LoginVO findId(LoginVO vo);

	LoginVO findPassword(LoginVO vo);

	LoginVO memberInfo(LoginVO vo);

	int updateMember(LoginVO vo);

	int deleteMember(LoginVO vo);

	int subemail(LoginVO vo);

	
}
