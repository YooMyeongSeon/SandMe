package com.green.sandme.order.vo;

public class OrderAddressVo {
	int shopNum;
	String shopName;
	String shopAddress;
	String shopTel;
	String shopOpen;
	
	public OrderAddressVo(int shopNum, String shopName, String shopAddress, String shopTel, String shopOpen) {
		this.shopNum = shopNum;
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.shopTel = shopTel;
		this.shopOpen = shopOpen;
	}

	public int getShopNum() {
		return shopNum;
	}

	public void setShopNum(int shopNum) {
		this.shopNum = shopNum;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopTel() {
		return shopTel;
	}

	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}

	public String getShopOpen() {
		return shopOpen;
	}

	public void setShopOpen(String shopOpen) {
		this.shopOpen = shopOpen;
	}
}