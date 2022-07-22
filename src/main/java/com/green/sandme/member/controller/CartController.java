package com.green.sandme.member.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@PostMapping("/cart/delete")
	public void deleteCart(@RequestParam("cartNum") int cartNum) throws Exception {

	sqlSession.delete("com.green.sandme.member.cart.dao.CartDao.deleteCart", cartNum);
	}
}