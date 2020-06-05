package com.javassem.controller;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.DeliveryVO;
import com.javassem.domain.MemberVO;
import com.javassem.domain.ProductVO;
import com.javassem.service.AdminService;

@Controller
public class AdminController {
	
	ProductVO vo;
	@Autowired
	private AdminService service;

	// 대시보드
	@RequestMapping("/dashBoard.do")
	public String dashBoard() {
		return "admin/dashboard";
	}
	
	// 회원 삭제
	@RequestMapping("/memberDelete.do")
	public ModelAndView memberDelete(MemberVO vo) {
		System.out.println("delete 컨트롤 도착");
		int result = service.memberDelete(vo);
		System.out.println("디비 갔다옴");
		ModelAndView mv = new ModelAndView();
		System.out.println("mv 객체 생성");
		mv.setViewName("admin/memberManagement");
		mv.addObject("result",result);
		System.out.println(" list 저장");
		
		return mv;
	}
	
//	@RequestMapping("/charts.do")
//	public String charts() {
//		return "admin/charts";
//	}
	
	// 차트
	@RequestMapping("/charts.do")
	public ModelAndView charts() {
		System.out.println("차트 컨트롤 도착");
		int[] category = service.salesCategory();
		int[] month = service.salesMonth();
		System.out.println("디비 갔다옴 차트");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/charts");
		mv.addObject("month", month);
		mv.addObject("category", category);
		
		return mv;
	}
	
	// 회원 관리
	@RequestMapping("/memberManagement.do")
	public ModelAndView memberManagement(MemberVO vo) {
		System.out.println("컨트롤 도착");
		List<MemberVO> listVO = service.selectMember(vo);
		System.out.println("디비 갔다옴");
		ModelAndView mv = new ModelAndView();
		System.out.println("mv 객체 생성");
		mv.setViewName("admin/memberManagement");
		mv.addObject("listVO",listVO);
		System.out.println(" list 저장");
		return mv;
	}

	// 사원 관리
	@RequestMapping("/employeeManagement.do")
	public ModelAndView employeeManagement (MemberVO vo) {
		System.out.println("컨트롤 도착");
		List<MemberVO> listVO = service.selectEmployee(vo);
		System.out.println("디비 갔다옴");
		ModelAndView mv = new ModelAndView();
		System.out.println("mv 객체 생성");
		mv.setViewName("admin/employeeManagement");
		mv.addObject("listVO",listVO);
		System.out.println(" list 저장");
		return mv;
	}

	// 재고 검색
	@RequestMapping("/inventorySituation.do")
	public ModelAndView inventorySituation(ProductVO vo) {
		System.out.println("inventorySituation 컨트롤 도착");
		List<ProductVO> listVO = service.selectProduct(vo);
		System.out.println("디비 갔다옴");
		ModelAndView mv = new ModelAndView();
		System.out.println("mv 객체 생성");
		mv.setViewName("admin/inventorySituation");
		mv.addObject("listVO",listVO);
		System.out.println(" list 저장");
		return mv;
	}

	// 배송 현황
	@RequestMapping("/deliverySituation.do")
	public ModelAndView deliverySituation(DeliveryVO vo) {
		System.out.println("컨트롤 도착");
		List<DeliveryVO> listVO = service.selectDelivery(vo);
		System.out.println("디비 갔다옴");
		ModelAndView mv = new ModelAndView();
		System.out.println("mv 객체 생성");
		mv.setViewName("admin/deliverySituation");
		mv.addObject("listVO",listVO);
		System.out.println(" list 저장");
		return mv;
	}

	// 컨설팅 예약 관리
	@RequestMapping("/consultingReservation.do")
	public String consultingReservation() {
		return "admin/consultingReservation";
	}

	// 재고 현황 - insert
	@RequestMapping(value = "/inventoryInsert.do")
	public String inventoryInsert(ProductVO vo, MultipartHttpServletRequest multi) {
		System.out.println("inventoryInsert 컨트롤 도착");
		service.inventoryInsert(vo);
		System.out.println("디비 갔다옴2222");
		this.vo = vo;
		MultifileUp(multi);
		
		return "redirect:/inventorySituation.do";
	}

	// 재고 현황 - update
	@RequestMapping(value = "/inventoryUpdate.do")
	public String inventoryUpdate(ProductVO vo, MultipartHttpServletRequest multi) {
		System.out.println("inventoryUpdate 컨트롤 도착");
		service.inventoryUpdate(vo);
		System.out.println("디비 갔다옴11111111111");
		this.vo = vo;
		System.out.println("vo에 저장");
		
		MultifileUp(multi);
		
		return "redirect:/inventorySituation.do";
	}

	// ajaxUpload
	@RequestMapping(value="/ajaxUpload.do",method=RequestMethod.GET)
	public void ajaxUpload() {
		System.out.println("ajax 옴");
	}
	
	// 파일 업로드
	@RequestMapping(value= "/MultiUpload.do", method=RequestMethod.POST)
	public String MultifileUp(MultipartHttpServletRequest multi) {
System.out.println("파일 업로드 실행");
		String path = "D:\\springwork\\webshop\\src\\main\\webapp\\resources\\img\\new imgs\\";
		String fileName = ""; // 업로드 되는 파일명

		File dir = new File(path);
		if(!dir.isDirectory()) {
			dir.mkdir();
		}
		Iterator<String> files = multi.getFileNames();
		MultipartFile mpf = multi.getFile(files.next());

		if(mpf == null || mpf.getSize() <= 0) {
			System.out.println("파일용랑 x");
			return "fileSubmit";
		}

		List<MultipartFile> fileList = multi.getFiles("file");
		int i=0;
		for(MultipartFile filePart : fileList) {
			fileName = filePart.getOriginalFilename(); // 원본 파일명
			
			System.out.println("실제  파일 이름 : " + fileName);
			
			i++;
			
			fileName = vo.getP_cat()+vo.getP_id()+"_"+i+".jpg";
		    
			long fileSize = filePart.getSize(); // 파일 사이즈

			if(!fileName.equals("")) { // 파일 쓰기
				try {
					FileOutputStream fs = new FileOutputStream(path + fileName);
					fs.write(filePart.getBytes());
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("파일 업로드 끝");
		return "redirect:/inventorySituation.do";
	}


	
	
	
	
	
	
	


}
