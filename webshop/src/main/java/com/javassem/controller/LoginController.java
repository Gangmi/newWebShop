package com.javassem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.LoginVO;
import com.javassem.domain.OrderVO;
import com.javassem.service.LoginService;



@Controller
public class LoginController {

	@Autowired
	private LoginService loginservice;

	////
	// 회원가입
	@RequestMapping("/insertMember.do")
	public ModelAndView insesrtMember(LoginVO vo) {
		//입력받은 회원정보를 db에 넣음
		int result = loginservice.insertMember(vo);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		return mv;
		
	}
	
	@ResponseBody//***********비동기통신이 가능하게 해준다. 화면이 넘어가는 것을 막아줌
	@RequestMapping(value="/idCheck.do",produces="application/text;charset=utf-8")
	public String idcheck(LoginVO vo) {//아이디 하나넘어오지만 vo에 담는다.
		LoginVO result = loginservice.signInMember(vo);//해당하는 아이디가 있으면 넘겨줌
		String message = "ID 사용 가능합니다"	;
		if(result != null)message="중복된 아이디가 있습니다";
		return message;
	}
	
	//로그인
	@RequestMapping("/sign_in.do")
	public String  sign_in(LoginVO vo, HttpSession session,HttpServletRequest request) {

		LoginVO id = loginservice.signInMember(vo);
		ModelAndView mv = new ModelAndView();
		if (id == null || id.getMid() == null) {
			return "/login";
		} else {// 로그인 성공했다면 세션에 로그인한 시간이랑 아이디를 저장
			session.setAttribute("sessionTime", new Date().toLocaleString());
			session.setAttribute("userId", id.getMid());

				System.out.println("로그인성공");
				return "redirect:/index.do";//메인 화면으로 전환		
		}
		
	}
	
	//주문한 목록을 가져옴
	@RequestMapping("/my-order.do")
	public ModelAndView myorder(OrderVO vo, HttpSession session, HttpServletRequest request) {
		vo.setM_id((String)session.getAttribute("userId"));
		List<OrderVO> list = loginservice.myorder(vo);//주문내역
		List<OrderVO> list_sum = loginservice.myorder_sum(vo);//주문번호의 총 가격
		Cookie[] cookies = request.getCookies();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/order-list");
		mv.addObject("orderlist", list);//주문내역 세션에 저장해서 넘김
		mv.addObject("orderlist_sum", list_sum);//주문내역 총합 세션에 저장해서 넘김
		mv.addObject("cookies", cookies);
		return mv;
		
	}
	
	//관리자창
	@RequestMapping("/managerwindow.do")
	public String managerwindow() {
		return "redirect:/dashBoard.do";//관리자창으로 넘김
		
	}
	
	//로그아웃을 눌렀을때
		@RequestMapping("/logout.do")
		public String logout(HttpSession session) {
			//로그아웃을 눌렀을때 세션을 종료한다.
			session. invalidate();			
			return "redirect:/index.do";
		}
		
//	아이디 찾기
	@RequestMapping("/find_Id.do")
	public ModelAndView findId(LoginVO vo) {
		//이름과 전화번오에 맞는 아이디를 가져옴
		LoginVO result = loginservice.findId(vo);
		ModelAndView mv = new ModelAndView();
		if(result == null) {
			mv.setViewName("redirect:/find-id.do");
			return mv;
		}else {
		
		mv.setViewName("/find-id-ok");
		mv.addObject("id", result.getMid());//검색한 아이디를 세션에 저장해서 넘겨서 화면에 찍음
		return mv;
		}
	}

	
	//비밀번호를 찾을때
	@RequestMapping("/find_password.do")
	public ModelAndView findPassword(LoginVO vo) {
		LoginVO result = loginservice.findPassword(vo);//아이디를 보냄 아이디에 맞는 비밀번호를 가져옴
		
		ModelAndView mv = new ModelAndView();
		//이메일이 없다면 다시 현재페이지로 리턴
		if (result == null || result.getMpass() == null) {
			mv.setViewName("/find-password");
	
		}else {//이메일이 있다면 ,저장돼있는 메일로 비밀번호가 담긴 메일을 보냄
		  gmailSend(result);//저장되어있는 메일로 비밀번호 보냄
		  mv.setViewName("/find-password-ok");
		  mv.addObject("email", result.getMemail());//이메일을 넘겨서 화면에 찍어줌
		  
		}
		 return mv;		
	}
	
	//회원정보 가져와서 보여주는 기능
	@RequestMapping("/member-info.do")
	public ModelAndView memberInfo(LoginVO vo,HttpSession session) {
	
		vo.setMid((String)session.getAttribute("userId"));//로그인한 아이디를 가져와서 vo에 저장함
		

		LoginVO result = loginservice.memberInfo(vo); //로그인한 아이디에 맞는 회원정보를 불러옴
		
		//회원정보를 넘김
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/member-info");
		mv.addObject("mid", result.getMid());
		mv.addObject("mpass", result.getMpass());
		mv.addObject("mtel", result.getMtel());
		mv.addObject("memail", result.getMemail());
		mv.addObject("maddr", result.getMaddr());
		mv.addObject("mpostCode", result.getMpostCode());
		mv.addObject("maddrDetail", result.getMaddrDetail());
		mv.addObject("mname", result.getMname());
		return mv;
	}
	
	//회원정보 수정
	@RequestMapping("/update_Member.do")
	public ModelAndView  updateMember(LoginVO vo) {
		//회원정보를 수정함
		int result = loginservice.updateMember(vo);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");//메인 화면으로 리턴
		return mv;
		
	}
	
	//회원탈퇴
	@RequestMapping("/deleteMember.do")
	public ModelAndView deleteMember(LoginVO vo,HttpSession session) {
		vo.setMid((String)session.getAttribute("userId")); //로그인한 아이디를 가져와 vo에 저장
		int result = loginservice.deleteMember(vo);//아이디가 맞는 개인정보를 삭제함
		//회원탈퇴을 눌렀을때 세션을 종료한다.
		session. invalidate();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");//메인 화면으로 리턴
		return mv;
	}
	
	
	//구독
		@RequestMapping(value="/subemail.do",produces="application/text;charset=utf-8")
		@ResponseBody
		public String subemail(LoginVO vo,HttpSession session) throws Exception {
			String message = null;
			if(session.getAttribute("userId")!=null) {		
				vo.setMid((String)session.getAttribute("userId")); 
				LoginVO result =loginservice.checkcoupon(vo);
				
				//쿠폰테이블에서 구독여부 확인
				//이미 구독했다면 이미 구독했다고 나오게함
				if(result.getMsub().equals("O")) {
					message = "이미 구독 하셨습니다.";
					return message;	
					
				}else{
					loginservice.subemail(vo);
					message = "구독되었습니다.";			
					return message;	
				}
			}else {//로그인을 안했다면 나오는 경고창
				message = "로그인 후 사용 가능합니다.";
			}
			return message;				
		}
	
	
	

	//이메일 보내는 기능
	public static void gmailSend(LoginVO vo) {
        String user = "cozinessimh@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = "coziness1!";   // 패스워드

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); 
        prop.put("mail.smtp.port", 465); 
        prop.put("mail.smtp.auth", "true"); 
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(vo.getMemail())); 

            // Subject
            message.setSubject("Coziness 비밀번호"); //메일 제목을 입력

            // Text
            message.setText("Coziness 비밀번호찾기 결과입니다.\n=================================================\n"+vo.getMid()+" 님의 비밀번호는 '"+vo.getMpass()+"'입니다 .\n"+"=================================================\n");//메일 내용을 입력
              
           
            // send the message
            Transport.send(message); ////전송
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
