package com.javassem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javassem.domain.LoginVO;

import com.javassem.service.LoginService;



@Controller
public class LoginController {

	@Autowired
	private LoginService loginservice;

	////
	// 회원가입
	@RequestMapping("/insertMember.do")
	public ModelAndView insesrtMember(LoginVO vo) {
		int result = loginservice.insertMember(vo);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		return mv;
		// 회원가입 완료되면 완료됬다는 창으로 보내주기 그리고 거기서 다시 버튼만들어서 다시 메인창으로
		//
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
	public String sign_in(LoginVO vo, HttpSession session) {

		LoginVO id = loginservice.signInMember(vo);

		ModelAndView mv = new ModelAndView();
		if (id == null || id.getMid() == null) {
			return "/login";
		} else {// 로그인 성공했다면
			session.setAttribute("sessionTime", new Date().toLocaleString());
			session.setAttribute("userId", id.getMid());
			if(id.getMid().equals("admin")) {
				System.out.println("성공");
				return "redirect:/dashBoard.do";
			}else {
				System.out.println("실패");
				return "/index";			
			}
		}
		
	}
	
	//로그아웃을 눌렀을때
		@RequestMapping("/logout.do")
		public ModelAndView logout(HttpSession session) {
			//로그아웃을 눌렀을때 세션을 종료한다.
			session. invalidate();
			//처음화면으로 돌려버림
			ModelAndView mv = new ModelAndView();
			mv.setViewName("/index");
			return mv;
		}
		
//	아이디 찾기
	@RequestMapping("/find_Id.do")
	public ModelAndView findId(LoginVO vo) {

		LoginVO result = loginservice.findId(vo);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/find-id-ok");
		mv.addObject("id", result.getMid());
		return mv;
	}

	
	//비밀번호를 찾을때
	@RequestMapping("/find_password.do")
	public ModelAndView findPassword(LoginVO vo) {
		LoginVO result = loginservice.findPassword(vo);
		
		ModelAndView mv = new ModelAndView();
		//이메일이 없다면 다시 현재페이지로 리턴
		if (result == null || result.getMpass() == null) {
			mv.setViewName("/find-password");
	
		}else {//이메일이 있다면 ,저장돼있는 메일로 비밀번호가 담긴 메일을 보냄
		  gmailSend(result);
		  mv.setViewName("/find-password-ok");
		  mv.addObject("email", result.getMemail());
		  
		}
		 return mv;		
	}
	
	//회원정보 가져와서 보여주는 기능
	@RequestMapping("/member-info.do")
	public ModelAndView memberInfo(LoginVO vo,HttpSession session) {
	
		vo.setMid((String)session.getAttribute("userId")); 
		

		LoginVO result = loginservice.memberInfo(vo);
		
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
	
	@RequestMapping("/update_Member.do")
	public ModelAndView  updateMember(LoginVO vo) {
		int result = loginservice.updateMember(vo);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		return mv;
		
	}
	
	//회원탈퇴
	@RequestMapping("/deleteMember.do")
	public ModelAndView deleteMember(LoginVO vo,HttpSession session) {
		vo.setMid((String)session.getAttribute("userId")); 
		int result = loginservice.deleteMember(vo);
		//회원탈퇴을 눌렀을때 세션을 종료한다.
		session. invalidate();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		return mv;
	}
	
	@RequestMapping("/subemail.do")
	public ModelAndView subemail(LoginVO vo,HttpSession session,HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		response.setContentType("text/html; charset=UTF-8");
		 
		PrintWriter out = response.getWriter();		
		if(session.getAttribute("userId")!=null) {		
			vo.setMid((String)session.getAttribute("userId")); 
			int result = loginservice.subemail(vo);
			
			out.println("<script>alert('구독되었습니다.') </script>");			 
			out.flush();
			
			mv.setViewName("/index");
			return mv;
		}else {
			out.println("<script>alert('로그인 후 이용 가능합니다.')</script>");
			out.flush();
			mv.setViewName("/index");
			return mv;
		}
		
			
	}
	
	
	

	//이메일 보내는 기능
	public static void gmailSend(LoginVO vo) {
        String user = "junpublic97@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = "Wjdwnsrjatk97!";   // 패스워드

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
            message.setSubject("정준주식회사 비밀번호찾기"); //메일 제목을 입력

            // Text
            message.setText("=================================================\n"+vo.getMid()+" 님의 비밀번호는 '"+vo.getMpass()+"'입니다 .\n"+"=================================================\n");//메일 내용을 입력
              
           
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
