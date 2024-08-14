<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.PersonVo" %>

<%
	PersonVo personVo = (PersonVo)request.getAttribute("personVo");

	System.out.println(personVo.getName());
	
%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호부</h1>

	<h2>전화번호-수정폼</h2>
	
	<p>수정할 항목을 입력한 후 수정버튼을 클릭해 주세요.</p>
	
	<form action="/phonebook2/pbc" method="get">
		<div>
		<label for="txt-name">이름(name):</label>
		<input id="txt-name" type="text" name="name" value="<%=personVo.getName()%>" placeholder="이름">
		</div>
		
		<div>
		<label for="txt-hp">핸드폰(hp):</label>
		<input id="txt-hp" type="text" name="hp" value="<%=personVo.getHp()%>" placeholder="전화번호">
		</div>
		
		<div>
		<label for="txt-company">회사(company):</label>
		<input id="txt-company" type="text" name="company" value="<%=personVo.getCompany()%>" placeholder="회사번호">
		</div>
		
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="no" value="<%=personVo.getPersonId()%>">
		<br>
		
		<button type="submit">수정(전송)</button>
		
	</form>
	
	<br><br>
	<a href="#">리스트로 가기</a>
	
</body>
</html>