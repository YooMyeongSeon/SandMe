package com.green.sandme.pay.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class PayController {
	
	
	// 클라이언트의 요청을 처리하기 위한 클래스

	@Controller  
	public class CartListController {
		
	@Autowired
	private SqlSession sqlSession;
		
		
	@RequestMapping("")
	@ResponseBody
	public String kakaopay() {
		try {
			URL payUrl = new URL("http://kapi.kakao.com/v1/payment/ready");
			// 사용자와 카카오페이를 연결시켜주는 역할 - 서버 연결
			try {
				HttpURLConnection server = (HttpURLConnection) payUrl.openConnection();  // 강제 형 변환
				server.setRequestMethod("POST");
				// 프로퍼티 하나씩 설정 기능
				server.setRequestProperty("Authorization", "KakaoAK 337d6a7354bfe683bd496a9480eb05de");
				server.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
				server.setDoOutput(true);  // 서버에 넣어줄 것이 있을 때 사용
				// 넣어줄 것이 있음에도 input을 쓰지 않는 이유는
				//connection은 기본적으로 input이 true
				
				String param = "item_name=item_name&item_code=item_code&quantity=quantity&total_amount=total_amount&approval_url=approval_url&cancel_url=cancel_url&fail_url=fail_url";
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		return "";
	}
		
		
	}
}
