<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead style="background: gray;">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>등록일자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${notices}" var="notice" varStatus="status">
				<tr>
					<td style="text-align: center;">${notice.noticeSeq}</td>
					<td>${notice.subject}</td>
					<td>${notice.content}</td>
					<td>${notice.regDt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>