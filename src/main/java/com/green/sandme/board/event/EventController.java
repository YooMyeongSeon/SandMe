package com.green.sandme.board.event;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.sandme.board.event.vo.EventVo;

@Controller
public class EventController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/eventList") //이벤트 목록 보기
	public String eventList(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.eventDao.selectEventCnt");
		List<EventVo> eventList = sqlSession.selectList("com.green.sandme.board.dao.eventDao.selectEventList");
				
		model.addAttribute("event", "list");
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
		eVo.setInsertEventSomeFile(eventSomeFile.getBytes());
		eVo.setInsertEventFile(eventFile.getBytes());
		
		sqlSession.insert("com.green.sandme.board.dao.eventDao.insertEvent", eVo);
		
    	return "redirect:eventList";
    }

	@RequestMapping("/eventView") //이벤트 상세 보기
	public String eventView(Model model, int eventNum) throws UnsupportedEncodingException {
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.eventDao.selectEventCnt");

		EventVo eVo = sqlSession.selectOne("com.green.sandme.board.dao.eventDao.viewEvent", eventNum);

		model.addAttribute("event", "view");
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("eVo", eVo);
		return "board/event";
	}
	
	@RequestMapping("/eventUpdateForm") //이벤트 수정 폼
	public String eventUpdateForm(Model model, int eventNum) {
		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.eventDao.selectEventCnt");
		
		EventVo eVo = sqlSession.selectOne("com.green.sandme.board.dao.eventDao.viewEvent", eventNum);

		model.addAttribute("event", "update");
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("eVo", eVo);
		return "board/event";
	}
	
	@RequestMapping("/eventUpdate") //이벤트 수정		
    public String updateNotice(@RequestParam("eventNum") int eventNum,
    		@RequestParam("memberName") String memberName,
    		@RequestParam("eventTitle") String eventTitle,
    		@RequestParam("eventContent") String eventContent,
    		@RequestParam("eventSomeFile") MultipartFile eventSomeFile,
    		@RequestParam("eventFile") MultipartFile eventFile) throws IOException {
		EventVo eVo = new EventVo();
		
		eVo.setEventNum(eventNum);
		eVo.setEventMember(memberName);
		eVo.setEventTitle(eventTitle);
		eVo.setEventContent(eventContent);
		eVo.setInsertEventSomeFile(eventSomeFile.getBytes());
		eVo.setInsertEventFile(eventFile.getBytes());
		
    	sqlSession.update("com.green.sandme.board.dao.eventDao.updateEvent", eVo);
    	
    	return "forward:eventView";
    }
	
	@RequestMapping("/eventDelete") //이벤트 삭제
	public String deleteEvent(int eventNum) {
		sqlSession.delete("com.green.sandme.board.dao.eventDao.deleteEvent", eventNum);
		
		return "redirect:eventList";
	}
}