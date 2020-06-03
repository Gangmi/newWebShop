package com.javassem.controller;

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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	//
	@RequestMapping("/sign_in.do")
	public ModelAndView sign_in(LoginVO vo, HttpSession session) {

		LoginVO id = loginservice.signInMember(vo);

		ModelAndView mv = new ModelAndView();
		if (id == null || id.getMid() == null) {
			mv.setViewName("/login");
		} else {// 로그인 성공했다면
			session.setAttribute("sessionTime", new Date().toLocaleString());
			session.setAttribute("userId", id.getMid());
			mv.setViewName("/index");
		}
		return mv;
	}

	@RequestMapping("/find_Id.do")
	public ModelAndView findId(LoginVO vo) {
		System.out.println(vo.getMname());
		LoginVO result = loginservice.findId(vo);
		System.out.println(result.getMid());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/find-id-ok");
		mv.addObject("id", result.getMid());
		return mv;
	}

	@RequestMapping("/find_password.do")
	public ModelAndView findPassword(LoginVO vo) {
		LoginVO result = loginservice.findPassword(vo);
		
	
		  ModelAndView mv = new ModelAndView();
		  gmailSend(result);
		  mv.setViewName("/find-password-ok");
		 return mv;		
	}

	
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
            message.setSubject("안녕하세요"); //메일 제목을 입력

            // Text
            message.setText("비밀번호는 ' "+vo.getMpass()+" '입니다 .");    //메일 내용을 입력

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
