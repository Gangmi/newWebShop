package com.javassem.service;

import com.javassem.domain.LoginVO;

public interface LoginService {

	int insertMember(LoginVO vo);

	LoginVO signInMember(LoginVO vo);
}
