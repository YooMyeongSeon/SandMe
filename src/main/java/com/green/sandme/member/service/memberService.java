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
}