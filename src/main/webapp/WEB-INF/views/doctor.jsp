<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<!-- 문서의 Content-Type 및 문자 인코딩 설정 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>응급실 정보</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">


<link rel="stylesheet" href="css/doctor.css">
<link rel="stylesheet" href="css/emrData.css">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/emrData.css">

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>


<body>
	<!-- 메뉴바 -->
	<jsp:include page="menu.jsp" />

	<!--파란 단락 -->
	<div class="text">인적자원의 재배치</div>
	<!-- 제목 -->
	<div class="container">
		<div class="main">
			<div class="main_text">
				<h4 class="display-3">
					Emergency Information
				</h4>
				<h5>
					<br>응급실 관련 자료 시각화 서비스
				</h5>
			</div>
			<div class="main_img">
				<img src="img/titleImg.png" alt="...">
			</div>
		</div>
	</div>
	<!-- --------------------------------------- -->

	<!-- 3 서울시 응급실 수와 병상 포화지수는 얼마나 될까?--------------------------------------- -->
	<div class="container" style="margin-top: 100px; margin-bottom: 100px;">

		<div class="row">
			<div class="col-sm">
				<h2 class="title">
					<!-- 1 아이콘 -->
					<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50"
						fill="currentColor" class="bi bi-1-square-fill"
						viewBox="0 0 16 16"> <path
						d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2Zm7.283 4.002V12H7.971V5.338h-.065L6.072 6.656V5.385l1.899-1.383h1.312Z" />
					</svg>
					서울시 응급실 수와 병상포화지수
				</h2>
			</div>
		</div>

		<div class="row" id="seoul_bargraph">
			<div class="col-lg-8">
				<!-- 응급실 필요갯수 응급실 실제갯수 서울시 막대그래프 -->
				<canvas id="myChart1_emrData"></canvas>
				<button class="btn btn-primary" onclick="emergencyRoom()"
					style="margin-top: 50px;">응급실</button>
				<button class="btn btn-primary" onclick="saturation()"
					style="margin-top: 50px;">병상포화지수</button>
			</div>

			<div class="col-lg-4 text-left align-items-center">

				<div id="text" class="chart1Text" style="font-size: 25px;" >
				<p>
					이 그래프는 <span>서울에 위치한 응급실의 수와 인구수 대비 필요한 응급실의 수</span>를 표현한 그래프입니다.
				</p>
				<br>
				<p style="font-size: 15px;">
					<span>버튼</span>을 눌러 차트를 확인해보세요
				</p>
			</div>
			
			</div>
		</div>
	</div>

	<!--꺽은선 그래프 페이지 -->


	<div class="container" style="margin-top: 50px; margin-bottom: 30px;">
		<div class="row">
			<div class="col-sm">
				<h2 class="title">
					<!-- 1 아이콘 -->
					<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50"
						fill="currentColor" class="bi bi-2-square-fill"
						viewBox="0 0 16 16"> <path
						d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2Zm4.646 6.24v.07H5.375v-.064c0-1.213.879-2.402 2.637-2.402 1.582 0 2.613.949 2.613 2.215 0 1.002-.6 1.667-1.287 2.43l-.096.107-1.974 2.22v.077h3.498V12H5.422v-.832l2.97-3.293c.434-.475.903-1.008.903-1.705 0-.744-.557-1.236-1.313-1.236-.843 0-1.336.615-1.336 1.306Z" />
					</svg>
					서울시 인구수 변화율
				</h2>
			</div>
		</div>

		<div class="row text-center" style="margin-top: 30px;">

			<div class="col-lg-12" id="secondParagraph">
				<!-- 인구변화율 꺽은선 그래프 -->
				<canvas id="myChart3_emrData"></canvas>
			</div>
			<div class="col-lg-12 text-center" style="margin-top: 30px;">
				<p style="font-size: 30px;">
					<span>서울시 인구수 변화율</span>을 시각화한 그래프입니다.
				<p>
					이를 통해 <span>인구수가 점차 감소한다</span>는 사 실을 알수 있으며, 응급실을 늘리고자 할때 이를 염두해야
					합니다.
				</p>
			</div>
		</div>
	</div>
	<!-- ------------------------------------------ -->

	<!-- 차트페이지 -->
	<div class="container" id="chart">
		<!-- 배너 -->
		<div class="baner">
			<img src="img/baner3.png" class="baner_img" alt="" width="100%">
		</div>

		<!-- OECD %한국 박스 -->
		<div class="container text-center">
			<!-- 첫번째 박스4개 -->
			<div class="row" id="box4">
				<div class="col-sm">
					<div class="bar_title doctor">
						<div class="title">
							<h6>평균 OECD 의사 수</h6>
							<h1>3.7명</h1>
						</div>
						<div class="bar-chart">
							<div class="progress-bar">
								<div class="progress1"></div>
							</div>
						</div>
					</div>

				</div>
				<div class="col-sm">
					<div class="bar_title">
						<div class="title">
							<h6>평균 OECD 병상 수</h6>
							<h1>4.3개</h1>
						</div>
						<div class="bar-chart">
							<div class="progress-bar">
								<div class="progress2"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm">
					<div class="bar_title doctor">
						<div class="title">
							<h6>평균 한국 의사 수</h6>
							<h1>2.6명</h1>
						</div>
						<div class="bar-chart">
							<div class="progress-bar">
								<div class="progress3"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm">
					<div class="bar_title">
						<div class="title">
							<h6>평균 한국 병상 수</h6>
							<h1>12.8개</h1>
						</div>
						<div class="bar-chart">
							<div class="progress-bar">
								<div class="progress4"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row " id="graph">
				<!-- 바그래프 -->
				<div class="col-sm">
					<canvas id="myChart"></canvas>

				</div>
				<!-- 표 -->
				<div class="col-sm">
					<h5>우리나라와 OECD 보건의료 지표 비교</h5>

					<table class="table-primary table table align-middle">
						<thead>
							<tr>
								<th scope="col">비교 항목</th>
								<th scope="col">한국</th>
								<th scope="col">OECD</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">인구 대비 병상<br>(1000명당)
								</th>
								<td>12.8개</td>
								<td>4.3개</td>

							</tr>
							<tr>
								<th scope="row">1인당 병원 외래<br>진료(연간)
								</th>
								<td>15.7회</td>
								<td>5.8회</td>

							</tr>
							<tr>
								<th scope="row">임상 의사 수 <br> (한의사 포함)
								</th>
								<td>2.6명</td>
								<td>3.7명</td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
	<!-- ---------------------------------->
	<!-- 서울 경기 비교 -->
	<div class="container">
		<div class="compare">
			<h1 class="display-3">How Different</h1>
			<h6>GOLDEN TIME</h6>
			<h2>전국 각지의 의사들은 몇명이나 있을까요?</h2>
		</div>
		<!-- 경기서울박스 -->
		<div class="row-sm">
			<div class="koreaDoctorBar">
				<canvas id="myChart1_doctor"></canvas>
			</div>
		</div>
	</div>
	<!-- ------------------------------ -->

	<!-- 푸터 -->
	<jsp:include page="footer.jsp" />

	<!-- 스크립트 -->
	<script
		src=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
	<!-- doctor js -->


	<!-- 
	<script src="js/doctor.js"></script>
	-->
	<script src="js/emrData.js"></script>
</body>

</html>