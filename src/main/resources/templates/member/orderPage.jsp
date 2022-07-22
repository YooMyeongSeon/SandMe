<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문하기 페이지</title>
</head>
<body>
<h1>주문하기 페이지</h1>

	<div>
		<h3>배달 정보</h3>
		<table border="1">
			<tr>
				<th>주소</th>
				<td><!-- order_tbl 에서 inner join --></td>
			</tr>
			<tr>
				<th>배달 매장</th>
				<td>${storeList[0].storeName}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${member[0].memberTel}</td>
			</tr>
<!-- 			<tr>
				<th>주문시 요청사항</th>
				<td> <input type="text" name="request"> </td>
			</tr>
			<tr>
				<th>일회용품</th>
				<td></td>
			</tr> -->
		</table>
	</div>

	<div>
		<h3>할인 방법 선택</h3>
		<table border="1">
			<tr>
				<th>쿠폰 사용</th>
				<td> 
					<select>
						<option>쿠폰 1</option>
						<option>쿠폰 2</option>
						<option>쿠폰 3</option>
					</select>
				</td>
			</tr>
		</table>
	</div>

	<div>
		<h3>결제 수단 선택</h3>
		<table border="1">
			<tr>
				<th>현금 영수증 신청</th>
				<td>
					<button>발급안함</button>
					<button>개인소득공제</button>
					<button>사업자증빙용</button>
				</td>
			</tr>
			<tr>
				<th>다른 결제 수단</th>
				<td> 
					<button>신용카드</button>
					<button>카카오페이</button>
					<button>페이코</button>
				 </td>
			</tr>
		</table>
	</div>
	
	<hr>
	
	<div>
		<table border="1">
			<c:forEach items="${cartList}" var="cartList">
				<tr>
					<th>메뉴</th>
					<td>${cartList.cartMenu}</td>
				</tr>
				<tr>
					<th>수량</th>
					<td>${cartList.cartCount}</td>
				</tr>
				<tr>
					<th>가격</th>
					<td></td>
				</tr>
			</c:forEach>
		</table>
	</div>

<h3>총 결제 금액</h3>


</body>
</html>