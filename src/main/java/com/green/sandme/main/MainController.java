package com.green.sandme.main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.sandme.board.notice.vo.NoticeVo;

@Controller
public class MainController {

	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/")
	public String main(Model model) {
		List<NoticeVo> noticeList = sqlSession.selectList("com.green.sandme.board.dao.noticeDao.selectNoticeList");
		
		model.addAttribute("noticeList", noticeList);
		return "main";
	}
	
	@RequestMapping("/introduce")
	public String introduce() {
		return "introduce";
	}
}