package com.green.sandme.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		 
		 //아이디 중복체크
		 int idResult = memberService.checkUserId(memberEmail);
		 
		 memberVo mVo = new memberVo();
				 
		 mVo.setMemberEmail(memberEmail);
		 mVo.setMemberPwd(memberPwd);
		 mVo.setMemberTel(memberTel);
		 mVo.setMemberName(memberName);
		 
		 try {
			 if(idResult == 0) {
				 memberService.RegisterUser(mVo);
				 String url = "/registerPage/"+ memberEmail;
                 return new RedirectView(url);
			 }else if(idResult == 1) {
				 String fail = "/join";
				 return new RedirectView(fail);
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 String url = "/registerPage/"+ memberEmail;
         return new RedirectView(url);
	}
	 
	//회원가입 완료 페이지
	@GetMapping("/registerPage/{memberEmail}")
	public String RegisterPage(@PathVariable("memberEmail")String memberEmail, Model model) throws Exception {
	
		memberVo mVo = memberService.RegisterPage(memberEmail);
		
		model.addAttribute("mVo", mVo);
		return "registerPage";
    }
	
	//마이페이지로 이동
	@GetMapping("/mypage")
	public String mypage() {	
		return "/mypage";
	}	
	
	
	//로그인 화면
	@GetMapping("/login")
	public String loginForm() {
		System.out.println("로그인 성공");
		return "/loginform";
	}
	
	
	//회원정보 변경 페이지 이동  
//	@GetMapping("/updateUser")
//	public String UpdateUser() {
//		return "/updateUser";
//	}
  
	//회원정보 수정 처리
	@GetMapping("/updatepro")
	public RedirectView UpdateUser(
								@RequestParam("memberNum") int memberNum,
								@RequestParam("memberEmail") String memberEmail,
								@RequestParam("memberPwd") String memberPwdout,
								@RequestParam("memberTel") String memberTel,
								@RequestParam("memberName") String memberName) throws Exception {
		 

		
		 System.out.println(memberName);
		 System.out.println(memberNum);
		 System.out.println(memberPwdout);
		 
		 String memberPwd = bcryptPasswordEncoder.encode(memberPwdout);
		 
		 memberVo mVo = new memberVo();
				
		 mVo.setMemberNum(memberNum);
		 mVo.setMemberEmail(memberEmail);
		 mVo.setMemberPwd(memberPwd);
		 mVo.setMemberTel(memberTel);
		 mVo.setMemberName(memberName);
		 
		 memberService.UpdateUser(mVo);
		 
		 String url = "/";

	     return new RedirectView(url);
	}	  
  
	  
	//정보변경페이지 (정보출력)
	 @GetMapping("/updateUser/{memberNum}")
	 public String UserInfo(@PathVariable("memberNum")int memberNum, Model model) throws Exception {
		 
		 memberVo mVo = memberService.UserInfo(memberNum);
		 
		 model.addAttribute("mVo",mVo);
	    
		return "updateUser.html";
	 }
	  
	 //회원탈퇴 페이지
	 @GetMapping("/deleteform")
	 public String Deleteform() {
		 return "/deleteform";
	 }
	  
	 //회원 탈퇴
	 @GetMapping("/deleteProcess")
	 public RedirectView DeleteUser(@RequestParam("memberNum") int memberNum) throws Exception{

		 memberService.DeleteUser(memberNum);
		 
		 String url = "/";

	     return new RedirectView(url);
	 }
	 
	 //회원 아이디 중복여부 (Ajax 비동기 방식)
	 @PostMapping("/idChk")
	 @ResponseBody
	 public String checkUserId(String memberEmail) throws Exception{
	
		 int result = memberService.checkUserId(memberEmail);
		 if(result != 0) {
			 return "fail";
		 } else {
			 return "success";
		 }
	 }
 
	 
	 

}
