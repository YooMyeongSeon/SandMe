package com.green.sandme.board.faq;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaqController {
	
	@Autowired
	private SqlSession sqlSession;
	
	// faq 이동
	@RequestMapping("/faqList")
	public String openFaq() {
		return "board/faq/faqList";
	}
}
