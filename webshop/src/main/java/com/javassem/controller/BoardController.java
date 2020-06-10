package com.javassem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javassem.dao.BoardDAOImple;
import com.javassem.domain.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardDAOImple dao ;
	
	//댓글 받기
	 @RequestMapping(value="/commentList.do"  ,produces = "application/text; charset=UTF-8")
	 @ResponseBody
	 public String getCommentList(BoardVO vo) {
		 
		 List<BoardVO> commentVO = dao.selectBoard(vo);
		 ArrayList<HashMap> hmlist = new ArrayList<HashMap>();   
	        if(commentVO.size() > 0){
	            for(int i=0; i<commentVO.size(); i++){
	                HashMap hm = new HashMap();
	                hm.put("com_id", commentVO.get(i).getCom_id());
	                hm.put("commentary", commentVO.get(i).getCommentary());
	                hm.put("m_id", commentVO.get(i).getM_id());
	                hm.put("com_date", commentVO.get(i).getCom_date());
	                
	                hmlist.add(hm);
	            }
	            
	        }
	        JSONArray json = new JSONArray(hmlist);
	       
		 
		 System.out.println( json.toString());
		 return json.toString();
	 }
	 
	 //댓글입력
	 @RequestMapping(value="/addComment.do")
	 @ResponseBody
	 public String addCommentList(BoardVO vo) {
	
		dao.addComment(vo);
		
		
		
	
		 return "";
	 }

}
