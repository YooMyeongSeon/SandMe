package com.green.sandme.map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MapController {
	
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/map")
	public String mapPage(@RequestParam("map")String map, Model model) {		
		if (map == null || map.equals("address")) {
			model.addAttribute("map", "address");
		} else {
			model.addAttribute("map", map);
		}

		return "map";
	}
	
	
	
	
}
