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
		<form id="postForm" action="reply.do" onsubmit="return validateForm()">
			<input type="hidden" name="bId" value="${reply_view.bId}"> <input
				type="hidden" name="bName" value="${reply_view.bName}"> <input
				type="hidden" name="bGroup" value="${reply_view.bGroup}"> <input
				type="hidden" name="bStep" value="${reply_view.bStep}"> <input
				type="hidden" name="bIndent" value="${reply_view.bIndent}">
			<div class="form-group mb-3">
				<label for="name">작성자</label> <input type="text"
					class="form-control" id="name" required
					value="<c:out value="${sessionScope.name}" />"
					<c:if test="${isAuthor eq true}">readonly</c:if>>
			</div>
			<div class="form-group mb-3">
				<label for="title">제목</label> <input type="text"
					class="form-control" id="title" name="bTitle">
			</div>
			<div class="form-group mb-3">
				<label for="content">내용</label>
				<textarea class="form-control" id="content" name="bContent" rows="3">
				</textarea>
			</div>

			<div class="d-flex justify-content-center">
				<button type="submit" class="btn btn-primary"
					style="margin-right: 20px;">답변</button>
				<button type="button" class="btn btn-primary"
					onclick="window.location='list.do'">취소</button>
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