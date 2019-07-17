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
	<form action="<%=request.getContextPath()%>/board/modify" method="post">
	<input type="hidden" name="num" value="${board.num}">
	<div class="container-fluid" style="margin-top:80px">
	<div class="form-gr oup">
	  <label>제목</label>
	  <input type="text" class="form-control" name="title" value="${board.title}" >
	</div>
	<div class="form-group">
	  <label>작성자</label>
	  <input type="text" class="form-control" name="writer" value="${board.writer}" readonly>
	</div>
	<div class="form-group">
	  <label>등록일</label>
	  <input type="text" class="form-control" name="registered" value="${board.registered}" readonly>
	</div>
	<div class="form-group">
	  <label>조회수</label>
	  <input type="text" class="form-control" name="views" value="${board.views}" readonly>
	</div>
	<div class="form-group">
	  <label>내용</label>
	  <textarea rows="10" class="form-control" name="contents" >${board.contents}</textarea>
	</div>
		<div class="form-group">
	  <label>첨부파일</label>
	  <input type="text" class="form-control" name="file" value="${board.file}" readonly>
	</div>
	
	<a href="<%=request.getContextPath()%>/board/display?num=${board.num}">
	<button type="button" class="btn btn-outline-primary">취소</button>
	</a>
	<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}">
	<button  class="btn btn-outline-primary">수정하기</button>
	</a>

	</form>
 <br>
	
	
	</div>
</body>
</html>