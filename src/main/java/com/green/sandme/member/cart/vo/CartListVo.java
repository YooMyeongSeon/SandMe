package com.green.sandme.member.cart.vo;

public class CartListVo {
	private int cartNum;
	private int memberNum;
	private String cartOrder;
	private String cartAddress;
	private String cartShop;
	private String cartMenu;
	private String cartCustom;
	private String cartDrink;
	private int cartQuantity;
	
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getCartOrder() {
		return cartOrder;
	}
	public void setCartOrder(String cartOrder) {
		this.cartOrder = cartOrder;
	}
	public String getCartAddress() {
		return cartAddress;
	}
	public void setCartAddress(String cartAddress) {
		this.cartAddress = cartAddress;
	}
	public String getCartShop() {
		return cartShop;
	}
	public void setCartShop(String cartShop) {
		this.cartShop = cartShop;
	}
	public String getCartMenu() {
		return cartMenu;
	}
	public void setCartMenu(String cartMenu) {
		this.cartMenu = cartMenu;
	}
	public String getCartCustom() {
		return cartCustom;
	}
	public void setCartCustom(String cartCustom) {
		this.cartCustom = cartCustom;
	}
	public String getCartDrink() {
		return cartDrink;
	}
	public void setCartDrink(String cartDrink) {
		this.cartDrink = cartDrink;
	}
	public int getCartQuantity() {
		return cartQuantity;
	}
	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}
}