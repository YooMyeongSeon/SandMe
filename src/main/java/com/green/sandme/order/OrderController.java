package com.green.sandme.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
	@RequestMapping("/order")
	public String Oder(HttpServletRequest request, Model model) {
		String order = request.getParameter("order");
		
		if (order == null || order.equals("home")) {
			model.addAttribute("order", "home");
		} else {
			model.addAttribute("order", "pickUp");
		}
		
		return "order";
	}
	
	@PostMapping("/order")
	public String OderChapter01(HttpServletRequest request, Model model) {
		String order = request.getParameter("order");
		String address = request.getParameter("address");
		
		if (order == null || order.equals("home")) {
			model.addAttribute("order", "home");
		} else {
			model.addAttribute("order", "pickUp");
		}
		
		model.addAttribute("address", address);
		return "order";
	}
}
