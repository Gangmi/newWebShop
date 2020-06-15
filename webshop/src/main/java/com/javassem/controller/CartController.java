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
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	// shop에서 장바구니 버튼 눌렀을때
	@ResponseBody
	@RequestMapping(value="/cartadd.do",method=RequestMethod.POST,produces="applicaton/text; charset=UTF-8")
	public String cartadd(String p_id,String p_quan,HttpServletResponse response)
	{
		// 재고량에 따른 alert창 글귀
		String result="재고가 없습니다~ㅜㅜ 다른 상품 살펴주세요~";
		if(Integer.parseInt(p_quan)>0)
		{
		Cookie cookie = new Cookie("cart"+p_id, p_id);
		//cookie로 장바구니 입력
		cookie.setPath("/");
		//7일동안 사용가능
		cookie.setMaxAge(60*60*24*7);
		response.addCookie(cookie);
		result="장바구니 담기 성공!";
		}
		return result;
	}



	//장바구니
	@RequestMapping("/cart.do") 
	public ModelAndView cart(String resultorder, String delstr,String p_id,HttpServletResponse response, HttpServletRequest request) {

		List<ProductVO> seq = new ArrayList<ProductVO>();
		// add to cart 시 상품의 아이디를 가지고 있을 경우
		if(!(p_id==null))
		{
			//cookie로 장바구니 입력
			Cookie cookie = new Cookie("cart"+p_id, p_id);
			
			cookie.setPath("/");
			//7일동안 사용가능
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
			ProductVO vo = new ProductVO();
			//새로 추가된 장바구니 상품 리스트에 추가하여 디비에서 화면에 출력할 상품아이디 입력
			vo.setP_id(Integer.parseInt(p_id));
			seq.add(vo);
			try {
				response.sendRedirect("cart.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//장바구니 삭제시
		if(delstr!=null)
		{
			//장바구니 삭제 상품아이디 배열에 담아 가지고 옴
			String[] array = delstr.split(",");

			//출력				
			for(int i=0;i<array.length;i++) {

				//쿠키에서 장바구니 목록 삭제
				Cookie delcookie = new Cookie("cart"+array[i], null);
				//쿠키 만료
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
		
		//장바구니 출력화면 보여주기 위한 쿠키 값들 가져오기
		Cookie[] cookies = request.getCookies(); //request로 받고
		List<ProductVO> list =new ArrayList<ProductVO>();
		int total=0;
		if(cookies != null&&cookies.length>1) //null이 아니면 
		{
			
			for(int i=1; i<cookies.length; i++) //모든 쿠키 출력
			{
				//장바구니 화면에 출력할 정보들 가져오기 위한 상품아이디들 리스트에 입력
				ProductVO vo = new ProductVO();
				vo.setP_id(Integer.parseInt(cookies[i].getValue()));
				seq.add(vo);

			} //cookieN에 대한 정보가 다 사라져있어야 함
			//mapper에서 상품 정보를 디비에서 select
			list = service.getShopList(seq);

			//System.out.println(list.getP_cat());

			//가져온 상품정보 중 가격을 가지고 장바구니에 총합계 표시하기 위한 for문
			for(int i=0; i<list.size();i++)
			{
				ProductVO result = new ProductVO();
				result = list.get(i);
				total+= result.getP_price();
			}
			
		}
		//cart.jsp 에 가지고 갈 정보들 입력
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cart");
		mv.addObject("list",list);
		mv.addObject("total", total);

		//만약 cart에서 checkout시 성공을 한다면 resultorder가 0보다 크게 설정해놓음
		if(resultorder!=null)
		{
			// cart로 전환될때 주문 완료 alert창을 띄우기 위한 구분
			if(Integer.parseInt(resultorder)>0)
			{
			mv.addObject("resultorder", resultorder);
			}
		}

		return mv;

	}
	//cart에서 checkout했을 때 실행하는 컨트롤러
	@RequestMapping(value="/checkout.do")
	public ModelAndView checkout(String count, String id, String subtotal,HttpServletResponse response, HttpServletRequest request,HttpSession session)
	{	
		//카트에서 수량과 상품아이디들을 가지고 옴
		String[] countarray = count.split(",");
		String[] idarray = id.split(",");
		ArrayList<String> countlist = new ArrayList<String>();
		ArrayList<String> idlist = new ArrayList<String>();
		for(int i=0; i<countarray.length;i++)
		{
			countlist.add(countarray[i]);
			idlist.add(idarray[i]);
		}
		// 세션에 수량과 상품아이디를 저장시킴
	     session.setAttribute("countlist",countlist);
	     session.setAttribute("idlist",idlist);
	     
	     // 만약 세션에 로그인 아이디가 없다면
		String userId = (String)session.getAttribute("userId");
		ModelAndView mv=new ModelAndView();
		if(userId==null)
		{
			//login창으로 전환시킴
			mv.setViewName("redirect:login.do");
			return mv;
		}
		else {
			//로그인이 되어 있다면 
		LoginVO vo = new LoginVO();
		vo.setMid(userId);
		//mapper에서 로그인 아이디를 가지고 있는 회원 정보를 가지고 옴
		LoginVO result = service.getmemberInfo(vo);
		//mapper에서 로그인 아이디를 가지고 있는 상품정보를 가지고 옴
		CouponVO couvo = service.getmembercoupon(vo);
		
		//checkout 페이지 전환 하면서 상품들의 정보를 가지고 감
		mv.setViewName("checkout");
		mv.addObject("result",result);
		if(couvo!=null)
		{
			//만약 쿠폰이 있다면 쿠폰의 정보도 넘김
			mv.addObject("couvo",couvo);
		}
		
		mv.addObject("subtotal",subtotal);
		return mv;}
	}
	// wishlist 
	@RequestMapping("/wishlist.do")
	public ModelAndView wishlist(String delstr,String p_id,HttpSession session)
	{
		String userId = (String)session.getAttribute("userId");
		ModelAndView mvid=new ModelAndView();
		// session에 등록된 아이디가 없다면 로그인 페이지 이동하여 로그인 후 wishlist 사용하도록 유도
		if(userId==null)
		{
			mvid.setViewName("redirect:login.do");
			return mvid;
		}
		// 삭제할 상품아이디들을 받아서 wishlist db 내역 삭제
		if(delstr!=null)
		{
			String[] array = delstr.split(",");
			service.deleteWishlist(array,userId);	
		}
		// wishlist 에 담을 상품아이디가 있다면 
		if(p_id!=null)
		{
			//wishlist 상품을 db에 추가
		service.insertWishlist(p_id,userId);
		}
		//db에 있는 list 페이지에 띄우기
		List<ProductVO> list = service.selectWishlist(userId);
		
		//list 담아서 wishlist.jsp페이지 이동
		ModelAndView mv = new ModelAndView();
		mv.setViewName("wishlist");
		mv.addObject("list",list);

		return mv;
		
	}
	
	//wishlist에 있는 목록 중 선택한 상품 아이디 cart로 보내기
	@RequestMapping("/wishtocart.do")
	public void wishtocart(String id,HttpServletResponse response, HttpServletRequest request)
	{
		// 상품 아이디가 있다면
		if(id!= null)
		{
			String[] idarray = id.split(",");
			for(int i=0; i<idarray.length;i++)
			{
				// 상품들 cookie 생성
				System.out.println(idarray[i]);
				Cookie cookie = new Cookie("cart"+idarray[i], idarray[i]);
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);


			}
			try {
				//cart페이지 보이기
				response.sendRedirect("cart.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

}
