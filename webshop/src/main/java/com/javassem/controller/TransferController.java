package com.javassem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.dao.TransferDAO;



@Controller
public class TransferController {


	//2.
	@Autowired
	TransferDAO dao;
	@ResponseBody
	@RequestMapping(value="/transfer.do",method=RequestMethod.POST,produces="applicaton/text; charset=UTF-8")
	public ModelAndView addAll(int subtotal)  throws Exception{

		String msg = "주문이 성공적으로 이체되었습니다.";
		dao.transfer(subtotal);
		ModelAndView ma = new ModelAndView("cart");
		ma.addObject("msg", msg);
		return ma;
	}
	

}
