package com.green.sandme.member.vo;

import java.util.List;

public class MemberOrderVo {
	int orderNum;
	String shopName;
	String orderCategory;
	String orderAddress;
	String orderTime;
	String orderRequest;
	int orderTotalPrice;
	List<MemberOrderMenuVo> orderMenu;
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getOrderCategory() {
		return orderCategory;
	}
	public void setOrderCategory(String orderCategory) {
		if (orderCategory.equals("home")) {
			orderCategory = "배달 주문";
		} else {
			orderCategory = "방문 포장";
		}

		this.orderCategory = orderCategory;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderRequest() {
		return orderRequest;
	}
	public void setOrderRequest(String orderRequest) {
		this.orderRequest = orderRequest;
	}
	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public List<MemberOrderMenuVo> getOrderMenu() {
		return orderMenu;
	}
	public void setOrderMenu(List<MemberOrderMenuVo> orderMenu) {
		this.orderMenu = orderMenu;
	}
}