package com.green.sandme.member.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.sandme.member.cart.vo.CartOrderVO;
import com.green.sandme.member.cart.vo.CartVO;
import com.green.sandme.member.cart.vo.CartVOForList;



// 클라이언트의 요청을 처리하기 위한 클래스

@Controller  // 단순히 객체만 반환하는 어노테이션, Json 형태로 객체 데이터를 반환하는 것
public class CartListController {
	
@Autowired
private SqlSession sqlSession;
	

	@PostMapping("/cart/insertOrder")
	public String pk(@RequestParam("menuPrice") int menuPrice,
					 @RequestParam("cartCount") int cartCount,
					 @RequestParam("cartNum") int cartNum,
					 Model model) {

		System.out.println(menuPrice);
		System.out.println(cartCount);
		System.out.println(cartNum);
		
		int totalPrice = (menuPrice * cartCount);
		String orderId = "kim@naver.com"; 
		
		CartOrderVO Cov = new CartOrderVO();
		
		Cov.setOrderId(orderId);
		Cov.setTotalPrice(totalPrice);
		Cov.setCartNum(cartNum);
		
		int total = Cov.getTotalPrice();
		int cart = Cov.getCartNum();
		String Id = Cov.getOrderId();
		
		System.out.println(Id);
		
		//sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.insertOrder", Cov);
		
		//List<CartOrderVO> orderList = sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.selectOrder",cartNum);
		
		//model.addAttribute("orderList",orderList);
		
		model.addAttribute("totalPrice",total);
		model.addAttribute("cartNum",cart);
		model.addAttribute("orderId", Id);

		
		return "member/practice";
	}
	
	// 카카오 페이 성공 페이지 이동
	@GetMapping("/member/kakaopaysuccess")
	public String kakaopaySuccess() {
		
		return "/member/kakaopaysuccess";
	}
	
	
	// 카카오 페이 실패 페이지 이동 -> 다시 장바구니로
	@GetMapping("/member/kakaopayfail")
	public String kakaopayFail() {
		
		return "/member/kakaopayfail";
	}
	


	// 장바구니 테이블 전체 조회 -> 삭제 확인 용도
	
	  @GetMapping("/cartTable") 
	  public String cartTable(Model model) { 
		  List<CartVO> allList = sqlSession.selectList("cart.selectAll");
		  model.addAttribute("allList", allList);
	  
		  return "cartTable"; 
	  }
	  
	 
	  
	  //회원 정보 입력 페이지
	  
	  @GetMapping("/loginForm") 
	  public String member() { 
		  return "member/loginForm";
	  }
	  
	   
	  // 로그인 후 장바구니 목록 페이지
	  
	  @PostMapping("/cartList") 
	  public String cartList(Model model, int memberNum){
	  
	  // 회원에 따른 장바구니 정보 - cartNum, cartCount, cartMenu 
		  List<CartVOForList> cartList =
				  sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.pSelectCart",memberNum);
	  
	  
			  System.out.println(cartList.size()); 
			  
			  if(cartList.size() == 0) { 
				  model.addAttribute("cartNullChk", "nothing"); 
			  }
			  	else { model.addAttribute("cartList", cartList); } //
			  
			  	return "member/cartList";
	  
	  }
	 
	
	// 장바구니 목록 -> 메뉴 선택 페이지
	
	
	
/*	@PostMapping("/cartList")
	public String cartList(HttpSession session, String memberEmail, String memberPwd) {
		
		//List<MemberVO> memList = (List<MemberVO>) sqlSession.selectMap("cart.checkLogin", memberEmail, memberPwd);
		
		
		if(memberEmail != null && memberPwd != null) {
			
			

			return "cartList";
		}
		
		
		
		return "loginForm";
	}
	*/
  

	  // 결제창 페이지 이동
//	  @PostMapping("/order")
//	  public String orderPage(Model model, @RequestParam int memberNum) {
//		  
//		  // 장바구니 목록
//		  List<CartVO> cartList = sqlSession.selectList("cart.SelectCart", memberNum);
//		  // 회원 정보
//		  List<MemberVO> member = sqlSession.selectList("cart.selectMember", memberNum);
//		  // 지점 정보
//		  List<StoreVO> storeList = sqlSession.selectList("cart.orderStore", memberNum);
//		  
//		  
//		  model.addAttribute("cartList",cartList);
//		  model.addAttribute("member",member);
//		  model.addAttribute("storeList", storeList);
//		  
//		  return "orderPage";
//	  }
	  

	  
	 
	


}
