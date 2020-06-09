package com.javassem.dao;

import java.util.ArrayList;
import java.util.List;

public interface TransferDAO {

	public void insertorder(String pay,String coupon,List<String> idlist, List<String> countlist, String userId)throws Exception;
		
	
}
