package com.green.sandme.board.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.sandme.board.notice.vo.NoticeVo;
import com.green.sandme.paging.spVo;

@Controller
public class NoticeController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/noticeList") //공지사항 목록 보기
	public String noticeList(HttpServletRequest request, Model model) {
		String _section = request.getParameter("section");
		String _page = request.getParameter("page");
		
		int section = Integer.parseInt((_section == null) ? "1" : _section);
		int page = Integer.parseInt((_page == null) ? "1" : _page);
		
		spVo spVo = new spVo();
		
		spVo.setSection(section);
		spVo.setPage(page);
		
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.selectNoticeCnt");
		List<NoticeVo> noticeList = sqlSession.selectList("com.green.sandme.board.dao.noticeDao.selectNoticeListByPaging", spVo);
		
		model.addAttribute("notice", "list");
		model.addAttribute("section", section);
		model.addAttribute("page", page);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("noticeList", noticeList);
		return "board/notice";
	}

	@RequestMapping("/noticeWriteForm") //공지사항 작성 폼
    public String openNoticeWrite(Model model) {
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.selectNoticeCnt");
		
		model.addAttribute("notice", "write");
		model.addAttribute("totalCnt", totalCnt);
		return "board/notice";
    }
	
	@PostMapping("/noticeWrite") //공지사항 작성
    public String insertnotice(NoticeVo nVo) {
    	sqlSession.insert("com.green.sandme.board.dao.noticeDao.insertNotice", nVo);
    	
    	return "redirect:noticeList";
    }
	
	@RequestMapping("/noticeView") //공지사항 상세 보기
	public String noticeView(Model model, int noticeNum) {
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.selectNoticeCnt");
		
		sqlSession.update("com.green.sandme.board.dao.noticeDao.updateViewCnt", noticeNum); //조회수 증가 기능
		NoticeVo nVo = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.viewNotice", noticeNum);
		
		model.addAttribute("notice", "view");
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("nVo", nVo);	
		return "board/notice";
	}
	
	@RequestMapping("/noticeUpdateForm") //공지사항 수정 폼
	public String noticeUpdateForm(Model model, int noticeNum) {
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.selectNoticeCnt");
		
		NoticeVo nVo = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.viewNotice", noticeNum);

		model.addAttribute("notice", "update");
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("nVo", nVo);
		return "board/notice";
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