package com.javassem.domain;

public class ProductVO {
	
	private int p_id;
	private String p_name;
	private String p_cat;
	private int p_price;
	private int p_quan;

	private String page;
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}


	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_cat() {
		return p_cat;
	}
	public void setP_cat(String p_cat) {
		this.p_cat = p_cat;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_quan() {
		return p_quan;
	}
	public void setP_quan(int p_quan) {
		this.p_quan = p_quan;
	}
	
	

}
