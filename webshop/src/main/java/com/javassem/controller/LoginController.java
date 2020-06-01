package com.javassem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javassem.domain.LoginVO;
import com.javassem.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginservice;
		
	@RequestMapping("/insertMember.do")
	public String insesrtMember(LoginVO vo) {
		loginservice.insertMember(vo);
		return "redirect:/index.do";
	}
	


}
