package com.javassem.controller;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.LoginVO;
import com.javassem.domain.ProductVO;
import com.javassem.service.CartService;


@Controller
public class CartController {
	@Autowired	
	private CartService service;


	@RequestMapping("/cart.do") 
	public ModelAndView cart(String delstr,String p_id,HttpServletResponse response, HttpServletRequest request) {

		List<ProductVO> seq = new ArrayList<ProductVO>();
		if(!(p_id==null))
		{
			Cookie cookie = new Cookie("cart"+p_id, p_id);

			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
			ProductVO vo = new ProductVO();
			vo.setP_id(Integer.parseInt(p_id));
			seq.add(vo);
		}
		if(delstr!=null)
		{
			String[] array = delstr.split(",");

			//출력				
			for(int i=0;i<array.length;i++) {

				Cookie delcookie = new Cookie("cart"+array[i], null);
				delcookie.setMaxAge(0);
				delcookie.setPath("/");
				System.out.println("cart"+array[i]);
				
				response.addCookie(delcookie);
				
			}
			try {
				response.sendRedirect("cart.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Cookie[] cookies = request.getCookies(); //request로 받고
		List<ProductVO> list =new ArrayList<ProductVO>();
		int total=0;
		if(cookies != null&&cookies.length>1) //null이 아니면 
		{
			
			for(int i=1; i<cookies.length; i++) //모든 쿠키 출력
			{
				ProductVO vo = new ProductVO();
				vo.setP_id(Integer.parseInt(cookies[i].getValue()));
				seq.add(vo);

			} //cookieN에 대한 정보가 다 사라져있어야 함
			list = service.getShopList(seq);

			//System.out.println(list.getP_cat());

			
			for(int i=0; i<list.size();i++)
			{
				ProductVO result = new ProductVO();
				result = list.get(i);
				total+= result.getP_price();
			}
			
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("cart");
		mv.addObject("list",list);
		mv.addObject("total", total);

		return mv;

	}
	
	@RequestMapping(value="/checkout.do")
	public ModelAndView checkout(String subtotal,HttpServletResponse response, HttpServletRequest request,HttpSession session)
	{	
		String userId = (String)session.getAttribute("userId");

		LoginVO vo = new LoginVO();
		vo.setMid(userId);
		LoginVO result = service.getmemberInfo(vo);

		ModelAndView mv=new ModelAndView();
		mv.setViewName("checkout");
		mv.addObject("result",result);
		mv.addObject("subtotal",subtotal);
		return mv;
	}



	@RequestMapping("/wishlist.do")
	public void wishlist(String id, Model model)
	{
		model.addAttribute("id",id);
	}

}
