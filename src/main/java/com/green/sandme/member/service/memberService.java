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
	
	@Transactional
	public void RegisterUser(@Param("mVo") memberVo mVo) throws Exception {
		sqlSession.insert("com.green.sandme.member.dao.memberDao.RegisterUser", mVo);
	}
	
	//회원가입 성공
	public memberVo RegisterPage(String memberEmail) throws Exception{
		return sqlSession.selectOne("com.green.sandme.member.dao.memberDao.RegisterPage", memberEmail);
	}

	@Override
	public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
		memberVo mVo = new memberVo(); 
		try {
			mVo = sqlSession.selectOne("com.green.sandme.member.dao.memberDao.LoginUser", memberEmail); 
			if (mVo == null){
				throw new UsernameNotFoundException("유저 찾을 수 없음");
			}
			
			return mVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mVo;
	}
}