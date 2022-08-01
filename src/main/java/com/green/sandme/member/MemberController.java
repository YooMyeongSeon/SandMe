package com.green.sandme.member;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.sandme.member.service.memberService;
import com.green.sandme.member.vo.memberVo;

@Controller
public class MemberController {
	
	@Resource
	private memberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	//회원가입 페이지 이동
	@GetMapping("/join")
	public String joinForm(Model model) {
		model.addAttribute("chapter", "chapter01");
		return "/joinForm";
	}
	
	//약관동의 후 챕터2로 이동
	@PostMapping("/join")
	public String joinFormChapter02(Model model) {
		model.addAttribute("chapter", "chapter02");
		return "/joinForm";
	}
	
	//회원가입 처리	  
	@PostMapping("/joinProcess")
	public String RegisterUser(@RequestParam("memberEmail") String memberEmail, 
			@RequestParam("memberPwd") String memberPwdout,
			@RequestParam("memberTel") String memberTel,
			@RequestParam("memberName") String memberName,
			Model model) throws Exception{

		 String memberPwd = bcryptPasswordEncoder.encode(memberPwdout);
		 
		 memberVo mVo = new memberVo();
				 
		 mVo.setMemberEmail(memberEmail);
		 mVo.setMemberPwd(memberPwd);
		 mVo.setMemberTel(memberTel);
		 mVo.setMemberName(memberName);
		 
		 memberService.RegisterUser(mVo);
		 
		 model.addAttribute("memberName", memberName);
		 model.addAttribute("chapter", "chapter03");
		 return "joinForm";
	}
	
	//로그인 폼으로 이동
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}

}