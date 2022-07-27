package com.green.sandme.member;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	public String joinForm() {	
		return "/joinform";
	}
	
	//회원가입 처리	  
	@PostMapping("/joinProcess")
	public RedirectView RegisterUser(
					            @RequestParam("memberEmail") String memberEmail,
					            @RequestParam("memberPwd") String memberPwdout,
					            @RequestParam("memberTel") String memberTel,
					            @RequestParam("memberName") String memberName) throws Exception{

		 String memberPwd = bcryptPasswordEncoder.encode(memberPwdout);
		 
		 memberVo mVo = new memberVo();
				 
		 mVo.setMemberEmail(memberEmail);
		 mVo.setMemberPwd(memberPwd);
		 mVo.setMemberTel(memberTel);
		 mVo.setMemberName(memberName);
		 
		 memberService.RegisterUser(mVo);
		 
		 String url = "/registerPage/"+ memberEmail;

	     return new RedirectView(url);
	}
	 
	//회원가입 완료 페이지
	@GetMapping("/registerPage/{memberEmail}")
	public String RegisterPage(@PathVariable("memberEmail")String memberEmail, Model model) throws Exception {
	
//		ModelAndView mav = new ModelAndView();
		memberVo mVo = memberService.RegisterPage(memberEmail);
		
//		mav.addObject("mVo", mVo);
//		mav.setViewName("/registerPage");
		
		model.addAttribute("mVo", mVo);
		return "registerPage";
    }
	
	//로그인 화면
	@GetMapping("/login")
	public String loginForm() {
		return "/loginform";
	}

}
