package com.javassem.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

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
		mv.setViewName("/index");
		return mv;
		//회원가입 완료되면 완료됬다는 창으로 보내주기 그리고 거기서 다시 버튼만들어서 다시 메인창으로
		//
	}
	//
	@RequestMapping("/sign_in.do")
	public ModelAndView sign_in(LoginVO vo,HttpSession session){
		
		LoginVO id = loginservice.signInMember(vo); 

		ModelAndView mv = new ModelAndView();
		if(id == null||id.getMid()==null) {
			mv.setViewName("/login");
		}else {//로그인 성공했다면
			session.setAttribute("sessionTime", new Date().toLocaleString());
			session.setAttribute("userId", id.getMid());
			mv.setViewName("/index");
		}
	return mv;
	}
	
	@RequestMapping("/find_id.do")
	public ModelAndView findId(LoginVO vo) {
		System.out.println(vo.getMname());
		LoginVO result = loginservice.findId(vo);
		System.out.println(result.getMid());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/find-id-ok");
		mv.addObject("id", result.getMid());
		return mv;
	}


}
