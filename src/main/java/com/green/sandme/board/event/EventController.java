package com.green.sandme.board.event;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.sandme.board.event.vo.EventVo;
import com.green.sandme.paging.spVo;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
    public String eventWrite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    
		EventVo eVo = new EventVo();
		
		eVo.setEventMember(request.getParameter("memberName"));
		eVo.setEventTitle(request.getParameter("eventTitle"));
		eVo.setEventContent(request.getParameter("eventContent"));
		
		String savePath = "src/main/resources/static/img"; //이미지 저장 경로
		int uploadFileSize = 100*1024*1024; //이미지 합계 파일 사이즈
		String encType = "UTF-8";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, uploadFileSize, encType, new DefaultFileRenamePolicy());
			
			@SuppressWarnings("unchecked")
			Enumeration<String> fileNames = multi.getFileNames(); //파일 이름을 배열 형태로 가져온다.
			
			while (fileNames.hasMoreElements()) {
				String file = fileNames.nextElement(); //파일 이름이 아니라 전송된 객체 이름을 가져옴
				String fileName = multi.getFilesystemName(file); //실제 파일 이름을 가져온다.
				
				String originFileName = multi.getOriginalFileName(file);
				
				out.println("업로드된 파일명 : " + fileName + "<br>");
				out.println("원본 파일명 : " + originFileName + "<br>");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
//    	sqlSession.insert("com.green.sandme.board.dao.eventDao.insertEvent", eVo);
    	
    	return "redirect:eventList";
    }

//	@RequestMapping("/noticeView") //공지사항 상세 보기
//	public String noticeView(Model model, int noticeNum) {
//		int totalCnt = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.selectNoticeCnt");
//		
//		sqlSession.update("com.green.sandme.board.dao.noticeDao.updateViewCnt", noticeNum); //조회수 증가 기능
//		NoticeVo nVo = sqlSession.selectOne("com.green.sandme.board.dao.noticeDao.viewNotice", noticeNum);
//		
//		model.addAttribute("notice", "view");
//		model.addAttribute("totalCnt", totalCnt);
//		model.addAttribute("nVo", nVo);	
//		return "board/notice";
//	}
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