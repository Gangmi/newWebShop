package com.javassem.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
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
		if (vo.getP_cat() == null) {
			// 의자로 기본 카테고리를 지정
			vo.setP_cat("chair");
		

		}
		// 만약 페이지가 들어오지 않았다면,
		if (vo.getPage() == null) {
			// 페이지를 1로 지정
			vo.setPage("1");
		}

		// 페이지당 아이템의 갯수가 들어오지 않으면
		if (vo.getItemQuan() == null) {
			vo.setItemQuan("4");
		}
		//현재 사용자의 카테고리를 지정
		
		System.out.println(vo.getP_cat());
		System.out.println(vo.getPage());
		System.out.println(vo.getItemQuan());
		
	

		// 해당하는 카테고리의 전체 갯수를 가져와서 몇 페이지를 할 지 결정

		int totalpage = service.getCatTotal(vo);
		
		
		// 해당하는 카테고리의 물품들을 db에 요청
		List<ProductVO> result = service.getProductDetail(vo);

		// 다음 페이지 지정
		mv.setViewName("shop");

		mv.addObject("totalpage",totalpage);
		
		// 다음 페이지에 해당하는 물품들을 전달
		mv.addObject("details", result);
		mv.addObject("nowcat",vo.getP_cat());
		mv.addObject("nowpage",vo.getPage());
		mv.addObject("nowquan",vo.getItemQuan());
		return mv;

	}

	// 상품을 클릭해서 해당 상품의 페이지로 넘어갈 때
	@RequestMapping("/product-details.do")
	public ModelAndView getProductDetails(ProductVO vo) {
		// 해당 상품의 id를 가져와서 서비스로 보냄
		ProductVO result = service.getOneProduct(vo);
		ModelAndView model = new ModelAndView();
		model.addObject("product", result);
		model.setViewName("product-details");
		return model;
	}

}
