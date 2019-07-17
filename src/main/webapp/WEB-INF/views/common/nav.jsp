<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul class="nav nav-pills" role="tablist">
    

    <li class="nav-item">
      <a class="nav-link" data-toggle="pill" href="https://github.com/st8324">github</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="pill" href="#menu2">Menu 2</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" data-toggle="pill" 
      style ="right:20px; position:absolute;"
      href="<%=request.getContextPath()%>/signout">로그아웃</a>
    </li>	
  </ul>
