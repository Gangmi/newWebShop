package com.javassem.domain;

public class CouponVO {

	private int cou_id;
	private String m_id;
	private String cou_name;
	private int ol_id;
	private String cou_date;
	private String used;
	public int getCou_id() {
		return cou_id;
	}
	public void setCou_id(int cou_id) {
		this.cou_id = cou_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	public int getOl_id() {
		return ol_id;
	}
	public void setOl_id(int ol_id) {
		this.ol_id = ol_id;
	}
	public String getCou_date() {
		return cou_date;
	}
	public void setCou_date(String cou_date) {
		this.cou_date = cou_date;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	
}
