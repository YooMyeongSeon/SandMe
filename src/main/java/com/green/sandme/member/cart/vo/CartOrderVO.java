package com.green.sandme.member.cart.vo;

public class CartOrderVO {
	
	private int orderNum;
	private String orderId;
	private int orderTime;
	private int totalPrice;
	private int cartNum;
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(int orderTime) {
		this.orderTime = orderTime;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	
	

}
