package com.javassem.dao;

public class TransException extends Exception {
	public String getMessage(){
		return "주문시 오류가 발생하였습니다";
	}
}
