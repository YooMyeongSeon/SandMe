package com.green.sandme.main.vo;

public class BestVo {
	String menu;
	String price;
	
	public BestVo(String menu, String price) {
		this.menu = menu;
		this.price = price;
	}
	
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}