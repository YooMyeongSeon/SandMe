package com.green.sandme.pay.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
		
		
	@RequestMapping("/kakaopay")
	@ResponseBody
	public String kakaopay() {  // 값 가져오기
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
				
				String param = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&"
						+ "item_name=sandme&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&"
						+ "approval_url=https://developers.kakao.com/success&v&cancel_url=https://developers.kakao.com/cancel";  //url은 수정
				
				OutputStream out = server.getOutputStream(); // outputStream 을 통해 실제 정보를 줄 수 있음 내보냄
				DataOutputStream data = new DataOutputStream(out);
				data.writeBytes(param); // 문자를 byte로 형 번환
				data.flush();
				data.close();
				
				int result = server.getResponseCode(); // 데이터를 보낸 것에 대한 결과를 int result로 받음
				
				InputStream in; // 받음
				
				if(result == 200) {  // http 에서 정상적인 통신을 뜻하는 코드 = 200
					in = server.getInputStream();
				}else {
					in = server.getErrorStream();
				}
				
				// 받아온 데이터를 읽어줌
				InputStreamReader reader = new InputStreamReader(in);
				
				// 받아온 데이터를 형 변환
				BufferedReader bufReader = new BufferedReader(reader);
				
				
				return bufReader.readLine();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		return "{\"result\":\"NO\"}";
	}
		
		
	}
}
