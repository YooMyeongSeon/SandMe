package com.green.sandme.member.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.sandme.member.vo.memberVo;

@Service
public class memberService implements UserDetailsService{
	
	@Autowired
	SqlSession sqlSession;
	
	//아이디 중복확인
	public int checkUserId(String memberEmail) throws Exception{
		int result = sqlSession.selectOne("com.green.sandme.member.dao.memberDao.checkUserId", memberEmail);
		return result;
	}
	
	@Transactional //회원가입
	public void RegisterUser(@Param("mVo") memberVo mVo) throws Exception {
		sqlSession.insert("com.green.sandme.member.dao.memberDao.RegisterMember", mVo);
	}

	@Override //로그인
	public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
		memberVo mVo = new memberVo(); 
		
		try {
			mVo = sqlSession.selectOne("com.green.sandme.member.dao.memberDao.LoginMember", memberEmail); 
			
			if (mVo == null) {
				throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
			}
			
			return mVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mVo;
	}
	
	//회원정보 수정
	@Transactional
	public void UpdateMember(memberVo mVo) throws Exception {
		sqlSession.update("com.green.sandme.member.dao.memberDao.UpdateMember", mVo);
	}

	//회원 탈퇴
	@Transactional
	public void deleteMember(int memberNum) throws Exception {
		sqlSession.delete("com.green.sandme.member.dao.memberDao.DeleteMember", memberNum);
	}
}