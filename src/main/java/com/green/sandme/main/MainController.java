package com.green.sandme.main;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/")
	public String main(Model model) {
<<<<<<< HEAD
//		
//		List<BestVo> list = sqlSession.selectList("com.green.sandme.main.dao.MainDao.selectBest");
//		
//		model.addAttribute("list", list);
=======
>>>>>>> origin/MS
		return "main";
	}
}