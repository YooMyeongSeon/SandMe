package com.green.sandme.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.sandme.member.service.memberService;
import com.green.sandme.member.vo.MemberOrderVo;
import com.green.sandme.member.vo.memberVo;
import com.green.sandme.order.vo.OrderMenuVo;
import com.green.sandme.order.vo.OrderVo;

@Controller
public class MemberController {
	
	@Resource
	private memberService memberService;
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	//회원가입 페이지 이동
	@GetMapping("/join")
	public String joinForm(Model model) {
		model.addAttribute("chapter", "chapter01");
		return "/joinForm";
	}
	
	//회원가입 : 약관동의 후 챕터2로 이동
	@PostMapping("/join")
	public String joinFormChapter02(Model model) {
		model.addAttribute("chapter", "chapter02");
		return "/joinForm";
	}
	
	//아이디 중복 확인
	@PostMapping("/idChk")
	@ResponseBody
	public String checkMemberId(String memberEmail) throws Exception {
		int result = memberService.checkUserId(memberEmail);
		
		if(result != 0) {
			return "fail";
		} else {
			return "success";
		}
	}
	
	//회원가입 처리	  
    @PostMapping("/joinProcess")
    public String RegisterMember(@RequestParam("memberEmail") String memberEmail, 
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
	
	//회원정보 페이지
	@GetMapping("/memberInfo")
	public String memberInfo(@RequestParam("memberNum")int memberNum, Model model) {
		memberVo mVo = sqlSession.selectOne("com.green.sandme.member.dao.memberDao.selectMemberByNum", memberNum);
		List<MemberOrderVo> mOVo = sqlSession.selectList("com.green.sandme.member.dao.memberDao.selectOrderByMemberNum", memberNum);
		
		if (!mOVo.isEmpty()) {
			for (int i = 0; i < mOVo.size(); i++) {
				List<OrderMenuVo> oMVo = sqlSession.selectList("com.green.sandme.member.dao.memberDao.selectOrderMenuByMemberNum", mOVo.get(i).getOrderNum());
				mOVo.get(i).setOrderMenu(oMVo);
			}
			
			model.addAttribute("mOVo", mOVo); //주문 내역 전송
		} else {
			model.addAttribute("mOVo", "null"); //주문 내역 전송
		}
		
		model.addAttribute("memberInfo", "info");
		model.addAttribute("mVo", mVo); //회원 정보 전송
		return "memberInfo";
	}
	
	//회원정보 수정 페이지
	@GetMapping("/updateMember")
	public String updateMember(@RequestParam("memberNum")int memberNum, Model model) { 
		memberVo mVo = sqlSession.selectOne("com.green.sandme.member.dao.memberDao.selectMemberByNum", memberNum);
		
		model.addAttribute("memberInfo", "update");
		model.addAttribute("mVo",mVo);
		return "memberInfo";
	}

	//회원정보 수정 처리
	@PostMapping("/updateProcess")
	public String updateMemberProcess(@RequestParam("memberNum") int memberNum,
			@RequestParam("memberEmail") String memberEmail,
			@RequestParam("memberPwd") String memberPwdout,
			@RequestParam("memberTel") String memberTel,
			@RequestParam("memberName") String memberName,
			Model model) throws Exception {
		
		String memberPwd = bcryptPasswordEncoder.encode(memberPwdout);
		 
		memberVo mVo = new memberVo();
				
		mVo.setMemberNum(memberNum);
		mVo.setMemberEmail(memberEmail);
		mVo.setMemberPwd(memberPwd);
		mVo.setMemberTel(memberTel);
		mVo.setMemberName(memberName);
		
		List<OrderVo> oVo = sqlSession.selectList("com.green.sandme.order.dao.OrderDao.selectOrderBymemberNum", memberNum); 
		memberService.UpdateMember(mVo);
		 
		model.addAttribute("memberInfo", "info");
		model.addAttribute("oVo", oVo); //주문 내역 전송
		model.addAttribute("mVo", mVo); //회원 정보 전송
	    return "memberInfo";
	}
	
	//회원 탈퇴 페이지
	@GetMapping("/deleteMember")
	public String deleteMemberForm(@RequestParam("memberNum") int memberNum, Model model) {
		memberVo mVo = sqlSession.selectOne("com.green.sandme.member.dao.memberDao.selectMemberByNum", memberNum);
		
		model.addAttribute("memberInfo", "delete");
		model.addAttribute("mVo",mVo);
		return "memberInfo";
	}
	
	//회원 탈퇴 처리
	@GetMapping("/deleteProcess")
	public String deleteMember(@RequestParam("memberNum") int memberNum) throws Exception {
		memberService.deleteMember(memberNum);
		
		SecurityContextHolder.clearContext();
		return "redirect:/";
	}
}