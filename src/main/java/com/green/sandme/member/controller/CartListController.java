package com.green.sandme.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.sandme.member.cart.vo.CartVO;
import com.green.sandme.member.cart.vo.CartVOForList;



// 클라이언트의 요청을 처리하기 위한 클래스

@Controller  // 단순히 객체만 반환하는 어노테이션, Json 형태로 객체 데이터를 반환하는 것
public class CartListController {
	
@Autowired
private SqlSession sqlSession;

	 
	  
	  //회원 정보 입력 페이지
	  
	  @GetMapping("/loginForm") 
	  public  String member() throws Exception { 
		  return "/member/loginForm";
	  }
	  
	   
	  // 로그인 후 장바구니 목록 페이지
	  
	  @PostMapping("/cartList") 
	  public String cartList(Model model, int memberNum) throws Exception {
	  
	  // 회원에 따른 장바구니 정보 - cartNum, cartCount, cartMenu 
		  List<CartVOForList> cartList =
				  sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.pSelectCart",memberNum);
	  
	  
			  //System.out.println(cartList.size()); 
			  
			  if(cartList.size() == 0) { 
				  model.addAttribute("cartNullChk", "nothing"); 
			  }
			  	else { 
			  		model.addAttribute("cartList", cartList); 
			  } 
			  
			  	return "member/cartList";
	  
	  }

	  /*	  
	  @GetMapping("/cartList/{memberNum}") 
	  public ModelAndView cartList(@PathVariable("memberNum") Integer memberNum) throws Exception {
	  
	  // 회원에 따른 장바구니 정보 - cartNum, cartCount, cartMenu 
		  List<CartVOForList> cartList =
				  sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.pSelectCart",memberNum);
	  
	  
			  System.out.println(cartList.size()); 
			  
			  ModelAndView mav = new ModelAndView();
			  
			  if(cartList.size() == 0) { 
				  mav.addObject("cartNullChk", "nothing"); 
			  }
			  	else { 
			  		mav.addObject("cartList", cartList); 
			  } 
			  mav.setViewName("member/cartList");
			  return mav;
	  }
	  */	  
	 
	  // 카카오 페이 성공 페이지 이동 -> 주문내역서
	  // 매핑 주소에 memnberNum
		@GetMapping("/member/kakaopaysuccess/{memberNum}")
		public ModelAndView kakaopaySuccess(@PathVariable("memberNum") Integer memberNum) throws Exception {
			ModelAndView mav = new ModelAndView();
			mav.addObject("memberNum",memberNum);
			mav.setViewName("/member/kakaopaysuccess");
			return mav;
		}
		
		
		
		
		// 카카오 페이 실패 페이지 이동 -> 다시 장바구니로
		// 매핑 주소에 memnberNum
		@GetMapping("/member/kakaopayfail/{memberNum}")
		public ModelAndView kakaopayFail(@PathVariable("memberNum") Integer memberNum) throws Exception  {
			ModelAndView mav = new ModelAndView();
			mav.addObject("memberNum",memberNum);
			mav.setViewName("/member/kakaopayfail");
			return mav;
		}
		
		//카카오 페이 결제 취소 페이지 이동 -> 다시 장바구니로
		// 매핑 주소에 memnberNum
		@GetMapping("/member/kakaopaycancle/{memberNum}")
		public ModelAndView kakaopayCancle(@PathVariable("memberNum") Integer memberNum) throws Exception  {
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("memberNum",memberNum);
			mav.setViewName("/member/kakaopaycancle");
			
			return mav;
		}
	  

}
