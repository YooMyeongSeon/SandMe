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
	
	@Transactional //회원가입 서비스
	public void RegisterUser(@Param("mVo") memberVo mVo) throws Exception {
		sqlSession.insert("com.green.sandme.member.dao.memberDao.RegisterMember", mVo);
	}

	@Override
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
	public void UpdateUser(memberVo mVo) throws Exception{
		sqlSession.update("com.green.sandme.member.dao.memberDao.UpdateUser", mVo);
	}
		

	//회원정보 정보 출력
	public memberVo UserInfo(int memberNum) throws Exception{
		return sqlSession.selectOne("com.green.sandme.member.dao.memberDao.UserInfo",memberNum);
	}

	//회원 탈퇴
	@Transactional
	public void DeleteUser(int memberNum) throws Exception{
		sqlSession.delete("com.green.sandme.member.dao.memberDao.DeleteUser",memberNum);
	}
	

	//회원 아이디 중복확인
	public int checkUserId(String memberEmail) throws Exception{
		int result = sqlSession.selectOne("com.green.sandme.member.dao.memberDao.checkUserId",memberEmail);
		return result;
	}















}