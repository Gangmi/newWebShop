package com.javassem.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
	
		
		@RequestMapping("/{step}.do")
		public String forwardPage(@PathVariable String step ,ModelAndView mo)
		{
		
			return step;
		}
		
		@RequestMapping("/shop.do")
		public ModelAndView viewShop (ModelAndView mv,ProductVO vo) {

			String page="1"; 
			if(vo.getP_cat()==null) {
				vo.setP_cat("chair");
				
			}
			vo.setPage(page);
			List<ProductVO> result =service.getProductDetail(vo);
			mv.setViewName("shop");
			mv.addObject("cat",vo.getP_cat());
			mv.addObject("page",vo.getPage());
			mv.addObject("details",result);
			return mv;
			
			
		}
		
		
	


}
