package com.green.sandme.order;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.sandme.order.vo.CartCheckVo;

@RestController
public class CartController {
	
	@Autowired
	SqlSession sqlSession;
	
	@PostMapping("/cartCheck") //카트 담기 체크
	@ResponseBody
	public String cartCheck(@RequestBody CartCheckVo data) throws Exception {
		int cartCheck = sqlSession.selectOne("com.green.sandme.member.cart.dao.CartDao.cartCheck", data);
		
		if (cartCheck == 0) { //장바구니에 상품이 없다면,
			return "success";
		} else { //장바구니에 이미 상품이 있다면,
			int orderCheck = sqlSession.selectOne("com.green.sandme.member.cart.dao.CartDao.cartOrderCheck", data);
			
			if (orderCheck == 0) { //장바구니에 상품이 다른 오더라면,
				return "fail";
			} else { //장바구니에 상품이 같은 오더라면,
				int shopCheck = sqlSession.selectOne("com.green.sandme.member.cart.dao.CartDao.cartShopCheck", data);
				
				if (shopCheck == 0) { //장바구니에 상품이 다른 매장이라면
					return "fail";
				} else { //장바구니에 상품이 같은 매장이라면
					if (data.getOrder().equals("home")) { //오더가 배달 주문이라면
						int addressCheck = sqlSession.selectOne("com.green.sandme.member.cart.dao.CartDao.cartAddressCheck", data);
						
						if (addressCheck == 0) { //주소가 다른 주소라면
							return "fail";
						} else { //주소가 같은 주소라면
							return "success";
						}
					} else { //오더가 매장 주문이라면
						return "success";
					}
				}
			}
		}
	}
	
	@PostMapping("/cartDeleteInsert") //카트 삭제 후 삽입
	@ResponseBody
	public void cartDeleteInsert(@RequestBody int memberNum) throws Exception {
		sqlSession.delete("com.green.sandme.member.cart.dao.CartDao.deleteCartByMemberNum", memberNum);
	}
	
	@PostMapping("/cartDelete") 
	@ResponseBody
	public void deleteCart(@RequestBody List<Integer> delCartNum) throws Exception {
		for(int i = 0; i < delCartNum.size(); i++) {
			sqlSession.delete("com.green.sandme.member.cart.dao.CartDao.deleteCart", delCartNum.get(i));
		}
	}
}