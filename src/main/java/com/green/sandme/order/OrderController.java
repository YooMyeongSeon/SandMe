package com.green.sandme.order;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

@Controller
public class OrderController {
	@RequestMapping("/order")
	public String Oder(HttpServletRequest request, Model model) {
		String order = request.getParameter("order");
		
		if (order == null || order.equals("home")) {
			model.addAttribute("order", "home");
		} else {
			model.addAttribute("order", order);
		}
		
		model.addAttribute("chapter", "chapter01");
		return "order";
	}
	
	@PostMapping("/order")
	public String OderChapter(HttpServletRequest request, Model model) {
		String order = request.getParameter("order");
		String chapter = request.getParameter("chapter");

		if (chapter.equals("chapter01")) {
			String address = request.getParameter("address");
			model.addAttribute("address", address);
			model.addAttribute("chapter", "chapter02");
		} else if (chapter.equals("chapter02")) {
			model.addAttribute("chapter", "chapter03");
		} else if (chapter.equals("chapter03")) {
			model.addAttribute("chapter", "chapter04");
		}
		
		model.addAttribute("order", order);
		return "order";
	}
	
	@PostMapping("/order/shop")
	public void OderShop(@RequestBody String address, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");

		if (address.contains("정자동")) {
			
		} else {
			
		}
		
		Gson gson = new Gson();
		
		String data = gson.toJson("데이터");
		PrintWriter out = response.getWriter();
		out.print(data);
	}
}