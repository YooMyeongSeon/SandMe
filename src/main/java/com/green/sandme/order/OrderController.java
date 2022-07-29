package com.green.sandme.order;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.green.sandme.member.cart.vo.CartVO;
import com.green.sandme.order.vo.MenuVo;
import com.green.sandme.order.vo.OrderAddressVo;

@Controller
public class OrderController {
	
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/order") //주문 페이지
	public String oder(HttpServletRequest request, Model model) {
		String order = request.getParameter("order");
		
		if (order == null || order.equals("home")) {
			model.addAttribute("order", "home");
		} else {
			model.addAttribute("order", order);
		}
		
		model.addAttribute("chapter", "chapter01");
		return "order";
	}
	
	@PostMapping("/order") //주문 챕터 기능
	public String oderChapter(HttpServletRequest request, Model model) {
		String order = request.getParameter("order");
		String chapter = request.getParameter("chapter");

		if (chapter.equals("chapter01")) {
			if (order.equals("home")) {
				String address = request.getParameter("address") + " " + request.getParameter("detailAddress");
				model.addAttribute("address", address);
			}
			//매장
			int shop = Integer.parseInt(request.getParameter("shopNum"));
			model.addAttribute("shop", shop);
			model.addAttribute("chapter", "chapter02_01");
		} else if (chapter.equals("chapter02_01")) {//  메뉴 선택
			if (order.equals("home")) {
				String address = request.getParameter("inputAddress");
				model.addAttribute("address", address);
			}
			
			int shop = Integer.parseInt(request.getParameter("inputShop"));
			int sandMenu = Integer.parseInt(request.getParameter("selectsandMenu")); // 선택한 샌드위치 메뉴를 통해 
			
			System.out.println(sandMenu);
			
			MenuVo mVo = sqlSession.selectOne("com.green.sandme.order.dao.OrderDao.selectMenu", sandMenu);
			
			model.addAttribute("shop", shop);
			model.addAttribute("mVo", mVo);
			model.addAttribute("chapter", "chapter02_02");
		} else if (chapter.equals("chapter02_02")) {// 세부 메뉴 선택
			if (order.equals("home")) {
				String address = request.getParameter("inputAddress");
				model.addAttribute("address", address);
			}
			
			int shop = Integer.parseInt(request.getParameter("inputShop"));
			int sandMenu = Integer.parseInt(request.getParameter("inputSandMenu"));
			
			System.out.println(sandMenu);
			
			String[] vegelist = request.getParameterValues("vegetable[]");
			String vege = "";
			
			for (int i=0; i<vegelist.length; i++) {
				vege += vegelist[i] + ", ";
			}
			
			String custom = "빵 : " + request.getParameter("bread") +
					", 야채 : " + vege +
					"소스 : " + request.getParameter("sauce") +
					", 치즈 : " + request.getParameter("cheese") +
					", 음료 : " + request.getParameter("drink");
			
			System.out.println(custom);

			model.addAttribute("custom", custom);
			model.addAttribute("sandMenu", sandMenu);
			model.addAttribute("shop", shop);
			model.addAttribute("chapter", "chapter03");
		} else if (chapter.equals("chapter03")) {
			if (order.equals("home")) {
				String address = request.getParameter("inputAddress");
				model.addAttribute("address", address);
			}
			
			model.addAttribute("chapter", "chapter04");
		} else if (chapter.equals("inCart")) {//////////////////////////////////////////////////
			System.out.println("장바구니 기능");
			if(order.equals("home")) {
				String address = request.getParameter("inputAddress");
				model.addAttribute("address", address);
			}
			
			// 매장
			int shop = Integer.parseInt(request.getParameter("inputShop"));
			// 메뉴 선택 - menuNum
			int sandMenu = Integer.parseInt(request.getParameter("inputSandMenu"));
			// 매장 주소
			String address = request.getParameter("inputAddress");
			
			// 세부 메뉴 선택
			String[] vegelist = request.getParameterValues("vegetable[]");
			String vege = "";
			
			for (int i=0; i<vegelist.length; i++) {
				vege += vegelist[i] + ", ";
			}
			
			String custom = "빵 : " + request.getParameter("bread") +
					", 야채 : " + vege +
					"소스 : " + request.getParameter("sauce") +
					", 치즈 : " + request.getParameter("cheese") +
					", 음료 : " + request.getParameter("drink");
			
			System.out.println(custom);
			
			// 자동으로 장바구니에 수량 +1
			int count = 1;
			
			// 객체에 저장
			CartVO cart = new CartVO();
			cart.setCartCount(count);
			cart.setCartAddress(address);
			cart.setCartShop(shop);
			cart.setCartMenu(sandMenu);
			cart.setCustom(custom);
			

			// mapper에서 parameterType으로 부여
			sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.insertCart", cart);
			
			// 장바구니 목록 가져오기
			List<CartVO> cartList = sqlSession.selectList("com.green.sandme.member.cart.dao.CartDao.SelectCart", sandMenu);
			
			
			model.addAttribute("shop",shop);
			model.addAttribute("sandMenu",sandMenu);
			
			// 빈 장바구니 여부 체크
			if(cartList.size() == 0) {
				model.addAttribute("cartNullChk", "nothing");
			} else {
				model.addAttribute("cartList", cartList);
			}
			
			return "member/cartList";
		}
		
		model.addAttribute("order", order);
		return "order";
	}
	
	@PostMapping("/order/home") //배달 주문 매장 검색 기능
	public void oderHome(@RequestBody String address, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		
		OrderAddressVo oAv = sqlSession.selectOne("com.green.sandme.order.dao.OrderDao.selectShopByAddress", address);
		
		Gson gson = new Gson();
		
		String data = gson.toJson(oAv);
		PrintWriter out = response.getWriter();
		out.print(data);
	}
	
	@PostMapping("/order/pickUp") //방문 포장 매장 검색 기능
	public void oderPickUp(@RequestBody String address, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		
		OrderAddressVo oAv = sqlSession.selectOne("com.green.sandme.order.dao.OrderDao.selectShopByName", address);
		
		Gson gson = new Gson();
		
		String data = gson.toJson(oAv);
		PrintWriter out = response.getWriter();
		out.print(data);
	}
	
	
	// 수량 변경
	@PostMapping("modifyCount")
	public String modifyCartCount(@RequestParam("cartNUm") int cartNum, 
								  @RequestParam("num") int num,
								  Model model) {
		
		CartVO cart = new CartVO();
		cart.setCartCount(num);
		
		sqlSession.update("com.green.sandme.member.cart.dao.CartDao.updateCartCount", cart);
		
		// 수량 * 가격
		int sumPrice = sqlSession.selectOne("com.green.sandme.member.cart.dao.CartDao.selectSumPrice",cartNum);
		
		System.out.println(sumPrice);
		
		model.addAttribute("sumPrice",sumPrice);
		
		return "member/cartList";
		
	}
}