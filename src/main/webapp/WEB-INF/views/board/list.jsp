<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>
	<table class="table">
		<tr>
			<th width="5%">번호</th>
			<th width="55%">제목</th>
			<th width="10%">작성자</th>
			<th width="20%">등록일</th>
			<th width="10%">조회수</th>
		</tr>
		<c:forEach var="board" items="${list}">
			<tr>
				<th>${board.num}</th>
				<th><a href="<%=request.getContextPath()%>/board/display?num=${board.num}">${board.title}</a></th>
				<th>${board.writer}</th>
				<th>${board.registered}</th>
				<th>${board.views}</th>
			</tr>
		</c:forEach>
	</table>
	<ul class="pagination" style="justify-content: center;">
   																	<!-- 이전가기버튼 -->
    <c:if test="${pageMaker.prev}">
        <li class="page-item">
            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.startPage-1}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}">Previous</a>
        </li>
    </c:if>
    <!-- forEach(하나씩중가하는속성)는 스타트 페이지에서부터 엔드페이지까지 해당하는 값까지 하나씩 증가하는 작업   -->
    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="index">
       <!-- 페이지에서 숫자 클릭하면 파랑색 색깔이 클릭한것으로 옮겨지는것  -->
        <c:if test="${pageMaker.criteria.page ==index}">
	        <li class="page-item active">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}">${index}</a>
	        </li>
        </c:if>
        
        <c:if test="${pageMaker.criteria.page !=index }">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}">${index}</a>
	        </li>
        </c:if>
        
    </c:forEach>
    <c:if test="${pageMaker.next}">
        <li class="page-item">
            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.endPage+1}&type=${pageMaker.criteria.type}&search=${pageMaker.criteria.search}">Next</a>
        </li>
    </c:if>
	</ul>
	<form class="float-left" method="get" action="<%=request.getContextPath()%>/board/list">
		<select name="type" class="float-left">
			<option value="0">선택</option>
			<option value="1" <c:if test="${pageMaker.criteria.type eq 1}">selected</c:if>>제목</option>
			<option value="2" <c:if test="${pageMaker.criteria.type eq 2}">selected</c:if>>내용</option>
			<option value="3" <c:if test="${pageMaker.criteria.type eq 3}">selected</c:if>>작성자</option>
		</select>
		<input type="text" name="search" class="float-left" value="${pageMaker.criteria.search}" >
		<button type="submit" class="btn btn-outline-success float-left">검색</button>
	</form>
	
	
	<a href="<%=request.getContextPath()%>/board/register">
		<button type="button" class="btn btn-outline-success float-right">등록</button>
	</a>
</body>
</html>




