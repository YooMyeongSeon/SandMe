package com.green.sandme.member.vo;

public class MemberOrderMenuVo {
	String menuPicUrl;
	String menuName;
	int orderQuantity;
	String orderCustom;
	String orderDrink;
	int menuPrice;
	
	public String getMenuPicUrl() {
		return menuPicUrl;
	}
	public void setMenuPicUrl(String menuPicUrl) {
		this.menuPicUrl = menuPicUrl;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getOrderCustom() {
		return orderCustom;
	}
	public void setOrderCustom(String orderCustom) {
		this.orderCustom = orderCustom;
	}
	public String getOrderDrink() {
		return orderDrink;
	}
	public void setOrderDrink(String orderDrink) {
		this.orderDrink = orderDrink;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		if (!orderDrink.equals("음료 선택 안함")) {
			menuPrice += 2000;
		}
		
		menuPrice *= orderQuantity;
		
		this.menuPrice = menuPrice;
	}
}