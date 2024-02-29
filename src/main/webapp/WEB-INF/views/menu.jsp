<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<!-- 네비게이션바 -->
  <nav id="menu" class="navbar navbar-expand-lg">
    <a class="navbar-brand imgbox" href="<c:url value='/'/>">
      <img id="logo" src="img/goldenTimeLogo_2.png" alt="GoldenTime">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
      style="background-color: white;">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <div class="me-auto"></div>
      <ul class="navbar-nav  mb-2 mb-lg-0" style="margin-right: 20px;">
       
        <li class="nav-item active">
          <a class="nav-link" type="button" href="<c:url value='/emrStatus'/>"><b>응급실 현황</b></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" type="button" href="<c:url value='/congestion'/>"><b>혼잡도 예측</b></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" type="button" href="<c:url value='/person'/>"><b>인적자원 재배치</b></a>
        </li>
         <li class="nav-item active">
          <a class="nav-link" type="button" href="<c:url value='/doctor'/>"><b style="font-family: MICEGothic Bold;">응급실 정보</b></a>
        </li>
      </ul>
      <ul class="navbar-nav" style="margin-right: 25px;">
        <li class="nav-item active dropdown">
        <% if(session.getAttribute("ValidMem") != null){ %>
        	<a class="nav-link bi bi-person-circle dropdown-toggle" href="" data-bs-toggle="dropdown" style="float: left; margin-right: 10px;"><b><%=session.getAttribute("name")%></b></a>
        	<ul class="dropdown-menu">
        		<a class="dropdown-item" href="logout">로그아웃</a>
        		<a class="dropdown-item" href="/goldenTime/modify/<%=session.getAttribute("id")%>">회원정보수정</a>
        	</ul>
        <%	} else { %>
          <a class="nav-link bi bi-person-circle" href="<c:url value='login'/>" style="float: left; margin-right: 10px; font-size: 0.9em;">로그인</a>
        <%	} %>
        	<a class="nav-link" href="list" style="float: left; margin-right: 10px; font-size: 0.9em;">게시판</a>
        </li>
      </ul>
    </div>
  </nav>

</body>
</html>