package com.green.sandme.member.controller;



import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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



}