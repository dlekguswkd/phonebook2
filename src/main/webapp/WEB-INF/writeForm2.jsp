<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호부</h1>

	<h2>전화번호-등록폼</h2>
	
	<p>아래의 항목을 입력한 후 등록버튼을 클릭해 주세요.</p>
	
	<!-- 여기에서 action은 어느 사이트로 갈지를 쓰는칸 
				name, value는 사이트 ? 뒤에 오는값 넣는칸 -->
	<form action="http://localhost:8080/phonebook2/pbc" method="get">
		<div>
		<label for="txt-name">이름(name):</label>
		<input id="txt-name" type="text" name="name" value="" placeholder="이름">
		</div>
		
		<div>
		<label for="txt-hp">핸드폰(hp):</label>
		<input id="txt-hp" type="text" name="hp" value="" placeholder="전화번호">
		</div>
		
		<div>
		<label for="txt-company">회사(company):</label>
		<input id="txt-company" type="text" name="company" value="" placeholder="회사번호">
		</div>
		
		<!-- 넣는게 들어가야하는데(사이트 ?뒤에 action=insert 연결) 사람들에게 안보여줘도 되기때문에 hidden 이라고 하고 넣어주기 
			 개발할땐 안보이니까 hidden 대신 text로 두고 해보기 (form 안에 넣는거 잊지말기)-->
		<input type="hidden" name="action" value="insert">
		<br>
		
		<!-- form 안에 넣는거 잊지말기 -->
		<button type="submit">등록(전송)</button>
		
	</form>
	
	<br><br>
	<!-- form 밖에 만들기 -->
	<a href="/phonebook2/pbc?action=list">리스트로 가기</a>
	
</body>
</html>