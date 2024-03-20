<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>인적자원 재배치</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<link rel="stylesheet" href="css/doctor.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>	
<!-- 메뉴바 -->
<jsp:include page="menu.jsp" />

<div class="text" style="margin-top: 70px;">인적자원의 재배치</div>
<!-- 예측결과페이지 -->
	<div class="container" id="last">
		<div class="prediction_page">
			<div class="container text-center">
				<h3>인적자원 재배치 분석 결과</h3>
				<div class="row">
					<div class="col-sm">
						<div class="text-center">
							<img src="img/lastpg_decoimg.png" class="rounded" alt="..."
								width="60%" height="60%">
						</div>
					</div>
					<div class="col-sm" style="color: gray;">
						<div class="container text-center" id="pphData">
							<canvas id="result1"></canvas>
							<p>
								OECD에서 권장하는 최소 기준치 : <span>2PPH</span>
							</p>
							<p>
								PPH란? <span>1명의 의사가 한 시간 당 진료 할 수 있는 환자 수를 의미한다.</span>
							</p>
						</div>
					</div>
				</div>
			</div>

			<div class="row text-center" id="result">

				<div class="col-sm" id="result-box">

					

					<div class="result-result">

						<p>
							서울 응급의학과 전문의 중<span> 341명(34.3%)이</span> 경기도로 이동하게 되면,
						</p>
						<br>
						<p>
							<span>서울과 경기 모두 2PPH를 충족</span>하게 되어 의료형평성이 개선될 수 있음을 알 수 있다.
						</p>

					</div>
					<div>
						<div id="button" class="box">클릭하여 계산식 확인하기</div>
						<div id="panel" class="box">
							<br> 수요 FTE = Number of Patient / Capacity of 1 FTE<br>
							1. 서울 : 1,025,310명 / 3,528 = 291FTE<br> 2. 경기 : 1,358,634명 /
							3,528 = 385FTE<br> <br> 수요 전문의 수 = FTE / (전문의 중 응급실에
							근무하는 비율 x 실제 근무시간)<br> 1.서울 : 291FTE / (0.8 x 0.7) = 519명<br>
							2.경기 : 385FTE / (0.8 x 0.7) = 688명<br> <br>

							<h6 style="color: grey;">FTE란? 응급의학과 전문의 1명을 의미하는 것이 아니라 전체
								업무량의 기본단위이다.</h6>
							<br>

						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- 푸터 -->
	<jsp:include page="footer.jsp" />

	<!-- 스크립트 -->
	<script
		src=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
	<!-- doctor js -->

	<script src="js/doctor.js"></script>

</body>
</html>