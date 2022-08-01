package com.green.sandme.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.sandme.member.service.memberService;
import com.green.sandme.member.vo.memberVo;

@Controller
public class MemberController {
	
	@Resource
	private memberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	//관리자 로그인 페이지 이동
	@GetMapping("/adminPage")             //세션정보확인
	public String adminPage(Authentication authentication) {
		if(authentication != null) {  //세션에 로그인 정보가 있는지 확인
			memberVo mVo = (memberVo) authentication.getPrincipal(); //로그인 정보를 가져와서 멤버vo로 정보를 변환해서 저장
			if(mVo.getAdmin().equals("Y")) { 
				return "/adminPage";
			}
		}
		return "redirect:/";
	}
	
	
	
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
//	@PostMapping("/joinProcess")
//	public String RegisterUser(@RequestParam("memberEmail") String memberEmail, 
//			@RequestParam("memberPwd") String memberPwdout,
//			@RequestParam("memberTel") String memberTel,
//			@RequestParam("memberName") String memberName,
//			Model model) throws Exception{
//
//		 String memberPwd = bcryptPasswordEncoder.encode(memberPwdout);
//		 
//		 //아이디 중복체크
//		 int idResult = memberService.checkUserId(memberEmail);
//		 
//		 memberVo mVo = new memberVo();
//				 
//		 mVo.setMemberEmail(memberEmail);
//		 mVo.setMemberPwd(memberPwd);
//		 mVo.setMemberTel(memberTel);
//		 mVo.setMemberName(memberName);
//		 
//		 try {
//			 if(idResult == 0) {
//				 memberService.RegisterUser(mVo);
//				 String url = "/registerPage/"+ memberEmail;
//                 return new RedirectView(url);
//			 }else if(idResult == 1) {
//				 String fail = "/join";
//				 return new RedirectView(fail);
//			 }
//		 }catch(Exception e) {
//			 e.printStackTrace();
//		 }
//		 
//
//		 model.addAttribute("memberName", memberName);
//		 model.addAttribute("chapter", "chapter03");
//		 return "joinForm";
//	}
//	
//	//로그인 폼으로 이동
//	@GetMapping("/loginForm")
//	public String loginForm() {
//		return "loginForm";
//	}
	
  
	//회원정보 수정 처리
//	@GetMapping("/updatepro")
//	public RedirectView UpdateUser(
//								@RequestParam("memberNum") int memberNum,
//								@RequestParam("memberEmail") String memberEmail,
//								@RequestParam("memberPwd") String memberPwdout,
//								@RequestParam("memberTel") String memberTel,
//								@RequestParam("memberName") String memberName) throws Exception {
//		 
//
//		
//		 System.out.println(memberName);
//		 System.out.println(memberNum);
//		 System.out.println(memberPwdout);
//		 
//		 String memberPwd = bcryptPasswordEncoder.encode(memberPwdout);
//		 
//		 memberVo mVo = new memberVo();
//				
//		 mVo.setMemberNum(memberNum);
//		 mVo.setMemberEmail(memberEmail);
//		 mVo.setMemberPwd(memberPwd);
//		 mVo.setMemberTel(memberTel);
//		 mVo.setMemberName(memberName);
//		 
//		 memberService.UpdateUser(mVo);
//		 
//		 String url = "/";
//
//	     return new RedirectView(url);
//	}	  
  
	  
	//정보변경페이지 (정보출력)
//	 @GetMapping("/updateUser/{memberNum}")
//	 public String UserInfo(@PathVariable("memberNum")int memberNum, Model model) throws Exception {
//		 
//		 memberVo mVo = memberService.UserInfo(memberNum);
//		 
//		 model.addAttribute("mVo",mVo);
//	    
//		return "updateUser.html";
//	 }
//	  
//	 //회원탈퇴 페이지
//	 @GetMapping("/deleteform")
//	 public String Deleteform() {
//		 return "/deleteform";
//	 }
	  
//	 //회원 탈퇴
//	 @GetMapping("/deleteProcess")
//	 public RedirectView DeleteUser(@RequestParam("memberNum") int memberNum) throws Exception{
//
//		 memberService.DeleteUser(memberNum);
//		 
//		 String url = "/";
//
//	     return new RedirectView(url);
//	 }
	 
	 //회원 아이디 중복여부 (Ajax 비동기 방식)
	 @PostMapping("/idChk")
//	 @ResponseBody
	 public String checkUserId(String memberEmail) throws Exception{
	
		 int result = memberService.checkUserId(memberEmail);
		 if(result != 0) {
			 return "fail";
		 } else {
			 return "success";
		 }
	 }
 
	 
	 

}