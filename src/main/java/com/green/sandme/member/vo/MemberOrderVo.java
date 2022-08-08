package com.green.sandme.member.vo;

import java.util.List;

import com.green.sandme.order.vo.OrderMenuVo;

public class MemberOrderVo {
	int orderNum;
	String shopName;
	String orderCategory;
	String orderAddress;
	String orderTime;
	String orderRequest;
	int orderTotalPrice;
	List<OrderMenuVo> orderMenu;
	
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
	public List<OrderMenuVo> getOrderMenu() {
		return orderMenu;
	}
	public void setOrderMenu(List<OrderMenuVo> orderMenu) {
		this.orderMenu = orderMenu;
	}
}