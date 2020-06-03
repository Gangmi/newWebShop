package com.javassem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.dao.TransferDAO;


@Controller
public class TransferController {


	//2.
	@Autowired
	TransferDAO dao;

	@RequestMapping(value="/transfer.do",method=RequestMethod.POST)
	public void addAll()  throws Exception{

//		String msg = "계좌가 성공적으로 이체되었습니다.";
//		dao.transfer(send, recv);
//		ModelAndView ma = new ModelAndView("result");
//		ma.addObject("msg", msg);
//		return ma;
	}

}
