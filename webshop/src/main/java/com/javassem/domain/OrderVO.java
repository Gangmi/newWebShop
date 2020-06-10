package com.javassem.domain;

public class OrderVO {
	
	private int o_id;
	private String m_id;
	private String o_payment;
	private int ol_id;
	private int p_id;
	private int cnt;
	private String o_date;
	private String o_delivery;
	
	
	
	public String getO_date() {
		return o_date;
	}
	public void setO_date(String o_date) {
		this.o_date = o_date;
	}
	public String getO_delivery() {
		return o_delivery;
	}
	public void setO_delivery(String o_delivery) {
		this.o_delivery = o_delivery;
	}
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getO_payment() {
		return o_payment;
	}
	public void setO_payment(String o_payment) {
		this.o_payment = o_payment;
	}
	public int getOl_id() {
		return ol_id;
	}
	public void setOl_id(int ol_id) {
		this.ol_id = ol_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
