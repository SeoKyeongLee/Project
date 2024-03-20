<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
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
<!-- 카카오 주소 -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- js파일 -->
<script type="text/javascript" src="js/members.js"></script>
<script language="JavaScript" src="js/findAddr.js"></script>
<style>
.container {
	margin-top: 200px;
	margin-bottom: 200px;
}

.extra-space {
	margin-top: 200px;
}

body {
	background-color: #f8f9fa;
}

.container h1, .container a {
	color: rgb(108, 117, 125);
}
</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page="menu.jsp" />

	<div class="extra-space"></div>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<form action="/goldenTime/modifyOk" method="post" name="reg_frm" onsubmit="return updateInfoConfirm();">
					<h2 class="text-center fw-bold mb-5">회원 정보 수정</h2>
					<hr class="my-4">
					<div class="form-group mb-3">
						<label for="id">아이디:</label> <input type="text" name="id"
							value="${member.id}" required class="form-control" readonly>
					</div>
					<div class="form-group mb-3">
						<label for="pw">비밀번호:</label> <input type="password" name="pw"
							placeholder="비밀번호" required class="form-control">
					</div>
					<div class="form-group mb-3">
						<label for="pw_check">비밀번호 확인:</label> <input type="password"
							name="pw_check" placeholder="비밀번호확인" required
							class="form-control">
					</div>
					<div class="form-group mb-3">
						<label for="name">이름:</label> <input type="text" name="name"
							value="${member.name}" required class="form-control" readonly>
					</div>
					<div class="form-group mb-3">
						<label for="email">이메일:</label> <input type="email" name="email"
							value="${member.email}" required class="form-control">
					</div>
					<div class="form-group mb-3">
						<label for="phone">핸드폰번호:</label> <input type="tel" name="phone"
							value="${member.phone}" required class="form-control">
					</div>
					<div class="form-group">
						<label for="address">주소:</label>
						<div class="d-flex justify-content-center mb-3">
							<input type="text" id="address" name="address"
								value="${member.address}" required class="form-control"
								style="flex: 2;"> <input type="button" id="searchAddr"
								onclick="findAddr()" value="주소 검색" class="form-control"
								style="flex: 1;">
						</div>
					</div>
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary" value="수정"
							onclick="updateInfoConfirm()" style="margin-right: 40px;">수정</button>
						<button type="button" class="btn btn-primary"
							onclick="window.location.href='index.jsp'">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 푸터 -->
	<jsp:include page="footer.jsp" />

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- 부트스트랩 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>