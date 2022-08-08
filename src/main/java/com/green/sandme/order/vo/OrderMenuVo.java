package com.green.sandme.order.vo;

public class OrderMenuVo {
	int orderMenuNum;
	int orderNum;
	int menuNum;
	String orderCustom;
	String orderDrink;
	int orderQuantity;
	
	public int getOrderMenuNum() {
		return orderMenuNum;
	}
	public void setOrderMenuNum(int orderMenuNum) {
		this.orderMenuNum = orderMenuNum;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getMenuNum() {
		return menuNum;
	}
	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
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
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}