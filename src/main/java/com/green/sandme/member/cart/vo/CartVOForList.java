package com.green.sandme.member.cart.vo;

public class CartVOForList {
	// 회원 번호에 따른 장바구니 목록 정보
	
	private int cartNum;
	private int memberNum;
	private String memberName;
	private String cartMenu;
	private int cartCount;
	
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getCartMenu() {
		return cartMenu;
	}
	public void setCartMenu(String cartMenu) {
		this.cartMenu = cartMenu;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	
	

}
