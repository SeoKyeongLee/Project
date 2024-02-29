<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<!-- 푸터 -->
  <footer class="container-fulid" style="margin: 20px; padding: 20px;">
    <div class="row">
        <ul class="col-lg-3 text-center">
            <a href="<%=request.getContextPath() %>/index.jsp"><img id="logo" src="img/goldenTimeLogo_2.png" alt="GoldenTime"></a>
        </ul>
        <ul class="col-lg-5"></ul>
        <ul class="col-lg-4">
            <h4 style="font-weight: 500;">Information</h4>
            <li class="bi bi-signpost-split-fill"><span class="text-secondary"> 주소 : 서울 마포구 신촌로 176(대흥동
                    12-20)</span>
            </li>
            <li class="bi bi-telephone-fill"><span class="text-secondary"> 대표전화 : 02-718-8513</span></li>
            <li class="bi bi-envelope-fill"><span class="text-secondary"> 이메일 : GoldenTime@gmail.com</span></li>
            <li class="bi bi-calendar-week"><span class="text-secondary"> 근무시간 : 월 ~ 금 / AM 9:00 ~ PM 6:00</span>
            </li>
        </ul>
    </div>
</footer>
</body>
</html>