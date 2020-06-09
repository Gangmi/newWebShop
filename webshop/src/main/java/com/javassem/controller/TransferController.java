package com.javassem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.dao.TransferDAO;
import com.javassem.domain.OrderVO;



@Controller
public class TransferController {


	//2.
	@Autowired
	TransferDAO dao;

//	@ResponseBody
//@RequestMapping(value="/checkoutok.do",method=RequestMethod.POST,produces="applicaton/text; charset=UTF-8")
	@RequestMapping(value="/checkoutok.do",produces="applicaton/text; charset=UTF-8")
	public void checkoutok(String pay,String coupon,HttpServletResponse response, HttpServletRequest request,HttpSession session) throws Exception
	{
		
		List<String> idlist = (ArrayList)session.getAttribute("idlist");
		List<String> countlist = (ArrayList)session.getAttribute("countlist");
		String userId = (String)session.getAttribute("userId");

		dao.insertorder(pay,coupon,idlist,countlist,userId);
		for(int i=0; i<idlist.size();i++)
		{
			Cookie delcookie = new Cookie("cart"+idlist.get(i), null);
			delcookie.setMaxAge(0);
			delcookie.setPath("/");
			response.addCookie(delcookie);
		}
		
		
		response.sendRedirect("cart.do");
		

	}

	

}
