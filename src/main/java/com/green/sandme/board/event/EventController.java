package com.green.sandme.board.event;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.sandme.board.event.vo.EventVo;
import com.green.sandme.paging.spVo;

@Controller
public class EventController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/eventList") //이벤트 목록 보기
	public String eventList(HttpServletRequest request, Model model) {
		String _section = request.getParameter("section");
		String _page = request.getParameter("page");
		
		int section = Integer.parseInt((_section == null) ? "1" : _section);
		int page = Integer.parseInt((_page == null) ? "1" : _page);
		
		spVo spVo = new spVo();
		
		spVo.setSection(section);
		spVo.setPage(page);
		
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.eventDao.selectEventCnt");
		List<EventVo> eventList = sqlSession.selectList("com.green.sandme.board.dao.eventDao.selectEventListByPaging", spVo);
		
		model.addAttribute("event", "list");
		model.addAttribute("section", section);
		model.addAttribute("page", page);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("eventList", eventList);
		return "board/event";
	}

	@RequestMapping("/eventWriteForm") //이벤트 작성 폼
    public String eventWriteForm(Model model) {
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.eventDao.selectEventCnt");
		
		model.addAttribute("event", "write");
		model.addAttribute("totalCnt", totalCnt);
		return "board/event";
    }
	
	@PostMapping("/eventWrite") //이벤트 작성
    public String eventWrite(@RequestParam("memberName") String memberName,
    		@RequestParam("eventTitle") String eventTitle,
    		@RequestParam("eventContent") String eventContent,
    		@RequestParam("eventSomeFile") MultipartFile eventSomeFile,
    		@RequestParam("eventFile") MultipartFile eventFile) throws IOException {
		EventVo eVo = new EventVo();
		
		eVo.setEventMember(memberName);
		eVo.setEventTitle(eventTitle);
		eVo.setEventContent(eventContent);
		eVo.setEventSomeFile(eventSomeFile.getBytes());
		eVo.setEventFile(eventFile.getBytes());
		
		sqlSession.insert("com.green.sandme.board.dao.eventDao.insertEvent", eVo);
		
    	return "redirect:eventList";
    }

	@RequestMapping("/eventView") //이벤트 상세 보기
	public String eventView(Model model, int eventNum) throws UnsupportedEncodingException {
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.eventDao.selectEventCnt");

		EventVo eVo = sqlSession.selectOne("com.green.sandme.board.dao.eventDao.viewEvent", eventNum);
		
		byte[] eventSomeFilebyte = eVo.getEventSomeFile();
		byte[] eventSomeFilebyteEnc64 = Base64.encodeBase64(eventSomeFilebyte);
		String eventSomeFile = new String(eventSomeFilebyteEnc64, "UTF-8");
		
		byte[] eventFilebyte = eVo.getEventFile();
		byte[] eventFilebyteEnc64 = Base64.encodeBase64(eventFilebyte);
		String eventFile = new String(eventFilebyteEnc64, "UTF-8");
		
		model.addAttribute("eventFile", eventFile);
		model.addAttribute("eventSomeFile", eventSomeFile);
		model.addAttribute("event", "view");
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("eVo", eVo);
		return "board/event";
	}
//	
//	@RequestMapping("/noticeUpdateForm") //공지사항 수정 폼
//	public String noticeUpdateForm(Model model, int noticeNum) {
//		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.selectNoticeCnt");
//		
//		NoticeVo nVo = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.viewNotice", noticeNum);
//
//		model.addAttribute("notice", "update");
//		model.addAttribute("totalCnt", totalCnt);
//		model.addAttribute("nVo", nVo);
//		return "board/notice";
//	}
//	
//	@RequestMapping("/noticeUpdate") //공지사항 수정		
//    public String updateNotice(NoticeVo nVo) {
//    	sqlSession.update("com.green.sandme.board.dao.noticeDao.updateNotice", nVo);
//    	
//    	return "forward:noticeView";
//    }
//	
//	@RequestMapping("/noticeDelete") //공지사항 삭제
//	public String deleteNotice(int noticeNum) {
//		sqlSession.delete("com.green.sandme.board.dao.noticeDao.deleteNotice", noticeNum);
//		
//		return "redirect:noticeList";
//	}
}