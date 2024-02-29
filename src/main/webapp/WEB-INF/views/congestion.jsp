<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.time.LocalDate"%>
    
<%
// 년도,월,지역 혼잡도 표시하기 (처음 들어올 시 현재 년도,월,전국 / 검색한 경우 검색한 년도,월,지역) 
 	Integer year = (Integer)request.getAttribute("year"); 
	Integer month = (Integer)request.getAttribute("month");
	System.out.println(year + " " + month);


	String stringDate = String.format("%04d-%02d", year, month);
    
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>혼잡도 예측</title>
    <!-- 부트스트랩 연결 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- 부트스트랩 아이콘 cdn -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <!-- 카카오맵 인증키 -->
    <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=844c165c559efe19967b545ff1fdb949"></script>

    <!-- chart.js 연결 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <!-- sheetjs cdn -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.15.5/xlsx.full.min.js"></script>
    <!-- css파일 연결 -->
    <link rel="stylesheet" href="css/congestion.css">
    <script> var congestionValue = '<%= request.getAttribute("congestionValue") %>';</script>
</head>

<body>
    <!-- 메뉴바 -->
	<jsp:include page="menu.jsp" />

    <div class="container-fluid">
        <div class="row" style="margin-top: 110px;">
            <div class="col-md-4" id="sideBar">
                <!-- 메뉴 이름 -->
                <div class="pageName">
                    <h1>혼잡도 예측</h1>
                    <p>Congestion Prediction</p>
                </div>
                <!-- 날짜검색 -->
                <div id="monthWrap">
                    <form action="monthSearch">
                        <p>혼잡도를 알고싶은 시점을 입력하세요!</p>
                        <div class="form-group" id="inputWrap">
                            <input type="month" name="month" id="month" class="btn btn-default" value="<%=stringDate%>">
                            <button type="submit" class="btn btn-primary">검색 <i class="bi bi-search"></i></button>
                        </div>
                    </form>
                </div>
                <div id="contentWrap">
                    <div id="content">
                        <p><span id="contentTime"><%=year%>년 <%=month%>월</span><br>
                            <span id="contentRegion">전국</span> 응급실의 혼잡도는<br>
                            <span id="contentCongestion">${congestion}</span> 수준입니다.
                        </p>
                        <p id="scription"><i class="bi bi-lightbulb"></i> 혼잡도는 응급실의 <span id="formula">병상 포화지수</span>를
                            이용하여 상정합니다.</p>
                        <div id="formulaPop">
                            <img src="img/formula.png" alt="" style="width: 100%; height: 100px;">
                        </div>
                    </div>
                </div>
                <!-- 응급실 이용 지표 -->
                <div id="infoWrap" class="border-bottom-primary">
                    <div id="info">
                        <p class="chartName">혼잡도 비교</p>
                        <p id="regionTitle">전국</p>
                        <div class="erChartWrap">
                            <canvas id="erChart1" class="erChart">
                            </canvas>
                        </div>
                        <button type="button" class="btn btn-sm btn-primary" id="popOpen">
                            크게 보기</button>
                    </div>
                </div>

                <!-- 응급실 이용 지표 크게보기 팝업 -->
                <div id="popWrap" class="hide">
                    <div id="popInner">
                        <div class="btnWrap">
                            <button type="button" id="popClose"><i class="bi bi-x-square"></i></button>
                        </div>
                        <p class="chartName">응급실 이용 지표</p>
                        <canvas id="erChart2" class="erChart">
                        </canvas>
                    </div>
                </div>

            </div>
            <div class="col-md-8" id="mapWrap">
                <div class="container-fulid" id="map"></div>
            </div>
        </div>


  <!-- 푸터 -->
	<jsp:include page="footer.jsp" />
    </div>

    <!-- jquery cdn -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <!-- js 파일 연결 -->
    <script type="module" src="js/congestion.js"></script>
</body>

</html>