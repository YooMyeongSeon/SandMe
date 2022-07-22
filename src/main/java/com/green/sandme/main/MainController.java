package com.green.sandme.main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.sandme.main.vo.BestVo;

@Controller
public class MainController {

	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/")
	public String main(Model model) {
//		
//		List<BestVo> list = sqlSession.selectList("com.green.sandme.main.dao.MainDao.selectBest");
//		
//		model.addAttribute("list", list);
		return "main";
	}
}