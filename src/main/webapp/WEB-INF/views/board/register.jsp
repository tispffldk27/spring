<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>
	<form action="<%=request.getContextPath() %>/board/register" method="post">
		<div class="container-fluid" style="margin-top:80px;">
			<div class="form-group">
			  <label for="title">제목</label>
			  <input type="text" class="form-control" name="title" value="" >
			</div>
			<div class="form-group">
			  <label for="title">작성자</label>
			  <input type="text" class="form-control" name="writer" value="${user.id}" readonly>
			</div>
			<div class="form-group">
			  <label>등록일</label>
			  <input type="text" class="form-control" name="" value="" readonly>
			</div>
			<div class="form-group">
			  <label>조회수</label>
			  <input type="text" class="form-control" name="" value="0" readonly>
			</div>
			<div class="form-group">
			  <label>내용</label>
			  <textarea rows="10" class="form-control" name="contents" ></textarea>
			</div>
			<div class="form-group">
			  <label>첨부파일</label>
			  <input type="text" class="form-control" name="file" value="" >
			</div>
			<a href="<%=request.getContextPath()%>/board/list">
				<button type="button" class="btn btn-outline-success">목록</button>
			</a>
			<button type="submit" class="btn btn-outline-success">등록하기</button>
		</div>
	</form>
</body>
</html>