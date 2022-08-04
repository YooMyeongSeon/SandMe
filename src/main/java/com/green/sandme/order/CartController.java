package com.green.sandme.order;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.sandme.order.vo.CartCheckVo;

@RestController
public class CartController {
	
	@Autowired
	SqlSession sqlSession;
	
	@PostMapping("/cartCheck")
	@ResponseBody
	public String cartCheck(@RequestBody CartCheckVo data) throws Exception {
		int orderResult = sqlSession.selectOne("com.green.sandme.member.cart.dao.CartDao.cartCheck", data);
		
		System.out.println(orderResult);
		
//		if(result != 0) {
//			return "fail";
//		} else {
//			return "success";
//		}
		
		return "success";
	}
	
	@PostMapping("/cart/delete") 
	@ResponseBody
	public void deleteCart(@RequestParam(value="cartNum[]") List<Integer> cartNum) throws Exception {  
		for(int i=0; i<cartNum.size(); i++) {
			sqlSession.delete("com.green.sandme.member.cart.dao.CartDao.deleteCart", cartNum.get(i));
		 }
	  }
	
//	@GetMapping("/cart/insertOrder")
//	public ModelAndView cartToorder(@RequestParam("chkNum[]") List<Integer> cartNum,
//					 				@RequestParam("memberNum") Integer memberNum) throws Exception{
//		
//		System.out.println("cartNum: "+cartNum.size());
//		System.out.println("memberNum :" +memberNum);
//		
//		ModelAndView mav = new ModelAndView();
//
//
//		List<Integer> cartNumList = new ArrayList<>();
//		for(int i=0; i<cartNum.size(); i++) {
//			cartNumList.add(cartNum.get(i));
//			System.out.println(cartNumList);
//			if(! cartNumList.isEmpty()) {
//				List<CartOrderVO> cartOrders = sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.selectCartOrder",cartNumList);
//				CartOrderVO totalPrice = sqlSession.selectOne("com.green.sandme.member.cart.dao.CartDao.cartTotalPrice",cartNumList);
//				mav.addObject("cartOrder",cartOrders);
//				mav.addObject("totalPrice",totalPrice);
//				mav.addObject("memberNum",memberNum);//modelandview로 memberNum은 이동시키고 받아온 장바구니로 select
//				mav.setViewName("member/orderForm");
//			}
//		}
//		
//		return mav;
//	}
}
