package com.javassem.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.javassem.dao.VisitCountDAO;

public class SessionController implements HttpSessionListener {
	


    @Override
    public void sessionCreated(HttpSessionEvent arg0) {

    	System.out.println("세션리스너 찾음");
    	
        // DAO 객체 생성
        VisitCountDAO dao = new VisitCountDAO();
         
        // 전체 방문자 수 +1
        dao.setTotalCount();
         
//        // 오늘 방문자 수
//        int todayCount = dao.getVisitTodayCount();
//         
//        // 전체 방문자 수
//        int totalCount = dao.getVisitTotalCount();
//         
//        HttpSession session = arg0.getSession();
//         
//        // 세션 속성에 담아준다.
//        session.setAttribute("totalCount", totalCount); // 전체 방문자 수
//        session.setAttribute("todayCount", todayCount); // 오늘 방문자 수
         
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
 
    }
}
