<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 폰트 -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap"
	rel="stylesheet">
<!-- 부트스트랩 아이콘 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<!-- CSS파일 -->
<link rel="stylesheet" href="../css/index.css">
<!-- js파일 -->
<script language="JavaScript" src="../js/board.js"></script>
<style>
.container {
	margin-bottom: 200px;
}

.extra-space {
	margin-top: 200px;
}
</style>
</head>
<body>

	<!-- 네비게이션바 -->
  <nav id="menu" class="navbar navbar-expand-lg">
    <a class="navbar-brand imgbox" href="<c:url value='/'/>">
      <img id="logo" src="../img/goldenTimeLogo_2.png" alt="GoldenTime">
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
  
  
	<div class="extra-space"></div>

	<div class="container mt-4">
		<h2 class="mb-5">게시판</h2>
		<form id="postForm" action="/goldenTime/boardModify" method="post" 
			onsubmit="return validateForm()">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<div class="form-group mb-3">
				<label for="name">작성자</label> <input type="text"
					class="form-control" id="name" required
					value="${content_view.bName}"
					<c:if test="${isAuthor eq true}">readonly</c:if>>
			</div>
			<div class="form-group mb-3">
				<label for="title">제목</label> <input type="text"
					class="form-control" id="title" name="bTitle"
					value="${content_view.bTitle}">
			</div>
			<div class="form-group mb-3">
				<label for="content">내용</label>
				<textarea class="form-control" id="content" name="bContent" rows="3">${content_view.bContent}</textarea>
			</div>
			<!-- 본인 게시글일 때  수정, 삭제, 취소, 답변 버튼 표시 -->
			<div class="d-flex justify-content-center">
				<c:if test="${isAuthor eq true}">
					<button type="submit" class="btn btn-primary"
						style="margin-right: 20px;">수정</button>
					<button type="button" class="btn btn-primary"
						style="margin-right: 20px;"
						onclick="window.location='/goldenTime/delete/${content_view.bId}'">삭제</button>
					<button type="button" class="btn btn-primary"
						style="margin-right: 20px;" onclick="window.location='/goldenTime/list'">취소</button>
					<button type="button" class="btn btn-primary"
						onclick="window.location='/goldenTime/reply_view/${content_view.bId}'">답변</button>
				</c:if>
				<!-- 작성자가 아닐 때  답변 버튼 표시 -->
				<c:if test="${isAuthor eq false}">
					<button type="button" class="btn btn-primary"
						style="margin-right: 20px;"
						onclick="window.location='/goldenTime/reply_view/${content_view.bId}'">답변</button>
					<button type="button" class="btn btn-primary"
						onclick="window.location='/goldenTime/list'">취소</button>
				</c:if>
			</div>
		</form>
	</div>

	<!-- 푸터 -->
  	<footer class="container-fulid" style="margin: 20px; padding: 20px;">
	    <div class="row">
	        <ul class="col-lg-3 text-center">
	            <a href="<c:url value='/'/>"><img id="logo" src="img/goldenTimeLogo_2.png" alt="GoldenTime"></a>
	        </ul>
	        <ul class="col-lg-5"></ul>
	        <ul class="col-lg-4">
	            <h4 style="font-weight: 500;">Information</h4>
	            <li class="bi bi-signpost-split-fill"><span class="text-secondary"> 주소 : 서울 마포구 신촌로 176(대흥동
	                    12-20)</span>
	            </li>
	            <li class="bi bi-telephone-fill"><span class="text-secondary"> 대표전화 : 02-718-8513</span></li>
	            <li class="bi bi-envelope-fill"><span class="text-secondary"> 이메일 : GoldenTime@gmail.com</span></li>
	            <li class="bi bi-calendar-week"><span class="text-secondary"> 근무시간 : 월 ~ 금 / AM 9:00 ~ PM 6:00</span>
	            </li>
	        </ul>
	    </div>
	</footer>

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- 부트스트랩 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>