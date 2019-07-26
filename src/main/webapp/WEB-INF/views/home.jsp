<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
	<script src="//code.jquery.com/jquery-3.4.1.js"></script>	
	<script type="text/javascript">
	$(document).ready(function(){
		$('input[name=id]').focus();
	})
	</script>
	
	<title>로그인</title>
	<style>
	*{
		margin: 0;
		padding : 0;
	}
	.main{
		margin-top:50px;
	}
	.row{
		margin: 5px 0px;
	}
	.fab.fa-amazon{
		font-size: 100px;
		color: red;
	}
	</style>
</head>
<body>
	<div>
		<div class="offset-4 col-4 border border-dark mt-5 clearFix">
			<h1 class="text-center">로그인</h1>
			<form method="post" action="<%=request.getContextPath()%>/signin">
				<div class="row">
					<label class="col-4">아이디</label>
					<input type="text"class="form-control col-7" placeholder="아이디" name="id">
				</div>
				<div class="row">
					<label class="col-4">비밀번호</label>
					<input type="password"class="form-control col-7" placeholder="비밀번호" name="pw">
				</div>
				<div class="offset-8 col-3 clearfix p-0">
					<button class="btn btn-primary float-right">로그인</button>
				</div>
				<a href="<%=request.getContextPath()%>/password/find" class="float-right">비밀번호 찾기</a>
			</form>
		</div>
	</div>
</body>
</html>