<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<!-- 카카오맵 api와 라이브러리 -->
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ec806f30ebc587bdcceff498b4e7d26&libraries=services,clusterer,drawing"></script>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<!-- 부트스트랩 아이콘 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<!-- CSS 파일 -->
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/emrStatus.css">

<title>응급실 현황</title>

</head>

<body>
	<!-- 메뉴바 -->
	<jsp:include page="menu.jsp" />


	<section>
		<!-- 페이지 이름 -->
		<div id="page2" class="container" style="padding-bottom: 100px;">
			<div class="row">
				<div class="col-md-12">
					<h1>응급실 정보</h1>
				</div>
			</div>
			<!-- 맵 -->
			<div class="row">
				<div class="col-lg-6">
					<img id="seoulMap" src="img/seoulMap/seoul.PNG" usemap="#image-map"
						class="responsive-img">
					<map name="image-map">
						<area target="" alt="강남" title="강남" onclick="clickGangnam()"
							coords="543,511,540,496,524,484,528,472,511,472,502,478,475,490,468,494,468,487,464,477,456,472,455,455,436,451,416,403,416,392,409,382,431,360,484,384,486,422,494,428,534,438,547,446,560,478,576,496,570,506,555,502"
							shape="poly">
						<area target="" alt="강동" title="강동" onclick="clickGangdong()"
							coords="616,390,579,367,570,373,556,360,589,302,662,268,675,304,675,328,667,333,652,329,635,342,624,372,612,379"
							shape="poly">
						<area target="" alt="강북" title="강북" onclick="clickGangbuk()"
							coords="385,52,396,55,396,63,404,64,407,73,412,82,408,88,407,100,404,106,403,113,403,120,407,125,411,126,412,129,416,124,423,128,424,135,427,139,432,138,436,145,444,149,444,159,449,162,453,167,460,174,455,181,452,186,438,199,432,195,424,196,415,192,408,189,399,183,398,176,399,173,394,172,383,165,379,164,376,156,374,151,368,154,360,147,362,138,352,110,360,107,367,96,375,91,374,64"
							shape="poly">
						<area target="" alt="강서" title="강서" onclick="clickGangseo()"
							coords="99,341,78,339,61,349,55,337,26,329,11,329,9,316,29,301,26,290,36,287,36,278,57,268,64,244,60,226,191,315,195,327,182,329,168,318,165,335,167,360,140,371,124,352,120,334,112,329,106,341"
							shape="poly">
						<area target="" alt="관악" title="관악" onclick="clickGwanak()"
							coords="232,465,236,458,263,448,273,440,291,444,319,444,326,465,338,476,353,475,364,494,364,510,354,518,341,533,329,536,328,543,294,555,287,543,276,538,276,524,266,510,256,512,258,502,254,488,265,480,254,462"
							shape="poly">
						<area target="" alt="광진" title="광진" onclick="clickGwangjin()"
							coords="481,361,516,371,523,370,542,354,566,318,570,303,549,303,551,278,543,278,529,284,524,278,512,276,504,300,508,309,501,314,499,326,497,334,483,346"
							shape="poly">
						<area target="" alt="구로" title="구로" onclick="clickGuro()"
							coords="198,393,208,401,214,411,214,423,214,437,220,450,230,455,226,466,215,465,207,458,204,451,196,445,187,446,177,431,163,450,156,462,140,465,139,477,124,478,118,472,113,477,99,473,97,458,101,447,95,444,88,430,99,422,103,410,116,410,115,419,124,422,127,426,132,424,134,417,139,417,145,408,153,408,159,412,168,417,181,413"
							shape="poly">
						<area target="" alt="금천" title="금천" onclick="clickGeumcheon()"
							coords="183,452,183,462,186,471,192,479,197,487,206,497,204,507,211,513,213,518,219,522,225,527,222,532,230,542,231,555,240,559,248,546,257,546,261,534,272,527,265,514,254,514,251,504,255,498,248,486,254,482,260,479,251,474,253,466,232,470,214,470,202,461,198,453,189,451"
							shape="poly">
						<area target="" alt="노원" title="노원" onclick="clickNowon()"
							coords="491,192,480,189,467,175,459,166,452,159,459,155,458,149,467,137,471,143,481,147,481,131,474,107,474,94,470,88,468,73,472,63,470,51,477,46,492,45,495,33,513,30,517,35,526,45,536,43,536,56,532,67,538,79,534,98,536,103,524,108,534,117,538,131,547,127,560,132,560,143,563,159,558,167,548,173,546,185,541,187,536,186,530,188,522,181,505,185"
							shape="poly">
						<area target="" alt="도봉" title="도봉" onclick="clickDobong()"
							coords="454,155,455,146,460,142,465,131,471,134,476,141,476,127,470,108,469,93,466,87,464,77,464,67,467,61,467,50,460,45,462,33,448,33,440,40,433,34,431,18,410,19,396,31,402,39,400,52,402,61,411,64,411,71,416,78,416,87,412,91,412,100,411,106,410,111,408,118,412,122,419,121,426,126,429,133,436,135,440,143,447,145,450,154"
							shape="poly">
						<area target="" alt="동대문" title="동대문" onclick="clickDongdaemun()"
							coords="498,300,480,298,469,285,461,280,450,279,440,281,429,280,423,275,428,268,437,260,442,254,444,247,450,244,456,233,464,233,467,226,475,226,482,226,484,220,489,214,500,210,500,218,498,226,498,234,496,242,499,249,507,258,508,272,507,280,503,291"
							shape="poly">
						<area target="" alt="동작" title="동작" onclick="clickDongjak()"
							coords="236,451,246,434,262,429,271,393,295,389,301,393,337,411,352,413,357,429,352,467,340,470,331,461,326,437,314,439,301,434,291,439,273,432,260,443"
							shape="poly">
						<area target="" alt="마포" title="마포" onclick="clickMapo()"
							coords="194,244,217,266,230,278,240,284,252,292,260,292,268,301,271,311,284,315,292,311,315,312,324,309,320,315,324,323,324,328,317,331,315,341,308,348,301,350,294,355,283,347,268,341,257,339,248,340,232,331,222,315,170,271,190,262,186,255"
							shape="poly">
						<area target="" alt="서대문" title="서대문" onclick="clickSeodaemun()"
							coords="304,209,294,216,292,230,282,231,273,240,270,251,260,264,247,251,243,260,232,271,241,280,256,289,266,291,271,301,275,309,286,311,295,307,316,307,329,300,316,270,310,263,318,260,314,246,317,232,311,230,308,215"
							shape="poly">
						<area target="" alt="서초" title="서초" onclick="clickSeocho()"
							coords="356,415,366,430,359,437,358,472,368,493,368,506,380,505,381,493,390,494,392,506,411,519,431,511,432,495,442,504,441,519,448,529,442,545,455,552,468,569,490,568,499,554,500,542,515,543,526,538,526,524,532,503,536,507,529,492,517,484,521,477,514,476,508,480,496,482,479,494,466,497,460,490,459,480,450,468,447,456,433,454,414,411,407,386,391,400,371,410"
							shape="poly">
						<area target="" alt="성동" title="성동" onclick="clickSeongdong()"
							coords="413,352,407,345,400,339,410,324,410,318,422,309,430,295,427,285,442,285,452,281,462,285,468,291,476,299,484,302,501,305,503,310,497,312,496,317,490,334,478,346,475,358,436,344,435,333,427,346"
							shape="poly">
						<area target="" alt="성북" title="성북" onclick="clickSeongbuk()"
							coords="416,267,424,264,438,249,439,243,445,242,452,231,452,224,460,228,461,221,467,216,478,221,479,215,484,208,493,207,499,203,499,195,488,195,480,193,462,178,454,189,445,200,436,207,430,201,416,201,405,194,394,185,396,175,387,176,380,169,376,169,373,157,368,160,360,151,352,151,348,157,348,164,355,166,356,174,360,179,359,187,369,193,368,209,366,213,368,223,362,225,356,232,364,233,370,237,379,237,392,237,394,245,399,250,399,257,400,262,410,258,416,257"
							shape="poly">
						<area target="" alt="송파" title="송파" onclick="clickSongpa()"
							coords="581,494,599,490,593,476,597,470,606,477,623,470,627,452,644,423,635,414,619,413,611,406,614,393,579,371,574,377,559,372,552,363,548,373,522,386,489,387,491,418,496,423,512,426,538,433,551,442,560,473,573,485"
							shape="poly">
						<area target="" alt="양천" title="양천" onclick="clickYangcheon()"
							coords="103,406,112,373,99,349,109,344,117,343,127,373,136,374,168,368,170,345,170,326,197,332,207,341,212,358,206,369,195,374,192,393,171,415,162,404,149,400,137,410,128,419,119,417,119,405"
							shape="poly">
						<area target="" alt="영등포" title="영등포"
							onclick="clickYeongdeungpo()"
							coords="232,452,242,433,258,426,260,410,267,389,287,382,284,369,258,355,244,358,232,349,200,320,195,318,198,329,211,338,215,355,208,369,200,372,196,386,204,390,215,400,217,409,220,441,225,447"
							shape="poly">
						<area target="" alt="용산" title="용산" onclick="clickYongsan()"
							coords="299,358,310,355,318,344,319,338,330,328,328,320,335,316,339,320,349,320,364,320,375,331,385,328,391,327,396,344,409,355,400,369,388,382,379,388,361,394,332,387,312,375"
							shape="poly">
						<area target="" alt="은평" title="은평" onclick="clickEunpyeong()"
							coords="195,241,202,235,206,240,225,238,229,225,229,188,238,167,230,158,235,151,247,153,242,137,266,121,277,120,282,112,295,99,307,109,317,113,317,120,326,134,334,138,337,154,332,162,323,158,316,171,306,169,301,178,305,186,303,205,295,206,294,212,287,214,289,221,283,226,277,229,271,233,266,242,263,249,257,254,251,252,255,247,245,246,239,255,231,265,226,268,217,259,209,250"
							shape="poly">
						<area target="" alt="종로" title="종로" onclick="clickJongno()"
							coords="310,174,306,179,308,187,308,207,313,216,311,223,319,229,320,261,314,266,322,271,328,286,338,279,349,283,366,283,376,285,381,281,388,283,420,280,420,271,411,270,411,262,404,267,396,266,392,251,388,243,378,243,369,245,362,237,355,237,352,230,357,221,362,221,360,215,365,198,353,185,348,168,342,161,332,166,323,165,320,173"
							shape="poly">
						<area target="" alt="중구" title="중구" onclick="clickJung()"
							coords="327,317,328,311,328,304,335,302,331,289,339,286,350,287,360,287,368,289,380,284,390,286,399,286,414,284,419,282,422,291,424,296,417,304,409,313,405,323,399,331,397,331,392,323,383,323,380,328,368,316,355,313,343,314,336,311"
							shape="poly">
						<area target="" alt="중랑" title="중랑" onclick="clickJungnang()"
							coords="512,272,527,274,531,278,536,275,546,272,548,261,544,255,559,257,564,238,573,232,570,221,576,202,574,187,567,179,550,185,547,190,535,189,528,193,520,184,503,191,508,217,501,228,500,240,504,248,514,257"
							shape="poly">
					</map>
				</div>
				<!-- 우측 코드 -->
				<div id="rightSection" class="col-lg-6">
					<div id="hospital">
						<button id="previousButton"
							class="bi bi-chevron-double-left text-secondary"
							onclick="previousGu()"></button>
						<div id="currentLocation" style="background: rgb(255, 255, 255);">
							<div style="font-size: 1.2em;">현재위치</div>
							<div id="present" style="height: 25px;"></div>
						</div>
						<button id="nextButton"
							class="bi bi-chevron-double-right text-secondary"
							onclick="nextGu()"></button>
					</div>
					<div class="text-secondary text-center"
						style="font-size: 2em; margin-top: 50px;">
						지도를 클릭하시면 응급실 정보를<br> 제공해드립니다.
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- 지도 실시간 정보 -->
	<div class="container" id="page1"
		style="margin-top: 120px; margin-bottom: 10px;">
		<h1>실시간 응급실</h1>
		<h6>병원 아이콘을 클릭하시면, 실시간 시각화 정보가 표시됩니다.</h6>
		<div class="row">
			<div class="col-lg-8">
				<div id="map"></div>
				<div id="clickLatlng"></div>
				<div class="col-lg-3">
					<button type="button" class="btn btn-default btn-small active"
						id="myLocation">
						<span class="bi bi-geo-alt text-primary" onclick="submitData()"></span>
						현위치
					</button>

					<button type="button" class="btn btn-default btn-small active"
						id="myLocation" onclick="fnMove(2)" name="myLocation">
						<span class="bi bi-chevron-double-up text-primary"></span> 처음으로
					</button>
				</div>
			</div>
			
			<div class="col-lg-4">

				<div id="emergencyList"></div>
				<div class = "row text-left" style="margin-top: 5%;margin-bottom:10%;">
				<h3>현재 위치 기준 자동 병원 추천</h3>
				<p style ="color: grey;"> 지도 왼쪽 하단의 현위치 아이콘을 클릭하세요</p>
				</div>
				
				<div class="container text-left" id="ajaxresult"  >
				
				</div>
				<div class="text-center" style="font-size:0.8em;margin-top:10%;">
				혼잡률과 거리를 50 대 50 비율로 계산한 결과로 산출한 데이터 입니다.
				<br><br>
				계산식 : (현재 위치로부터 병원까지의 거리 * 50) - (혼잡률 * 50)<br>
				(※  혼잡률은 높을수록 여유롭습니다.)
				
				</div>


			</div>
		</div>
	</div>


	<div class="container" id="page1">
		<div class="row">
			<div class="col-lg-4">
				<!-- measurement-standard: 혼잡도 측정 기준-->
				<div id="measurement-standard">
					<p>
						<b>응급실 혼잡도: <span class="bi bi-circle-fill text-success">
						</span><span class="standard-free">80% 이상:여유</span> <span>&ensp;<span
								class="bi bi-circle-fill text-warning"></span> <span
								class="standard-normal">50~79% 이상:보통</span>&ensp; <span
								class="bi bi-circle-fill text-danger"> </span><span
								class="standard-confused">50% 미만:심각</span></b> <br>(기존 병상수 대비
						가용 병상수의 비율)
					</p>
				</div>
				<!-- 병원 이름 가운데 정렬 하려고 id 부여-->
				<p id="status-name">
					<b>병원 이름:<span id="hospital-name"></span></b>
				</p>
				<div class="row" class="status-chart">
					<div class="col-lg-12">
						<div id="statusChartContainer"
							style="background-color: rgb(230, 230, 230);">
							<canvas id="statusChart"></canvas>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="row" id="status-confused">
					<div class="col-lg-12">
						<span class="bi bi-circle-fill" id="confused-level"></span><span>현재
							응급실의 혼잡도는&nbsp<span id="confused-step"><b></b></span>&nbsp<span>입니다.</span>
					</div>
				</div>
				<!-- id=status-room: 위의 차트와 공간을 살짝 주기 위해서. used-, existing-: 사용중인 병상, 기존 병상-->
				<div class="row" id="status-room">
					<!--<div class="col-lg-6">
					 	<div id="status-admiss">
							<li><b><span
									class="fa-solid fa-notes-medical text-success"></span>입원실</b><br>
								<span id="used-admiss"><b></b></span></li>
							<li class="explain"><b>(가용병상수)</b></li>
						</div>
					</div> -->
					
						<div id="status-surgery">
							<li><b><span class="bi bi-heart-pulse-fill text-danger"></span>수술실</b>
								<br> <span id="used-surgery"><b></b></span></li>
							<li class="explain" id="used-surgery"><b>(가용병상수)</b></li>
						</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="row" id="status-message">
					<div class="col-lg-12">
						<div class="em-mesage">
							<!-- 제목 가운데 정렬 위해 class=em-head 부여-->
							<li class="em-head"><b><span
									class="fa-solid fa-truck-medical text-danger"></span><span>응급
										메시지</span></b></li>
							<li class="message-list"><span>항목:</span><span
								class="message-item" id="em-receive1"></span></li>
							<li class="message-list"><span>응급/중증구분:</span><span
								class="message-item" id="em-receive2"></span></li>
							<li class="message-list"><span>진료불가 메시지:</span><span
								class="message-item" id="em-receive3"></span></li>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 푸터 -->
	<jsp:include page="footer.jsp" />

	<!-- GSAP 및 jQuery 스크립트는 한 번만 로드 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.1.1/gsap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<!-- 네비게이션 반응형 웹 -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<!-- 서울 이미지 맵 반응형 웹 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jQuery-rwdImageMaps/1.6/jquery.rwdImageMaps.min.js"></script>
	<!-- 차트 js  -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<!-- js파일 -->
	<script src="js/emrStatus.js"></script>
	<!-- 부트스트랩 및 기타 스크립트는 필요한 경우에만 로드 -->
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script> -->
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> -->
	<script>
		
	</script>
</body>
</html>