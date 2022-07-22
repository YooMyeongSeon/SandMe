<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 장바구니 페이지</title>
</head>
<body>
<div>
	<H1>장바구니 페이지</H1>
	<c:choose>
			<c:when test="${! empty cartList[0].cartNum}">
				<form action="/order?memberNum=${cartList[0].memberNum}" method="post">
		      	<p>${cartList[0].memberNum}</p>
		
				 	 <table border="1">
				                <tr>
				                	<th></th>
				                    <th>회원 이름</th>
				                    <th>장바구니 메뉴</th>
				                    <th>장바구니 수량</th>
				                </tr>
				
				             <c:forEach items="${cartList}" var="cartList">

					                <tr>
						                	<td>${cartList.memberName}</td>
						                	<td>${cartList.cartMenu}</td>
						                	<td>${cartList.cartCount}</td>
						 				    <td>
						                		<a href="/deleteCart?cartNum=${cartList.cartNum}&memberNum=${cartList.memberNum}" role="button" class="cartDelete">[삭제]</a> <br>
						                		<a href="/updateCart?cartNum=${cartList.cartNum}" role="button">[수정]</a>
						                	</td>
						                </tr>   

					         </c:forEach>
					 
			       		 </table>
			 			<br>
			 			<input type="submit" value="결제">	
		
			 	</form>

			</c:when>
			<c:when test="${empty cartList[0].cartNum}">
					<p>목록 없음</p>
					<a href="메뉴 선택 페이지 이동">[메뉴 선택]</a>
			</c:when>

	</c:choose>

</div>

</body>
</html>