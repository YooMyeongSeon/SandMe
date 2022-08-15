package com.green.sandme.board.cs;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.sandme.board.cs.vo.QnaVo;

@Controller
public class CSController {
	
	@Autowired
	private SqlSession sqlSession;
	
	//자주하는 질문
	@RequestMapping("/faq")
	public String openFaq() {
		return "board/faq";
	}
	
	//문의하기
	@RequestMapping("/qnaWriteForm")
	public String openQnaWrite() {
		return "board/qna/qnaWrite";
	}
	
	//문의하기 작성
	@PostMapping("/qnaWrite")		
    public String insertQna(QnaVo qVo) {
    	sqlSession.insert("com.green.sandme.board.dao.qnaDao.insertQna", qVo);
    	
    	return "redirect:/";
    }
	
	//문의하기 리스트
	@RequestMapping("/qnaList")
	public String qnaList(Model model) {
		List<QnaVo> qnaList = sqlSession.selectList("com.green.sandme.board.dao.qnaDao.getQna");
		model.addAttribute("qnaList", qnaList);
		
		return "board/qna/qnaList";
	}
	
	//문의내용 삭제 기능
	@RequestMapping("/qnaDelete")
	public String deleteQna(int qnaNum) {
		sqlSession.delete("com.green.sandme.board.dao.qnaDao.deleteQna", qnaNum);
		return "redirect:qnaList";
	}
}