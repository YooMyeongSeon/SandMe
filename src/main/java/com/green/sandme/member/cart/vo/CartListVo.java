package com.green.sandme.member.cart.vo;

public class CartListVo {
	private int cartNum;
	private int memberNum;
	
	private String cartOrder;
	private String cartAddress;
	private String shopName;
	private String shopAddress;
	private int shopNum;
	
	private int menuNum;
	private String menuName;
	private String menuPicUrl;
	private String cartCustom;
	private String cartDrink;
	private int cartQuantity;
	private int menuPrice;
	
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public int getMenuNum() {
		return menuNum;
	}
	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
	}
	public int getShopNum() {
		return shopNum;
	}
	public void setShopNum(int shopNum) {
		this.shopNum = shopNum;
	}
	public String getMenuPicUrl() {
		return menuPicUrl;
	}
	public void setMenuPicUrl(String menuPicUrl) {
		this.menuPicUrl = menuPicUrl;
	}
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
		if (cartOrder.equals("home")) {
			cartOrder = "배달 주문";
		} else {
			cartOrder = "방문 포장";
		}
		
		this.cartOrder = cartOrder;
	}
	public String getCartAddress() {
		return cartAddress;
	}
	public void setCartAddress(String cartAddress) {
		this.cartAddress = cartAddress;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		if (!cartDrink.equals("음료 선택 안함")) {
			menuPrice += 2000;
		}
		
		menuPrice *= cartQuantity;
		
		this.menuPrice = menuPrice;
	}
}