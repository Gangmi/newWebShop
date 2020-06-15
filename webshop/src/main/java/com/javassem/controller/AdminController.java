package com.javassem.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.DeliveryVO;
import com.javassem.domain.MemberVO;
import com.javassem.domain.OrderVO;
import com.javassem.domain.ProductVO;
import com.javassem.service.AdminService;

@Controller
public class AdminController {

	ProductVO vo;
	@Autowired
	private AdminService service;

	// 대시보드
	@RequestMapping("/dashBoard.do")
	public ModelAndView dashBoard() {
		// 오늘 주문 건 수
		int result1 = service.orderCount();
		// 오늘 회원가입 수
		int result2 = service.memberCount();
		// 오늘 페이지 뷰 수
		int[] result3 = service.viewCount();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/dashboard");
		mv.addObject("orderCount", result1);
		mv.addObject("memberCount", result2);
		mv.addObject("viewCount", result3);
		return mv;
	}

	// 회원 삭제
	@RequestMapping("/memberDelete.do")
	public ModelAndView memberDelete(MemberVO vo) {
		int result = service.memberDelete(vo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/memberManagement");
		mv.addObject("result", result);
		return mv;
	}

	// 차트
	@RequestMapping("/charts.do")
	public ModelAndView charts() {
		// 카테고리별 매출 차트 데이터 가져오기
		int[] category = service.salesCategory();
		// 월간 매출 차트 데이터 가져오기
		int[] month = service.salesMonth();
		// 최근30일간 매출 차트 데이터 가져오기
		int[] day = service.recentSales();
		// 나이별 매출 데이터 가져오기
		int[] age = service.salesAge();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/charts");
		mv.addObject("month", month);
		mv.addObject("category", category);
		mv.addObject("day", day);
		mv.addObject("age", age);
		return mv;
	}

	// 회원 관리
	@RequestMapping("/memberManagement.do")
	public ModelAndView memberManagement(MemberVO vo) {
		// 회원 리스트 데이터 가져오기
		List<MemberVO> listVO = service.selectMember(vo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/memberManagement");
		mv.addObject("listVO", listVO);
		return mv;
	}

	// 사원 관리
	@RequestMapping("/employeeManagement.do")
	public ModelAndView employeeManagement(MemberVO vo) {
		// 사원 리스트 가져오기
		List<MemberVO> listVO = service.selectEmployee(vo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/employeeManagement");
		mv.addObject("listVO", listVO);
		return mv;
	}

	// 재고 검색
	@RequestMapping("/inventorySituation.do")
	public ModelAndView inventorySituation(ProductVO vo) {
		// 상품 리스트 가져오기
		List<ProductVO> listVO = service.selectProduct(vo);
		int nextval = service.getNextid();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/inventorySituation");
		mv.addObject("listVO", listVO);
		mv.addObject("nextval", nextval);
		return mv;
	}

	// 주문 리스트
	@RequestMapping("/deliverySituation.do")
	public ModelAndView deliverySituation(OrderVO vo) {
		// 주문 리스트 가져오기
		List<OrderVO> listVO = service.selectDelivery(vo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/deliverySituation");
		mv.addObject("listVO", listVO);
		return mv;
	}

	// 컨설팅 예약 관리 페이지 이동
	@RequestMapping("/consultingReservation.do")
	public String consultingReservation() {
		return "admin/consultingReservation";
	}

	// 재고 관리 - insert
	@RequestMapping(value = "/inventoryInsert.do")
	public String inventoryInsert(ProductVO vo, MultipartHttpServletRequest multi) {
		service.inventoryInsert(vo);
		this.vo = vo;
		// insert시 파일 같이 올리면 파일은 MultifileUp() 호출
		MultifileUp(multi);
		return "redirect:/inventorySituation.do";
	}

	// 재고 관리 - update
	@RequestMapping(value = "/inventoryUpdate.do")
	public String inventoryUpdate(ProductVO vo, MultipartHttpServletRequest multi) {
		service.inventoryUpdate(vo);
		this.vo = vo;
		// update시 파일 같이 올리면 파일은 MultifileUp() 호출
		MultifileUp(multi);
		return "redirect:/inventorySituation.do";
	}

	// ajaxUpload
	@RequestMapping(value = "/ajaxUpload.do", method = RequestMethod.GET)
	public void ajaxUpload() {
		System.out.println("ajax 옴");
	}

	// 파일 업로드
	@RequestMapping(value = "/MultiUpload.do", method = RequestMethod.POST)
	public String MultifileUp(MultipartHttpServletRequest multi) {
		// 파일 업로드 경로 지정
		String path = 
		"C:\\Users\\Canon\\Documents\\newWebShop\\webshop\\src\\main\\webapp\\resources\\img\\product-img\\";
		String fileName = ""; // 업로드 되는 파일명
		// 경로에 폴더가 없을 경우 폴더 생성
		File dir = new File(path);
		if (!dir.isDirectory()) {
			dir.mkdir();
		}
		Iterator<String> files = multi.getFileNames();
		MultipartFile mpf = multi.getFile(files.next());
		// 
		if (mpf == null || mpf.getSize() <= 0) {
			System.out.println("파일용랑 x");
			return "fileSubmit";
		}
		List<MultipartFile> fileList = multi.getFiles("file");
		int i = 0;
		for (MultipartFile filePart : fileList) {
			fileName = filePart.getOriginalFilename(); // 원본 파일명
			i++;
			// 변경하려는 파일명 저장 - 파일명을 카테고리명과 상품ID로 맞춰주기 위해 vo에서 가져옴
			fileName = vo.getP_cat() + vo.getP_id() + "_" + i + ".jpg";

			long fileSize = filePart.getSize(); // 파일 사이즈
			// 지정한 경로와 파일명으로 파일 저장
			if (!fileName.equals("")) { // 파일 쓰기
				try {
					FileOutputStream fs = new FileOutputStream(path + fileName);
					fs.write(filePart.getBytes());
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/inventorySituation.do";
	}

	// 페이지 로딩 시 방문자 수 1 db에 올림
	@RequestMapping("/setTotalCount.do")
	public String setTotalCount() {
		service.setTotalCount();
		return "admin/dashboard.do";
	}

	// 배송상태 변경update
	@ResponseBody
	@RequestMapping(value = "/updateDeli.do", produces = "application/text; charset=UTF-8")
	public void updateDeli(OrderVO vo) {
		int result = service.updateDeli(vo);
		if (result > 0) {
			System.out.println("배송상태 변경성공");
		} else {
			System.out.println("배송상태 변경실패");
		}
		return;
	}

}
