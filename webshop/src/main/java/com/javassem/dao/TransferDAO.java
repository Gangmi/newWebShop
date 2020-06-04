package com.javassem.dao;

import java.util.ArrayList;
import java.util.List;

public interface TransferDAO {
	
	public void transfer(int subtotal)  throws Exception;
	public void insertorder(String pay,List<String> idlist, List<String> countlist, String userId)throws Exception;
		
	
}
