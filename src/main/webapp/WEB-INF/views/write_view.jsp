<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
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
		<form id="postForm" action="write" method="post"
			onsubmit="return validateForm()">
			<div class="form-group mb-3">
				<label for="bName">작성자</label> <input type="text"
					class="form-control" id="name" name="bName"
					value="<%=session.getAttribute("name")%>" required>
			</div>
			<div class="form-group mb-3">
				<label for="bTitle">제목</label> <input type="text"
					class="form-control" id="title" name="bTitle">
			</div>
			<div class="form-group mb-3">
				<label for="bContent">내용</label>
				<textarea class="form-control" id="content" name="bContent" rows="3"></textarea>
			</div>
			<div class="d-flex justify-content-center">
				<button type="submit" class="btn btn-primary"
					style="margin-right: 40px;">작성</button>
				<button type="button" class="btn btn-primary"
					onclick="window.location='list'">취소</button>
			</div>
		</form>

		<div id="postList" class="mb-4"></div>
	</div>

	<jsp:include page="footer.jsp" />

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- 부트스트랩 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>