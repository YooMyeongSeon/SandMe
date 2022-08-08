package com.green.sandme.board.event;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/eventList")		
    public String openEventList() {
    	return "board/event/eventList";
    }
}
