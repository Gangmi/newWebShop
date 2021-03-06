package com.javassem.domain;

public class ProductVO {

	private int p_id;
	private String p_name;
	private String p_cat;
	private int p_price;
	private int p_quan;
	private String page;
	private String itemQuan;
	private String p_brand;
	private String p_color;
	private String p_detail;
	private String p_date;
	private int soldcount;
	private int confirm;
	private int ordermethod;
	private String search;

	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getSoldcount() {
		return soldcount;
	}

	public void setSoldcount(int soldcount) {
		this.soldcount = soldcount;
	}

	public int getOrdermethod() {
		return ordermethod;
	}

	public void setOrdermethod(int ordermethod) {
		this.ordermethod = ordermethod;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	private int startprice;

	public int getStartprice() {
		return startprice;
	}

	public void setStartprice(int startprice) {
		this.startprice = startprice;
	}

	public int getEndprice() {
		return endprice;
	}

	public void setEndprice(int endprice) {
		this.endprice = endprice;
	}

	private int endprice;

	public String getP_brand() {
		return p_brand;
	}

	public void setP_brand(String p_brand) {
		this.p_brand = p_brand;
	}

	public String getP_color() {
		return p_color;
	}

	public void setP_color(String p_color) {
		this.p_color = p_color;
	}

	public String getP_detail() {
		return p_detail;
	}

	public void setP_detail(String p_detail) {
		this.p_detail = p_detail;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

	public String getItemQuan() {
		return itemQuan;
	}

	public void setItemQuan(String itemQuan) {
		this.itemQuan = itemQuan;
	}

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
