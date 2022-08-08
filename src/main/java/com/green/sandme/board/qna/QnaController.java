package com.green.sandme.board.qna;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.sandme.board.qna.vo.QnaVO;

@Controller
public class QnaController {

	@Autowired	
	private SqlSession sqlSession;
	
	// 문의하기 폼으로 이동
	@RequestMapping("/qnaWriteForm")
	public String openQnaWrite() {
		return "board/qna/qnaWrite";
	}
	// 문의하기 작성
	@PostMapping("/qnaWrite")		
    public String insertQna(QnaVO qVo) {
    	sqlSession.insert("com.green.sandme.board.notice.dao.BoardDao.insertQna", qVo);
    	return "main";
    }
	
	// 문의내용 확인 리스트
	@RequestMapping("/qnaList")
	public String qnaList(Model model) {
		List<QnaVO> qnaList = sqlSession.selectList("com.green.sandme.board.notice.dao.BoardDao.getQna");
		model.addAttribute("qnaList",qnaList);
		return "board/qna/qnaList";
	}
	
	// 문의내용 삭제 기능
	@RequestMapping("/qnaDelete")
	public String deleteQna(int qnaNum) {
		sqlSession.delete("com.green.sandme.board.notice.dao.BoardDao.deleteQna", qnaNum);
		return "redirect:qnaList";
	}
}	

