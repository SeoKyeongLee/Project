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
<link rel="stylesheet" href="css/index.css">
<!-- js파일 -->
<script language="JavaScript" src="js/board.js"></script>
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
	<jsp:include page="menu.jsp" />

	<div class="extra-space"></div>

	<div class="container mt-4">
		<h2 class="mb-5">게시판</h2>
		<form id="postForm" action="boardModify.do"
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
						onclick="window.location='delete.do?bId=${content_view.bId}'">삭제</button>
					<button type="button" class="btn btn-primary"
						style="margin-right: 20px;" onclick="window.location='list.do'">취소</button>
					<button type="button" class="btn btn-primary"
						onclick="window.location='reply_view.do?bId=${content_view.bId}'">답변</button>
				</c:if>
				<!-- 작성자가 아닐 때  답변 버튼 표시 -->
				<c:if test="${isAuthor eq false}">
					<button type="button" class="btn btn-primary"
						style="margin-right: 20px;"
						onclick="window.location='reply_view.do?bId=${content_view.bId}'">답변</button>
					<button type="button" class="btn btn-primary"
						onclick="window.location='list.do'">취소</button>
				</c:if>
			</div>
		</form>
	</div>

	<jsp:include page="footer.jsp" />

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- 부트스트랩 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>