package com.green.sandme.board.notice.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.sandme.board.notice.vo.BoardVO;

@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
	
	
	// 공지사항 목록 보기
	@RequestMapping("/boardList")
	public String boardList(Model model) {
		List<BoardVO> boardList = sqlSession.selectList("com.green.sandme.board.notice.dao.BoardDao.getBoard");

		model.addAttribute("boardList", boardList);
		return "board/notice/boardList";
	}
	
	// 공지사항 상세보기 (원래코드)
	@RequestMapping("/boardView")
	public String boardView(Model model, int boardNum) {
		sqlSession.update("com.green.sandme.board.notice.dao.BoardDao.updateViewCnt", boardNum);
		BoardVO bVo = sqlSession.selectOne("com.green.sandme.board.notice.dao.BoardDao.viewBoard", boardNum);
		model.addAttribute("bVo", bVo);
		return "board/notice/boardView";
	}
	/////////////////////////////////////////////////////////////////////////
	//게시글 작성 화면 호출
	@RequestMapping("/boardWriteForm")		
    public String openBoardWrite() {
    	return "board/notice/boardWrite";
    }
	
	// 작성된 게시글 등록 기능 메소드
	@PostMapping("/boardWrite")		
    public String insertBoard(BoardVO bVo) {
    	sqlSession.insert("com.green.sandme.board.notice.dao.BoardDao.insertBoard", bVo);
    	return "redirect:boardList";
    }
	// //////////////////////////////////////////////////////////////////////////
	@RequestMapping("/boardUpdateForm")
	public String boardUpdateForm(Model model, int boardNum) {
		BoardVO bVo = sqlSession.selectOne("com.green.sandme.board.notice.dao.BoardDao.viewBoard", boardNum);
		model.addAttribute("bVo", bVo);
		return "board/notice/boardUpdate";
	}
	
	// 게시글 수정 기능
	@RequestMapping("/boardUpdate")		
    public String updateBoard(BoardVO bVo) {
    	sqlSession.update("com.green.sandme.board.notice.dao.BoardDao.insertBoard", bVo);
    	return "forward:boardView";
    }
	///////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/boardDelete")
	public String deleteBoard(int boardNum) {
		sqlSession.delete("com.green.sandme.board.notice.dao.BoardDao.deleteBoard", boardNum);
		return "redirect:boardList";
	}
		
}