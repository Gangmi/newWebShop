package com.javassem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.LoginVO;
import com.javassem.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginservice;
	////
	//회원가입
	@RequestMapping("/insertMember.do")
	public ModelAndView insesrtMember(LoginVO vo) {
		int result = loginservice.insertMember(vo);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index.do");
		return mv;
		//회원가입 완료되면 완료됬다는 창으로 보내주기 그리고 거기서 다시 버튼만들어서 다시 메인창으로
		
	}
	//
	@RequestMapping("/sign_in.do")
	public void sign_in(LoginVO vo){
		loginservice.signInMember(vo);
	}


}
