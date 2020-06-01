package com.javassem.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.ProductVO;
import com.javassem.domain.ShopVO;
import com.javassem.service.ShopService;

@Controller
public class ShopController {

	@Autowired
	private ShopService service;

	// 페이지만 넘기는 메소드
	@RequestMapping("/{step}.do")
	public String forwardPage(@PathVariable String step, ModelAndView mo) {

		return step;
	}

	//shop.do요청이 들어왔을 때
	@RequestMapping("/shop.do")
	public ModelAndView viewShop(ModelAndView mv, ProductVO vo) {

		String page = "1";
		if (vo.getP_cat() == null) {
			vo.setP_cat("chair");

		}
		vo.setPage(page);
		List<ProductVO> result = service.getProductDetail(vo);
		mv.setViewName("shop");
		mv.addObject("cat", vo.getP_cat());
		mv.addObject("page", vo.getPage());
		mv.addObject("details", result);
		return mv;

	}

	//상품을 클릭해서 해당 상품의 페이지로 넘어갈 때
	@RequestMapping("/product-details.do")
	public ModelAndView getProductDetails(ProductVO vo) {
		//해당 상품의 id를 가져와서 서비스로 보냄
		ProductVO result =service.getOneProduct(vo);
		ModelAndView model = new ModelAndView();
		model.addObject("product", result);
		model.setViewName("product-details");
		return model;
	}


}
