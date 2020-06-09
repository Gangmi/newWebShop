package com.javassem.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javassem.domain.BoardVO;

@Repository
public class BoardDAOImple {
	
	@Autowired
	SqlSessionTemplate mybatis;
	
	public void addComment(BoardVO vo) {
		
		mybatis.insert("BoardDAO.addComment",vo);
	}
	
	public List<BoardVO>selectBoard(BoardVO vo){
		
		return mybatis.selectList("BoardDAO.selectBoard",vo);
	}	

}
