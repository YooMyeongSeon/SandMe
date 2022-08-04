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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class KakaoPayController {
	


	   @RequestMapping("/kakaopayP")
	   public String kakaopay(@RequestParam("totalPrice") int totalPrice,
			   				  @RequestParam("memberNum") String memberNum) {
		  
		   System.out.println("가격" + totalPrice);
		   System.out.println("사용자 아이디 :" + memberNum);

		  
		   
	      try {
	         URL payUrl = new URL("https://kapi.kakao.com/v1/payment/ready");
	         try {// 카카오 디벨로퍼 단건결제페이지에 있는 양식 그대로 옮김
	            
	            HttpURLConnection server = (HttpURLConnection) payUrl.openConnection(); //서버연결
	            server.setRequestMethod("POST");
	            server.setRequestProperty("Authorization", "KakaoAK 337d6a7354bfe683bd496a9480eb05de" );
	            server.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	            server.setDoOutput(true);
	            
	            // 정보 파라미터
	            String param = "cid=TC0ONETIME" // 가맹점 코드
						+ "&partner_order_id=partner_order_id" // 가맹점 주문번호
						+ "&partner_user_id=partner_user_id" // 가맹점 회원 id
						+ "&item_name=sandme" // 상품명
						+ "&quantity=1" // 상품 수량
						+ "&total_amount="+totalPrice+"" // 총 금액
						+ "&vat_amount=200" // 부가세
						+ "&tax_free_amount=0" // 상품 비과세 금액
						+ "&approval_url=http://localhost:8090/member/kakaopaysuccess/"+memberNum+"" // 결제 성공 시
						+ "&fail_url=http://localhost:8090/member/kakaopayfail/"+memberNum+"" // 결제 실패 시
						+ "&cancel_url=http://localhost:8090/member/kakaopaycancle/"+memberNum+""; // 결제 취소 시

	            
	            System.out.println(param);
	            
	            OutputStream out = server.getOutputStream();   // 주는애
	            DataOutputStream dataOut = new DataOutputStream(out);   //데이터 주는놈
	            dataOut.writeBytes(param);   // 바이트형식으로 데이터를 주고 받는다.
	            dataOut.flush();
	            dataOut.close();
	            
	            int result = server.getResponseCode();
	            
	            System.out.println(result);
	            
	            InputStream in;            // 받는애
	            
	            if(result==200) {
	               in = server.getInputStream();
	               System.out.println("결제성공");
	            }else {
	               in = server.getErrorStream();
	               System.out.println("에러"+in);
	            }
	            
	            
	            InputStreamReader inReader = new InputStreamReader(in); // 받아온 값 읽어주기
	            BufferedReader bufferReader = new BufferedReader(inReader); //형변환이 가능한 친구
	            
	            
	            return bufferReader.readLine(); // 형변환 해서 받아주기
	            
	         } catch (IOException e) {
	            
	            e.printStackTrace();
	         }
	      } catch (MalformedURLException e) {
	         
	         e.printStackTrace();
	      }
	      
	      return "{\"result\":\"NO\"}";
	   }

		
		
	
}
