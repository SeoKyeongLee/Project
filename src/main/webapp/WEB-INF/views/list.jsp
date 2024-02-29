<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
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
<style>
.container {
	margin-top: 200px;
	margin-bottom: 200px;
}

.pagination-container {
	text-align: center; /* 가로 방향 가운데 정렬 */
	margin-top: -100px;
}

.pagination-container a {
	margin: 0 5px; /* 각 링크 사이의 간격 조절 */
	text-decoration: none;
	padding: 5px 10px; /* 링크의 패딩 조절 */
	border: 1px solid #ccc; /* 링크 테두리 스타일 지정 */
	border-radius: 3px; /* 링크 테두리 둥글게 만듦 */
	display: inline-block; /* 링크를 인라인 블록 요소로 표시 */
	background-color: #f5f5f5; /* 링크 배경색 지정 */
}
</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page="menu.jsp" />

	<div class="container">
		<h2 class="mb-4">게시판</h2>
		<button type="button" class="btn btn-outline-primary"
			style="float: right;" id="b-write" onclick="redirect()">작성하기</button>
		<table class="table table-hover table-striped text-center">
			<thead>
				<tr>
					<th class="col-2">번호</th>
					<th class="col-2">이름</th>
					<th class="col-4">제목</th>
					<th class="col-2">날짜</th>
					<th class="col-2">조회수</th>
				</tr>
			</thead>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.bId}</td>
					<td>${dto.bName}</td>
					<td><c:forEach begin="1" end="${dto.bIndent}">-</c:forEach> <a
						href="content_view/${dto.bId}">${dto.bTitle}</a></td>
					<td>${dto.bDate}</td>
					<td>${dto.bHit}</td>
				</tr>
			</c:forEach>
		</table>

		<c:forEach var="post" items="${posts}">
			<!-- Display post details -->
		</c:forEach>

	</div>

	<!-- 페이지네이션 -->
	<div class="pagination-container">
		<c:if test="${paginatedData.currentPage > 1}">
			<a href="?action=list&page=${paginatedData.currentPage - 1}">이전</a>
		</c:if>

		<c:forEach begin="1" end="${paginatedData.totalPages}"
			var="pageNumber">
			<c:choose>
				<c:when test="${pageNumber == paginatedData.currentPage}">
                ${pageNumber} <!-- 현재 페이지, 링크 없음 -->
				</c:when>
				<c:otherwise>
					<a href="?action=list&page=${pageNumber}">${pageNumber}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${paginatedData.currentPage < paginatedData.totalPages}">
			<a href="?action=list&page=${paginatedData.currentPage + 1}">다음</a>
		</c:if>
	</div>

	<!-- 푸터 -->
	<jsp:include page="footer.jsp" />

	<!-- 로그인 안했을 때 - 작성하기 버튼 클릭 시 로그인 페이지(login.jsp)로 이동 -->
	<script>
		function redirect() {
			var loggedInUser = "${sessionScope.ValidMem}";
			if (loggedInUser == "yes") {
				window.location = "write_view";
			} else {
				window.location = "login";
			}
		}
	</script>

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- 부트스트랩 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>