package com.javassem.dao;


import com.javassem.domain.ReceiverVO;
import com.javassem.domain.SenderVO;

public interface TransferDAO {
	
	public void transfer(SenderVO sender, ReceiverVO receiver)  throws Exception;
}
