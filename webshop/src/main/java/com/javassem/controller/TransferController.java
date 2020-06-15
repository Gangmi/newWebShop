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
import org.springframework.ui.Model;
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
	public ModelAndView checkoutok(String pay,String coupon,HttpServletResponse response, HttpServletRequest request,HttpSession session) throws Exception
	{
		//checkout페이지에서 session으로 담은 id와 수량을 가지고 옴
		List<String> idlist = (ArrayList)session.getAttribute("idlist");
		List<String> countlist = (ArrayList)session.getAttribute("countlist");
		// 회원아이디 가지고 옴
		String userId = (String)session.getAttribute("userId");

		//총합과 coupon사용여부, 상품들의 아이디와 수량들, 회원아이디 트랜잭션 처리
		//성공적으로 mapper가 완료 했는지 구분하기 위한 값 가지고 옴
		int resultorder = dao.insertorder(pay,coupon,idlist,countlist,userId);

		//주문이 성공했다면 장바구니의 모든 쿠키 삭제
		for(int i=0; i<idlist.size();i++)
		{
			Cookie delcookie = new Cookie("cart"+idlist.get(i), null);
			delcookie.setMaxAge(0);
			delcookie.setPath("/");
			response.addCookie(delcookie);
		}

		//성공적으로 값을 받아왔다면 0, 아니면 1을 가지고 와서 cart페이지에서 구분할 수 있도록 값 보내줌
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:cart.do?resultorder="+resultorder);

        return mv;

		

	}

	

}
