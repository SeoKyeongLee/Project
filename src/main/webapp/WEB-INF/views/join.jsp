<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- 부트스트랩 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- 폰트 -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
  <!-- 부트스트랩 아이콘 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
  <!-- CSS파일 -->
  <link rel="stylesheet" href="css/index.css">
<!-- 카카오 주소 -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- js파일 -->
<script language="JavaScript" src="js/members.js"></script>
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
                <form action="joinOk" method="post" name="reg_frm" onsubmit="return infoConfirm();">
                    <h1 class="text-center fw-bold mb-5">회원가입</h1>
                    <hr class="my-4">
                    <div class="form-group mb-3">
                        <input type="text" placeholder="아이디를 입력해주세요." name="id" required class="form-control">
                    </div>
                    <div class="form-group mb-3">
                        <input type="password" placeholder="비밀번호를 입력해주세요." name="pw" required class="form-control">
                    </div>
                    <div class="form-group mb-3">
                        <input type="password" placeholder="비밀번호를 한번 더 입력해주세요." name="pw_check" required class="form-control">
                    </div>
                    <div class="form-group mb-3">
                        <input type="text" placeholder="이름을 입력해주세요." name="name" required class="form-control">
                    </div>
                    <div class="form-group mb-3">
                        <input type="email" placeholder="이메일을 입력해주세요." name="email" required class="form-control">
                    </div>
                    <div class="form-group mb-3">
                        <input type="tel" placeholder="핸드폰 번호를 입력해주세요." name="phone" required class="form-control">
                    </div>
                    <div class="form-group d-flex justify-content-center mb-3">
                        <input type="text" id="address" name="address" placeholder="주소를 검색해주세요." required class="form-control" style="flex: 2;">
                        <input type="button" id="searchAddr" onclick="findAddr()" value="주소 검색" class="form-control" style="flex: 1;">
                    </div>
                    <div class="d-flex flex-column text-center">
                        <button type="submit" class="btn btn-primary mb-3" onclick="infoConfirm()">회원가입</button>
                        <a href="index.jsp">메인화면으로 돌아가기</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- 푸터 -->
	<jsp:include page="footer.jsp" />
	

</body>
</html>