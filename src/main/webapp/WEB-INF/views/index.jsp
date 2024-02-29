<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 부트스트랩 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- 폰트 -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
  <!-- 부트스트랩 아이콘 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
  <!-- CSS파일 -->
  <link rel="stylesheet" href="css/index.css">
  <title>GoldenTime</title>
</head>

<body>
	<!-- 메뉴바 -->
	<jsp:include page="menu.jsp" />
	
  <!-- 캐러셀 -->
  <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel" data-bs-interval="2000">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="img/img007.jpg" class="d-block w-100 carousel-img" alt="...">
        <div class="carousel-caption d-none d-md-block" style="color: black;">
          <!-- 텍스트 내용 -->
          <h1 style="font-size: 4em; font-family: MICEGothic Bold;">GoldenTime</h1>
          <p style="font-size: 1.5em; font-family: MICEGothic Bold;">골든타임에 오신것을 환영합니다.<br>응급실에 대한 모든 정보를 소개합니다.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="img/img008.jpg" class="d-block w-100 carousel-img" alt="...">
        <div class="carousel-caption d-none d-md-block" style="color: black;">
          <!-- 텍스트 내용 -->
          <h1 style="font-size: 4em;">GoldenTime</h1>
          <p style="font-size: 1.5em;">골든타임에 오신것을 환영합니다.<br>응급실에 대한 모든 정보를 소개합니다.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="img/img009.jpg" class="d-block w-100 carousel-img" alt="...">
        <div class="carousel-caption d-none d-md-block" style="color: black;">
          <!-- 텍스트 내용 -->
          <h1 style="font-size: 4em;">GoldenTime</h1>
          <p style="font-size: 1.5em;">골든타임에 오신것을 환영합니다.<br>응급실에 대한 모든 정보를 소개합니다.</p>
        </div>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
      <span class="bi bi-caret-left-fill" style="color: black; font-size: 2em;"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
      <span class="bi bi-caret-right-fill" style="color: black; font-size: 2em;"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
  <!-- 기능 설명 및 버튼 -->
  <section class="container">
    <div class="row">
      <div class="col-lg-6 text-center" style="margin-bottom: 20px;">
        <img class="circleimg effect" src="img/img001.jpg" alt="">
        <h1 class="effect">응급실 현황 서비스</h1>
        <p class="text-secondary effect">
          	주변 응급실을 찾을 수 있게 도와드리며, <br>응급실에 실시간 정보까지 제공해 드립니다.
        </p>
        <a class="btn btn-lg btn-danger effect" style="color: white;" href="emrStatus">더보기</a>
      </div>
      <div class="col-lg-6 text-center" style="margin-bottom: 20px;">
        <img class="circleimg effect" src="img/img002.jpg" alt="">
        <h1 class="effect">응급실 데이터 서비스</h1>
        <p class="text-secondary effect">
          	응급실 수의 증가로 예상되는 효과를 확인할 수 있는 페이지를 소개합니다. <br>이 페이지는 그래프를 통해 응급실 환경의 예상 개선을 직관적으로 시각화하여 제공합니다.
        </p>
        <a class="btn btn-lg btn-primary effect" style="color: white;" href="doctor">더보기</a>
      </div>
    </div>
    <div id="page3" class="row" style="margin-top: 50px; text-align: center;">
      <div class="col-lg-6 text-center" id="page3">
        <img class="circleimg effect" src="img/graph.jpg" alt="">
        <div class="col-lg-12 text-center">
          <h1 class="effect">응급실 혼잡도 예측</h1>
          <p class="text-secondary effect">
          	빅데이터를 통해,<br>지역 별 응급실 혼잡도를 예측합니다.
          </p>
          <a class="btn btn-lg btn-success effect" style="color: white;" href="congestion">더보기</a>
        </div>
      </div>
      <div class="col-lg-6 text-center pageInfo" id="page4">
        <img class="circleimg effect" src="img/doctor.jpg" alt="">
        <ul class="col-lg-12 text-center">
          <div class="align-bottom">
            <h1 class="effect">인적자원 재배치</h1>
            <li class="text-secondary effect" style="margin-bottom: 16px;">
            	효율적인 응급의료 극대화를 위해<br>응급의학과 전문의 재배치 예측치를 제공합니다
            </li>
            <a class="btn btn-lg effect" style="background: yellow; color: black;" href="person">더보기</a>
          </div>
        </ul>
      </div>
    </div>
  </section>

  <!-- 푸터 -->
	<jsp:include page="footer.jsp" />


  <!-- jquery -->
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
  <!-- 부트스트랩 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  <!-- 애니메이션 -->
  <script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/16327/gsap-latest-beta.min.js"></script>
  <script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/16327/CSSRulePlugin3.min.js"></script>
  <!-- js파일 -->
  <script src="js/index.js"></script>

</body>

</html>