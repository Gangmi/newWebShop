package com.javassem.dao;


public interface TransferDAO {
	
	public void transfer(String sender, String receiver)  throws Exception;
}
