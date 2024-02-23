<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 부트스트랩 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- 폰트 -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
  <!-- 부트스트랩 아이콘 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
  <!-- CSS파일 -->
  <link rel="stylesheet" href="css/index.css">
	<style>
        .container {
            margin-top: 200px;
            margin-bottom: 200px;
        }
        body{
            background-color: #f8f9fa;
        }
        .container h1, .container a{
          color: rgb(108, 117, 125);
        }
    </style>	
</head>
<body>
	<!-- 메뉴바 -->
 	<jsp:include page="menu.jsp" />

	<div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <form action="loginOk.do" method="post">
                	<h1 class="text-center fw-bold mb-5">로그인</h1>
                	<hr class="my-4">
                    <div class="form-group mb-3">
                        <input type="text" placeholder="아이디" name="id" required class="form-control">
                    </div>
                    <div class="form-group mb-3">
                        <input type="password" placeholder="비밀번호" name="pw" required class="form-control">
                    </div>
                    <div class="logIn d-flex flex-column text-center">
                        <button type="submit" class="btn btn-primary mb-3">로그인</button>
                        <a href="join.do">회원가입</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- 푸터 -->
    <jsp:include page="footer.jsp" />
</body>
</html>