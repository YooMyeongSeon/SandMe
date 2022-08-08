package com.green.sandme.board.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.sandme.board.notice.vo.NoticeVo;

@Controller
public class NoticeController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/noticeList") //공지사항 목록 보기
	public String noticeList(Model model) {
		List<NoticeVo> noticeList = sqlSession.selectList("com.green.sandme.board.dao.noticeDao.selectNoticeList");
		
		model.addAttribute("notice", "list");
		model.addAttribute("noticeList", noticeList);
		return "board/notice/noticeList";
	}

	@RequestMapping("/noticeWriteForm") //공지사항 작성 폼
    public String openNoticeWrite(Model model) {
		List<NoticeVo> noticeList = sqlSession.selectList("com.green.sandme.board.dao.noticeDao.selectNoticeList");
		
		model.addAttribute("notice", "write");
		model.addAttribute("noticeList", noticeList);
		return "board/notice/noticeList";
    }
	
	@PostMapping("/noticeWrite") //공지사항 작성
    public String insertnotice(NoticeVo nVo) {
    	sqlSession.insert("com.green.sandme.board.dao.noticeDao.insertNotice", nVo);
    	return "redirect:noticeList";
    }
	
	@RequestMapping("/noticeView") //공지사항 상세 보기
	public String noticeView(Model model, int noticeNum) {
		sqlSession.update("com.green.sandme.board.dao.noticeDao.updateViewCnt", noticeNum);
		NoticeVo nVo = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.viewNotice", noticeNum);
		model.addAttribute("nVo", nVo);
		return "board/notice/noticeView";
	}
	
	@RequestMapping("/noticeUpdateForm") //공지사항 수정 폼
	public String noticeUpdateForm(Model model, int noticeNum) {
		NoticeVo nVo = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.viewNotice", noticeNum);
		model.addAttribute("nVo", nVo);
		return "board/notice/noticeUpdate";
	}
	
	@RequestMapping("/noticeUpdate") //공지사항 수정		
    public String updateNotice(NoticeVo nVo) {
    	sqlSession.update("com.green.sandme.board.dao.noticeDao.updateNotice", nVo);
    	return "forward:noticeView";
    }
	
	@RequestMapping("/noticeDelete") //공지사항 삭제
	public String deleteNotice(int noticeNum) {
		sqlSession.delete("com.green.sandme.board.dao.noticeDao.deleteNotice", noticeNum);
		return "redirect:noticeList";
	}
}