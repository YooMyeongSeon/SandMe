package com.green.sandme.member.controller;



import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.green.sandme.member.cart.vo.CartOrderVO;

@RestController
public class CartController {
	
	@Autowired
	private SqlSession sqlSession;
	

	// 장바구니에서 선택 항목만 삭제
	// @RequestParam을 통해 cartList.html에서 선택 체크박스를 배열로 묶어 값을 넘겼으므로
	// "cartNum=[]"을 통해 배열로 받고 cartNum이라는 정수형 리스트에 담아
	// for문을 통해 cartNum에 담긴 사이즈 만큼 deleteCart를 실행해 삭제함
	
	
	@PostMapping("/cart/delete") 
	public void deleteCart(@RequestParam(value="cartNum[]") List<Integer> cartNum) throws Exception {
  
		
		System.out.println(cartNum.size());
  
		for(int i=0; i<cartNum.size(); i++) {
			sqlSession.delete("com.green.sandme.member.cart.dao.CartDao.deleteCart",cartNum.get(i));
		 }

	  }
	
	
	
	// 주문서 이동
	// 매핑 주소에 memberNum을 담아서 이동
/*	@GetMapping("/cart/insertOrder/{memberNum}")
	public ModelAndView cartToorder(@RequestParam("cartNum") int cartNum,
					 @PathVariable("memberNum") Integer memberNum) throws Exception{
		
		System.out.println("cartNum: "+cartNum);
		System.out.println("memberNum :" +memberNum);
		
		ModelAndView mav = new ModelAndView();
		
		List<CartOrderVO> cartOrder = sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.selectCartOrder",memberNum);
		
		mav.addObject("cartOrder",cartOrder);
		mav.addObject("memberNum",memberNum);
		mav.setViewName("member/orderForm");
		
		System.out.println(mav.getViewName());
		
		return mav;
	}
*/
	
	@GetMapping("/cart/insertOrder")
	public ModelAndView cartToorder(@RequestParam("chkNum[]") List<Integer> cartNum,
					 				@RequestParam("memberNum") Integer memberNum) throws Exception{
		
		System.out.println("cartNum: "+cartNum.size());
		System.out.println("memberNum :" +memberNum);
		
		ModelAndView mav = new ModelAndView();


		List<Integer> cartNumList = new ArrayList<>();
		for(int i=0; i<cartNum.size(); i++) {
			cartNumList.add(cartNum.get(i));
			System.out.println(cartNumList);
			if(! cartNumList.isEmpty()) {
				List<CartOrderVO> cartOrders = sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.selectCartOrder",cartNumList);
				CartOrderVO totalPrice = sqlSession.selectOne("com.green.sandme.member.cart.dao.CartDao.cartTotalPrice",cartNumList);
				mav.addObject("cartOrder",cartOrders);
				mav.addObject("totalPrice",totalPrice);
				mav.addObject("memberNum",memberNum);//modelandview로 memberNum은 이동시키고 받아온 장바구니로 select
				mav.setViewName("member/orderForm");
			}
		}
		
		return mav;
	}


}