package com.javassem.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javassem.domain.ProductVO;
import com.javassem.service.CartService;
import com.javassem.service.ShopService;

@Controller
public class CartController {
	@Autowired	
	private CartService service;
	
	@RequestMapping("/product-details.do")
	public void getShopList(String id, Model model)
	{
		model.addAttribute("id",id);
		
	}
	
	  @RequestMapping("/cart.do") 
	  public String cart(String id,String quantity, HttpServletResponse response) {
/*	  model.addAttribute("id",id);
	  model.addAttribute("quantity",quantity);*/
		  ProductVO vo = new ProductVO();
		  vo.setP_id(Integer.parseInt(id));
	  
		  ProductVO list = service.getShop(vo);
		  
		  System.out.println(list.getP_cat());


		  
	  Cookie cookie = new Cookie("cartidtest", id);
	  cookie.setPath("/");
	  cookie.setMaxAge(60*60*24*7);
	  response.addCookie(cookie);
	  
	  return "cart";
  
	  }
	  
	  @RequestMapping("/wishlist.do")
	  public void wishlist(String id, Model model)
	  {
		  model.addAttribute("id",id);
	  }
	 

}
