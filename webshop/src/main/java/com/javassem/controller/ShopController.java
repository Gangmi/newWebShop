package com.javassem.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.ProductVO;
import com.javassem.domain.ShopVO;
import com.javassem.service.ShopService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
public class ShopController {
	
	

	@Autowired
	private ShopService service;

	// 페이지만 넘기는 메소드
	@RequestMapping("/{step}.do")
	public String forwardPage(@PathVariable String step, ModelAndView mo) {

		return step;
	}

	// shop.do요청이 들어왔을 때
	@RequestMapping("/shop.do")
	public ModelAndView viewShop(ModelAndView mv, ProductVO vo) {

	
		
		// 카테고리가 들어오지 않았다면
		String cat=vo.getP_cat();
		System.out.println(vo.getSearch()+"현재검색어-----------------------");
		
		
		
		
		System.out.println(vo.getStartprice()+"컨트롤러 시작가격");
		
		//만약 가격에 대한 검색이 들어오지 않으면
		if(vo.getStartprice()==0) {
			//시작가격과 끝 가격을 지정해줌
			vo.setStartprice(1000);
			vo.setEndprice(25000);
			
			//model에 추가
			mv.addObject("startprice",vo.getStartprice());
			mv.addObject("endprice",vo.getEndprice());
			
			
			
			System.out.println("가격검색 들어오지않아서 기본값으로 설정"+vo.getStartprice()+"다시");
			
			
		}
		
		
		// 만약 페이지가 들어오지 않았다면,
		if (vo.getPage() == null) {
			// 페이지를 1로 지정
			vo.setPage("1");
		}

		// 페이지당 아이템의 갯수가 들어오지 않으면
		if (vo.getItemQuan() == null) {
			//4로 지정
			vo.setItemQuan("4");
		}
		//체크된 브랜드의 정보를 넘기기 위해서 ArrayList선언
		
		
		//브랜드정보가 들어왔다면,
		if(vo.getP_brand()!=null) {
			
			//브랜드 정보를 가져오고
			String brand=vo.getP_brand();
			List<String> brandlist = new ArrayList<String>();
			
			//스트링 토큰나이저로 끊어줌
			StringTokenizer sc = new StringTokenizer(brand,"/");
			int i =0;
			//토큰나이저로 끊는대로 리스트에 저장
			while(sc.hasMoreTokens()) {
				brandlist.add(i, sc.nextToken());
				i++;
				
			}
			
			mv.addObject("brand", brandlist);
			
			mv.addObject("rawbrand",brand);
			
		}
		//정렬기준이 인기순일 경우 vo의 p_date 멤버변수를 이용 , 지금까지의 정보와 함께 인기순으로 정렬한다.
		
		//색이 들어온 경우
		if(vo.getP_color()!=null) {
			
			//mv에 저장
			mv.addObject("selectcolor",vo.getP_color());
			System.out.println(vo.getP_color() +"-----------controler");
			
		}
		
		
		System.out.println(vo.getOrdermethod()+"색을 눌렀을 때 정렬방법");
		

	
		// 해당하는 카테고리의 전체 갯수를 가져와서 몇 페이지를 할 지 결정

		int totalpage = service.getCatTotal(vo);
		
		System.out.println(totalpage + "토탈 페이지-------------" );
		
		//만약 구해온 페이지보다 현재 페이지가 크다면
		if(Integer.parseInt(vo.getPage())>totalpage) {
			//페이지를 1페이지로 만듦
			vo.setPage("1");
		}
		
		// 해당하는 카테고리의 물품들을 db에 요청
		List<ProductVO> result = service.getProductDetail(vo);

		if(result.size()==0) {
			System.out.println("받아온 리스트 없음");
		}
		System.out.println(result.size() + " 컨트롤러에서 다시 받아온 리스트 갯수");
		
		//전체 아이템 갯수를 알기위해 dao에 신호를주기위한 세팅
		vo.setConfirm(1);
		int totalitems = service.getCatTotal(vo);
		
		//전체 아이템 갯수
		mv.addObject("totalitems", totalitems);
		
		// 다음 페이지 지정
		mv.setViewName("shop");

		//총 페이지 지정
		mv.addObject("totalpage",totalpage);
		
		//db에서 꺼내온 데이터들의 이름의 첫 글자를 대문자로 변환
		for(int i=0; i<result.size(); i++) {
			String trans=result.get(i).getP_name().substring(0,1);
			trans=trans.toUpperCase();
			result.get(i).setP_name(trans+result.get(i).getP_name().substring(1));
			
		}
		
		// 다음 페이지에 해당하는 물품들을 전달
		mv.addObject("details", result);
		
		//다음페이지에 검색어가 있음을 알림
		mv.addObject("search",vo.getSearch());
		
		
		//현재 사용자의 카테고리, 페이지 , 페이지당 아이템 갯수의 정보를 저장
		mv.addObject("nowcat",vo.getP_cat()); //default all
		mv.addObject("nowpage",vo.getPage()); //default 1
		mv.addObject("nowquan",vo.getItemQuan()); //default 4
		mv.addObject("startprice",vo.getStartprice());
		mv.addObject("endprice",vo.getEndprice());
		mv.addObject("selectorder",vo.getOrdermethod());
		
		
		
		
		
		return mv;

	}
	
	
	

	// 상품을 클릭해서 해당 상품의 페이지로 넘어갈 때
	@RequestMapping("/product-details.do")
	public ModelAndView getProductDetails(ProductVO vo,HttpSession session) {
		// 해당 상품의 id를 가져와서 서비스로 보냄
		ProductVO result = service.getOneProduct(vo);
		
		ModelAndView model = new ModelAndView();
		
		
	
			String trans=result.getP_name().substring(0,1);
			trans=trans.toUpperCase();
			result.setP_name(trans+result.getP_name().substring(1));
			
		
		
		model.addObject("product", result);
		
		//회원이 로그인한 상태라면 회원의 wishlist 내역을 product-details에 보냄
		String userId = (String)session.getAttribute("userId");
		if(userId!=null)
		{
			List<ProductVO> wish = service.getwish(userId);
			model.addObject("wish", wish);
		}
		
		model.setViewName("product-details");
		return model;
	}

	

}
