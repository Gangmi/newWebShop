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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.CouponVO;
import com.javassem.domain.LoginVO;
import com.javassem.domain.ProductVO;
import com.javassem.service.CartService;


@Controller
public class CartController {
	@Autowired	
	private CartService service;
	
//	@ExceptionHandler(RuntimeException.class)
//	public String exceptionHandler(Model model, Exception e){
//	model.addAttribute("exception", e);
//	return "login";
//
//	}
	
	@RequestMapping("/cartadd.do")
	public int cartadd(String p_id,String p_quan,HttpServletResponse response)
	{
		int result=0;
		if(Integer.parseInt(p_quan)>0)
		{
		Cookie cookie = new Cookie("cart"+p_id, p_id);

		cookie.setPath("/");
		cookie.setMaxAge(60*60*24*7);
		response.addCookie(cookie);
		result=1;
		}
		return result;
	}



	@RequestMapping("/cart.do") 
	public ModelAndView cart(String resultorder, String delstr,String p_id,HttpServletResponse response, HttpServletRequest request) {

		
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
			try {
				response.sendRedirect("cart.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(delstr!=null)
		{
			String[] array = delstr.split(",");

			//출력				
			for(int i=0;i<array.length;i++) {

				Cookie delcookie = new Cookie("cart"+array[i], null);
				delcookie.setMaxAge(0);
				delcookie.setPath("/");
				
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

		if(resultorder!=null)
		{
			if(Integer.parseInt(resultorder)>0)
			{
			mv.addObject("resultorder", resultorder);
			}
		}

		return mv;

	}
	
	@RequestMapping(value="/checkout.do")
	public ModelAndView checkout(String count, String id, String subtotal,HttpServletResponse response, HttpServletRequest request,HttpSession session)
	{	
		
		

		String[] countarray = count.split(",");
		String[] idarray = id.split(",");
		ArrayList<String> countlist = new ArrayList<String>();
		ArrayList<String> idlist = new ArrayList<String>();
		for(int i=0; i<countarray.length;i++)
		{
			countlist.add(countarray[i]);
			idlist.add(idarray[i]);
		}
	     session.setAttribute("countlist",countlist);
	     session.setAttribute("idlist",idlist);
		String userId = (String)session.getAttribute("userId");
		System.out.println("****************id값"+userId);
		ModelAndView mv=new ModelAndView();
		if(userId==null)
		{
			mv.setViewName("redirect:login.do");
			return mv;
		}
		else {
		LoginVO vo = new LoginVO();
		vo.setMid(userId);
		LoginVO result = service.getmemberInfo(vo);
		CouponVO couvo = service.getmembercoupon(vo);
		
		mv.setViewName("checkout");
		mv.addObject("result",result);
		if(couvo!=null)
		{
			mv.addObject("couvo",couvo);
		}
		
		mv.addObject("subtotal",subtotal);
		return mv;}
	}
	
	



	@RequestMapping("/wishlist.do")
	public ModelAndView wishlist(String delstr,String p_id,HttpSession session)
	{
		String userId = (String)session.getAttribute("userId");
		ModelAndView mvid=new ModelAndView();
		if(userId==null)
		{
			mvid.setViewName("redirect:login.do");
			return mvid;
		}
		if(delstr!=null)
		{
			String[] array = delstr.split(",");
			service.deleteWishlist(array,userId);	
		}
		
		if(p_id!=null)
		{
		service.insertWishlist(p_id,userId);
		}
		List<ProductVO> list = service.selectWishlist(userId);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("wishlist");
		mv.addObject("list",list);

		return mv;
		
	}
	
	@RequestMapping("/wishtocart.do")
	public void wishtocart(String id,HttpServletResponse response, HttpServletRequest request)
	{
		if(id!= null)
		{
			String[] idarray = id.split(",");
			for(int i=0; i<idarray.length;i++)
			{
				System.out.println(idarray[i]);
				Cookie cookie = new Cookie("cart"+idarray[i], idarray[i]);
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);


			}
			try {
				response.sendRedirect("cart.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

}
