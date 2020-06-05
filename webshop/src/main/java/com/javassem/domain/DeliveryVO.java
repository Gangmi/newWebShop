package com.javassem.domain;

public class DeliveryVO {
	private int d_id;
	private int o_id;
	private String d_state;
	private String d_startdate;


	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public String getD_state() {
		return d_state;
	}
	public void setD_state(String d_state) {
		this.d_state = d_state;
	}
	public String getD_startdate() {
		return d_startdate;
	}
	public void setD_startdate(String d_startdate) {
		this.d_startdate = d_startdate;
	}
}
