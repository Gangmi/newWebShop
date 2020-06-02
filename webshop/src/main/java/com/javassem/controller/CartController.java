package com.javassem.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.ProductVO;
import com.javassem.service.CartService;
import com.javassem.service.ShopService;

@Controller
public class CartController {
	@Autowired	
	private CartService service;

	
	  @RequestMapping("/cart.do") 
	  public ModelAndView cart(String p_id,String quantity, HttpServletResponse response, HttpServletRequest request) {

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

	  Cookie[] cookies = request.getCookies(); //request로 받고
		if(cookies != null) //null이 아니면 
		{
			for(int i=1; i<cookies.length; i++) //모든 쿠키 출력
			{
				ProductVO vo = new ProductVO();
				vo.setP_id(Integer.parseInt(cookies[i].getValue()));
				seq.add(vo);
				
			} //cookieN에 대한 정보가 다 사라져있어야 함
		}
	  

	  List<ProductVO> list = service.getShopList(seq);
	  
	  //System.out.println(list.getP_cat());

	  for(int i=0; i<list.size();i++)
	  {
		  ProductVO result = new ProductVO();
		  result = list.get(i);

	  }
	  
	  ModelAndView mv = new ModelAndView();
	  mv.setViewName("cart");
	  mv.addObject("list",list);
	  
	  return mv;
  
	  }
	  
	  @RequestMapping("/wishlist.do")
	  public void wishlist(String id, Model model)
	  {
		  model.addAttribute("id",id);
	  }
	 

}
