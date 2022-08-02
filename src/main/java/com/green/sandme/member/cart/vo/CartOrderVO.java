package com.green.sandme.member.cart.vo;

public class CartOrderVO {
	// 주문서
	private int cartNum;
	private int cartMenu;
	private int cartCount;  
	private String custom;
	private String cartAddress;
	private int memberNum;
	private int menuPrice;
	
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public int getCartMenu() {
		return cartMenu;
	}
	public void setCartMenu(int cartMenu) {
		this.cartMenu = cartMenu;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public String getCartAddress() {
		return cartAddress;
	}
	public void setCartAddress(String cartAddress) {
		this.cartAddress = cartAddress;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	
	
	
	
	
}
