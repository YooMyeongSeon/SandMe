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
		 return "registerPage";
	}
	
	//로그인 화면
	@GetMapping("/login")
	public String loginForm() {
		System.out.println("로그인 성공");
		return "/loginform";
	}

}
