package com.javassem.dao;

import java.util.ArrayList;

public interface TransferDAO {
	
	public void transfer(int subtotal)  throws Exception;
	public void insertorder(String pay,ArrayList<String> idlist, ArrayList<String> countlist, String userId)throws Exception;
		
	
}
