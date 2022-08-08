package com.green.sandme.board.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.sandme.board.notice.vo.NoticeVO;

@Controller
public class NoticeController {

	@Autowired
	private SqlSession sqlSession;
	
	// 공지사항 목록 보기
	@RequestMapping("/noticeList")
	public String noticeList(Model model) {
		List<NoticeVO> noticeList = sqlSession.selectList("com.green.sandme.board.notice.dao.BoardDao.getNotice");
		model.addAttribute("noticeList", noticeList);
		return "board/notice/noticeList";
	}
	
	// 공지사항 상세보기 (원래코드)
	@RequestMapping("/noticeView")
	public String noticeView(Model model, int noticeNum) {
		sqlSession.update("com.green.sandme.board.notice.dao.BoardDao.updateViewCnt", noticeNum);
		NoticeVO nVo = sqlSession.selectOne("com.green.sandme.board.notice.dao.BoardDao.viewNotice", noticeNum);
		model.addAttribute("nVo", nVo);
		return "board/notice/noticeView";
	}
	/////////////////////////////////////////////////////////////////////////
	
	//게시글 작성 화면 호출
	@RequestMapping("/noticeWriteForm")		
    public String openNoticeWrite() {
    	return "board/notice/noticeWrite";
    }
	
	// 작성된 게시글 등록 기능 메소드
	@PostMapping("/noticeWrite")		
    public String insertnotice(NoticeVO nVo) {
    	sqlSession.insert("com.green.sandme.board.notice.dao.BoardDao.insertNotice", nVo);
    	return "redirect:noticeList";
    }
	
	// //////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/noticeUpdateForm")
	public String noticeUpdateForm(Model model, int noticeNum) {
		NoticeVO nVo = sqlSession.selectOne("com.green.sandme.board.notice.dao.BoardDao.viewNotice", noticeNum);
		model.addAttribute("nVo", nVo);
		return "board/notice/noticeUpdate";
	}
	
	// 게시글 수정 기능
	@RequestMapping("/noticeUpdate")		
    public String updateNotice(NoticeVO nVo) {
    	sqlSession.update("com.green.sandme.board.notice.dao.BoardDao.updateNotice", nVo);
    	return "forward:noticeView";
    }
	
	///////////////////////////////////////////////////////////////////////////////////
	// 게시글 삭제 기능
	@RequestMapping("/noticeDelete")
	public String deleteNotice(int noticeNum) {
		sqlSession.delete("com.green.sandme.board.notice.dao.BoardDao.deleteNotice", noticeNum);
		return "redirect:noticeList";
	}
	

}