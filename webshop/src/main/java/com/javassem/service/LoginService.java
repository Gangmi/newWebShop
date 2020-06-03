package com.javassem.service;

import com.javassem.domain.LoginVO;

public interface LoginService {

	int insertMember(LoginVO vo);

	LoginVO signInMember(LoginVO vo);

	LoginVO findId(LoginVO vo);

	LoginVO findPassword(LoginVO vo);
}
