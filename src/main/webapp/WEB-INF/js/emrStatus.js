
const menu = document.querySelector('#menu');
const menuHeight = menu.getBoundingClientRect().height;
document.addEventListener('scroll', () => {
  if (window.scrollY > menuHeight) {
    document.querySelector("nav").style.background="rgb(255, 255, 255, 0.8)";
  } else {
    document.querySelector("nav").style.background="white";
  }
});

// effect 클래스에 화면 스크롤에 따른 fadeInUp 클래스 적용
$(document).ready(function() {
  $(window).scroll(function() {
    $('.effect').each(function(i) {
      var bottom_of_element = $(this).offset().top + $(this).outerHeight() / 3;
      var bottom_of_window = $(window).scrollTop() + $(window).height();
    
      if (bottom_of_window > bottom_of_element && !$(this).hasClass('fadeInUp')) {
        $(this).addClass('fadeInUp');
      }
    });
  });
});

var rightSection = document.getElementById("rightSection");
var seoulMap = document.getElementById("seoulMap");
// 서블릿 넘겨줄 파라미터
var gu;
// 행정구역별 응급실 배열
var data = [];
// 응급실 실시간(실제 실시간 데이터는 아님) 데이터 배열
var hospitalData = [];
//
window.onload = function() {
	getHospitalData();
}
// 서블릿 연결
function getHospitalData() {
    // jQuery를 사용하여 Ajax POST 요청을 보냄
    $.ajax({
        url: 'realTimeEmr', // 요청할 서블릿의 URL
        type: 'post', // 요청 방식을 POST로 지정
        dataType: 'json', // 응답 데이터 타입은 JSON으로 지정
        success: function (responseData) {
        	console.log("getHospitalData ajax 성공");
            // 비동기 콜백 함수 내에서 데이터를 처리
            for (var key in responseData) {
                if (responseData.hasOwnProperty(key)) {
                    hospitalData.push(responseData[key]);
                }
            }
            console.log('Received Data (hospitalData):', hospitalData);
            
            // 데이터를 받아온 후에 inputData 함수 호출

        },
        error: function (xhr, status, error) {
            console.error('Error:', status, error);
        }
    });
}
// 페이지 이동
function fnMove(seq) {
    var offset = $("#page" + seq).offset();
    $('html, body').animate({ scrollTop: offset.top - 110 }, 100);
}

// currentLocationBorder 함수 정의
function currentLocationBorder(){
    // rightSection의 자식 노드 전체를 삭제
    rightSection.replaceChildren();
  
    // 좌측 이전 버튼 생성
    var leftButton = document.createElement("button");
    leftButton.id = 'previousButton';
    leftButton.className = "bi bi-chevron-double-left text-secondary";
    leftButton.addEventListener('click', previousGu);
  
    // 우측 다음 버튼 생성
    var rightButton = document.createElement("button");
    rightButton.id = 'nextButton';
    rightButton.className = "bi bi-chevron-double-right text-secondary";
    rightButton.addEventListener('click', nextGu);
  
    // "현재위치" 텍스트를 담을 div 요소 생성
    var text1 = document.createElement("div");
    text1.textContent = ("현재위치");
    text1.style.fontSize = "1.2em ";
  
    // 현재 위치 정보를 담을 div 요소 생성
    let gubox = document.createElement("div");
    gubox.id = 'present';
    gubox.style = "height: 25px; animation: fadeInUp 1s;"
  
    // 전체 현재 위치 UI를 감싸는 div 요소 생성
    var currentLocation = document.createElement("div");
    currentLocation.style="background: rgb(255, 255, 255);"
    currentLocation.id = 'currentLocation';
    
    // 현재 위치 UI 조립
    currentLocation.appendChild(text1);
    currentLocation.appendChild(gubox);
  
    // 전체 현재 위치 UI를 감싸는 div 요소를 병원 UI로 추가
    var hospital = document.createElement("div");
    hospital.id = 'hospital'
    hospital.appendChild(leftButton);
    hospital.appendChild(currentLocation);
    hospital.appendChild(rightButton);
  
    // 병원 UI를 rightSection에 추가
    rightSection.appendChild(hospital);

  }
  
// inputData 함수 정의
function inputData(data, startIndex, lastIndex) {
    // 데이터가 없을 경우 빠르게 함수를 종료
    if (!data || data.length === 0 || !data[0]) {
        return;
    }

    // 주어진 데이터의 길이만큼 반복문 실행
    for (var i = startIndex; i < lastIndex; i++) {
        // 홀수 번째 또는 첫 번째 항목일 때 새로운 병원을 담을 div 요소 생성
        if (i % 2 === 0 || i === 0) {
            var hospitalBody = document.createElement("div");
            hospitalBody.style = "display: flex; justify-content: space-around; margin-top: 50px;  position: relative; animation: fadeInUp 1s;";
        }

        // 각 병원의 정보를 담을 div 요소 생성
        var row = document.createElement("div");
        row.style = "width: 200px; text-align: center; box-shadow: 5px 5px 3px #666; border-radius: 20px; border: 2px solid black; margin-bottom: 20px;";
        row.className = "align-botoom";

        // 병원 이름을 나타낼 p 요소 생성
        var nameCell = document.createElement("p");
        nameCell.textContent = data[i].dutyName;
        console.log("inputdata() 함수 실행, dutyName : " + data[i].dutyName);
        nameCell.style = "border-bottom: 1px solid black; height: 60px; margin: 0; font-weight: bold; font-size: 1.2em; padding: auto; display: flex; align-items: center; justify-content: center;";
        nameCell.className = "text-primary emrName";

        // 전화번호를 나타낼 p 요소 생성
        var telnumCell = document.createElement("p");
        telnumCell.innerHTML = '<span class="bi bi-telephone-fill">' + " " + data[i].dutyTel3 + "</span>";
        console.log("dutyTel3 : " + data[i].dutyTel3);
        telnumCell.style = "height: 25px; line-height: 25px; margin: 0;";
        telnumCell.className = "text-secondary";

        // 주소 및 자세히 버튼을 나타낼 p 요소 생성
        var addressCell = document.createElement("p");
        addressCell.innerHTML = '<span class="bi bi-map-fill">' + " " + data[i].dutyAddr + "</span>" + "<br>" + '<button id="show" class="btn btn-primary">자세히</button>';
        console.log("dutyAddr : " + data[i].dutyAddr);
        addressCell.style = "border-top: 1px solid black;";
        addressCell.className = "text-secondary emrAddress";

        // 각 정보를 병원 정보 div에 추가
        row.appendChild(nameCell);
        row.appendChild(telnumCell);
        row.appendChild(addressCell);
        hospitalBody.appendChild(row);

        // 홀수 번째 항목이거나 첫 번째 항목일 때, 생성된 병원 정보 div를 웹 페이지에 추가
        if (i - 1 === 0 || (i - 1) % 2 === 0 || data[0].length === 1) {
            rightSection.appendChild(hospitalBody);
        } else if (row != null) { // 남은 데이터가 없을시 생성된 병원 정보 div를 웹 페이지에 추가
            rightSection.appendChild(hospitalBody);
        }
        if(i + 1 >= data[0].length){ // 다음 데이터에 값이 없을시 break
        	break;
        }
    }
    // 데이터 5개 이상일시 페이지네이션 추가
    if (data[0].length > 4) {
        var additionalInfo = document.createElement("div");
        if(startIndex<4){
        	additionalInfo.innerHTML = '<div><ul class="pagination pagination-sm"><li class="page-item active" aria-current="page"><span class="page-link">1</span></li><li class="page-item"><a class="page-link" style="cursor: pointer;" onclick="clickSecond()">2</a></li></ul></div>';
        }else{
        	additionalInfo.innerHTML = '<div><ul class="pagination pagination-sm"><li class="page-item" aria-current="page"><span class="page-link" style="cursor: pointer;" onclick="clickFirst()">1</span></li><li class="page-item"><a class="page-link active">2</a></li></ul></div>';
        }
        

        additionalInfo.style.fontSize = "1em";
        additionalInfo.style.color = "blue";
        additionalInfo.style.display = "flex";
        additionalInfo.style.justifyContent = "center"; // 내용을 가로로 가운데로 정렬
        	
        // 추가 정보를 병원 정보 div 아래에 추가
        rightSection.appendChild(additionalInfo);
    }

}

function clickFirst() {
	currentLocationBorder();
	inputData(data, 0, 4);
}

function clickSecond() {
	currentLocationBorder();
	inputData(data, 4, 8);
}
	
function getData() {
    data = [];
    // jQuery를 사용하여 Ajax POST 요청을 보냄
    $.ajax({
        url: 'emrInfo', // 요청할 서블릿의 URL
        type: 'post', // 요청 방식을 POST로 지정
        dataType: 'json', // 응답 데이터 타입은 JSON으로 지정
        data: { area: gu }, // POST 요청 시 데이터 전송
        success: function (responseData) {
        	console.log("emrInfo 성공");
            // 비동기 콜백 함수 내에서 데이터를 처리
            for (var key in responseData) {
                if (responseData.hasOwnProperty(key)) {
                    data.push(responseData[key]);
                }
            }
            console.log('Received Data (data):', data);
            // 데이터를 받아온 후에 inputData 함수 호출
            inputData(data,0,data.length);
        },
        error: function (xhr, status, error) {
            console.error('Error:', status, error);
        }
    });
}



// 수정
// 행정구역 클릭 함수 정의
function clickDobong(){
seoulMap.src = "img/seoulMap/Dobong.PNG"
currentLocationBorder();
gu = "도봉";
moveLatLon = new kakao.maps.LatLng(37.668768, 127.047163);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickDongdaemun(){
seoulMap.src = "img/seoulMap/Dongdaemun.PNG"
currentLocationBorder();
gu = "동대문";
moveLatLon = new kakao.maps.LatLng(37.574524, 127.03965);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickDongjak(){
seoulMap.src = "img/seoulMap/Dongjak.PNG"
currentLocationBorder();
gu = "동작";
moveLatLon = new kakao.maps.LatLng(37.51245, 126.9395);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickEunpyeong(){
seoulMap.src = "img/seoulMap/Eunpyeong.PNG"
currentLocationBorder();
gu = "은평";
moveLatLon = new kakao.maps.LatLng(37.602784, 126.929164);
map.setCenter(moveLatLon);
moveLatLon = new kakao.maps.LatLng(37.668768, 127.047163);
document.getElementById("present").innerText = gu;
getData();
};
function clickGangbuk(){
seoulMap.src = "img/seoulMap/Gangbuk.PNG"
currentLocationBorder();
gu = "강북";
moveLatLon = new kakao.maps.LatLng(37.6397819, 127.0256135);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
// document.getElementById("present").innerText = gu;
// let nullText = document.createElement("div");
// nullText.innerHTML = "강북구에는 응급실이 없습니다.<br>주변 지역을 클릭하면<br> 다른 응급실을 제공해드립니다.";
// nullText.className = "text-secondary text-center";
// nullText.style="font-size: 2em; margin-top: 50px; animation: fadeInUp 1s";
// rightSection.appendChild(nullText);
};
function clickGangdong(){
seoulMap.src = "img/seoulMap/Gangdong.PNG"
currentLocationBorder();
gu = "강동";
moveLatLon = new kakao.maps.LatLng(37.530126, 127.1237708);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickGangnam(){
seoulMap.src = "img/seoulMap/Gangnam.PNG"
currentLocationBorder();
gu = "강남";
moveLatLon = new kakao.maps.LatLng(37.517305, 127.047502);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickGangseo(){
seoulMap.src = "img/seoulMap/Gangseo.PNG"
currentLocationBorder();
gu = "강서";
moveLatLon = new kakao.maps.LatLng(37.550937, 126.849642);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickGeumcheon(){
seoulMap.src = "img/seoulMap/Geumcheon.PNG"
currentLocationBorder();
gu = "금천";
moveLatLon = new kakao.maps.LatLng(37.4568644, 126.8955105);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickGuro(){
seoulMap.src = "img/seoulMap/Guro.PNG"
currentLocationBorder();
gu = "구로";
moveLatLon = new kakao.maps.LatLng(37.495472, 126.887536);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickGwanak(){
seoulMap.src = "img/seoulMap/Gwanak.PNG"
currentLocationBorder();
gu = "관악";
moveLatLon = new kakao.maps.LatLng(37.4781549, 126.9514847);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickGwangjin(){
seoulMap.src = "img/seoulMap/Gwangjin.PNG"
currentLocationBorder();
gu = "광진";
moveLatLon = new kakao.maps.LatLng(37.538617, 127.082375);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickJongno(){
seoulMap.src = "img/seoulMap/Jongno.PNG"
currentLocationBorder();
gu = "종로";
moveLatLon = new kakao.maps.LatLng(37.5735207, 126.9788345);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickJung(){
seoulMap.src = "img/seoulMap/Jung.PNG"
currentLocationBorder();
gu = "중구";
moveLatLon = new kakao.maps.LatLng(37.563843, 126.997602);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickJungnang(){
seoulMap.src = "img/seoulMap/Jungnang.PNG"
currentLocationBorder();
gu = "중랑";
moveLatLon = new kakao.maps.LatLng(37.6063242, 127.0925842);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickMapo(){
seoulMap.src = "img/seoulMap/Mapo.PNG"
currentLocationBorder();
gu = "마포";
moveLatLon = new kakao.maps.LatLng(37.5663245, 126.901491);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickNowon(){
seoulMap.src = "img/seoulMap/Nowon.PNG"
currentLocationBorder();
gu = "노원";
moveLatLon = new kakao.maps.LatLng(37.654358, 127.056473);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickSeocho(){
seoulMap.src = "img/seoulMap/Seocho.PNG"
currentLocationBorder();
gu = "서초";
moveLatLon = new kakao.maps.LatLng(37.483569, 127.032598);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickSeodaemun(){
seoulMap.src = "img/seoulMap/Seodaemun.PNG"
currentLocationBorder();
gu = "서대문";
moveLatLon = new kakao.maps.LatLng(37.579225, 126.9368);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickSeongbuk(){
seoulMap.src = "img/seoulMap/Seongbuk.PNG"
currentLocationBorder();
gu = "성북";
moveLatLon = new kakao.maps.LatLng(37.5894, 127.016749);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickSeongdong(){
seoulMap.src = "img/seoulMap/Seongdong.PNG"
currentLocationBorder();
gu = "성동";
moveLatLon = new kakao.maps.LatLng(37.563456, 127.036821);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickSongpa(){
seoulMap.src = "img/seoulMap/Songpa.PNG"
currentLocationBorder();
gu = "송파";
moveLatLon = new kakao.maps.LatLng(37.5145636, 127.1059186);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickYangcheon(){
seoulMap.src = "img/seoulMap/Yangcheon.PNG"
currentLocationBorder();
gu = "양천";
moveLatLon = new kakao.maps.LatLng(37.517016, 126.866642);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickYeongdeungpo(){
seoulMap.src = "img/seoulMap/Yeongdeungpo.PNG"
currentLocationBorder();
gu = "영등포";
moveLatLon = new kakao.maps.LatLng(37.526436, 126.896004);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
function clickYongsan(){
seoulMap.src = "img/seoulMap/Yongsan.PNG"
currentLocationBorder();
gu = "용산";
moveLatLon = new kakao.maps.LatLng(37.532527, 126.99049);
map.setCenter(moveLatLon);
document.getElementById("present").innerText = gu;
getData();
};
// 눌러서 다음구보기
function nextGu(){
  let thisGu = document.getElementById("present").innerText;
  let arr = [clickGangnam, clickGangdong, clickGangbuk, clickGangseo, clickGwanak, clickGwangjin, clickGuro, clickGeumcheon, clickNowon, clickDobong, clickDongdaemun, clickDongjak, clickMapo, clickSeodaemun, clickSeocho, clickSeongdong, clickSeongbuk, clickSongpa, clickYangcheon, clickYeongdeungpo, clickYongsan, clickEunpyeong, clickJongno, clickJung, clickJungnang];
  let guu = ["강남", "강동", "강북", "강서", "관악", "광진", "구로", "금천", "노원", "도봉", "동대문", "동작", "마포", "서대문", "서초", "성동", "성북", "송파", "양천", "영등포", "용산", "은평", "종로", "중구", "중랑"];
  for(let i=0; i<arr.length; i++){
    if(thisGu===null||thisGu===""||thisGu==="중랑"){
      arr[0](); // 첫 번째 함수 호출
      break;
    } else if(thisGu===guu[i]){
      arr[i+1](); // 현재 위치의 다음 함수 호출
      break;
    } 
  }
}
// 눌러서 이전구보기
function previousGu(){
  let thisGu = document.getElementById("present").innerText;
  let arr = [clickGangnam, clickGangdong, clickGangbuk, clickGangseo, clickGwanak, clickGwangjin, clickGuro, clickGeumcheon, clickNowon, clickDobong, clickDongdaemun, clickDongjak, clickMapo, clickSeodaemun, clickSeocho, clickSeongdong, clickSeongbuk, clickSongpa, clickYangcheon, clickYeongdeungpo, clickYongsan, clickEunpyeong, clickJongno, clickJung, clickJungnang];
  let guu = ["강남", "강동", "강북", "강서", "관악", "광진", "구로", "금천", "노원", "도봉", "동대문", "동작", "마포", "서대문", "서초", "성동", "성북", "송파", "양천", "영등포", "용산", "은평", "종로", "중구", "중랑"];
  for(let i=arr.length-1; i<arr.length; i--){
    if(thisGu===null||thisGu===""||thisGu==="강남"){
      arr[24](); // 마지막 함수 호출
      break;
    } else if(thisGu===guu[i]){
      arr[i-1](); // 현재 위치의 이전 함수 호출
      break;
    } 
  }
}
// 서울 이미지맵 반응형 웹
$(document).ready(function() {
  $('img[usemap]').rwdImageMaps();
});
// 수정
// 각 응급실 이름을 통해 검색 후 좌표 이동
function show(content) {
for(let i=0; i<positions.length; i++){
 if(positions[i].title === content){
   // 이동할 위도 경도 위치를 생성합니다
   var moveLatLon = positions[i].latlng;
   // 페이지를 지도로 이동시킵니다
   fnMove(1)
   // 지도 중심을 이동 시킵니다
   map.setCenter(moveLatLon);
 }
}
for(let i=0; i<markerArray.length; i++){
	  if(markerArray[i].Gb === content){
		  kakao.maps.event.trigger(markerArray[i], 'click');
	  }
}
}

// 버튼 클릭 이벤트 등록
document.addEventListener('click', function(event) {
    var target = event.target;

    if (target.classList.contains('btn-primary')) {
        var parent = target.closest(".align-botoom");
        var emrName = parent.querySelector(".emrName").textContent.trim();
        var emrAddress = parent.querySelector(".emrAddress").textContent.slice(0, -3);
        show(emrName);
    }
});

// -------------------------------------------------------------------------------------------------------------
var mapContainer = document.getElementById('map'), // 지도를 표시할 div
                mapOption = { 
                    center: new kakao.maps.LatLng(37.5642135, 127.0016985), // 지도의
																			// 중심좌표
                    level: 6 // 지도의 확대 레벨

                };
            
            var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
            // 지도 크기 조절
            var minZoomLevel = 4;
            var maxZoomLevel = 8;

            // setMinLevel() 메서드를 사용하여 최소 지도 확대 수준 설정
            map.setMinLevel(minZoomLevel);
            map.setMaxLevel(maxZoomLevel);
            


          // 수정
            // 마커를 표시할 위치와 title 객체 배열입니다
            var positions = [
                {
                    // 1
                    title: '가톨릭대학교 여의도성모병원', 
                    latlng: new kakao.maps.LatLng(37.51827234, 126.9367313)
                },
                {
                    // 2
                    title: '가톨릭대학교 은평성모병원', 
                    latlng: new kakao.maps.LatLng(37.63360841, 126.9161505)
                },
                {
                    // 3
                    title: '강동경희대학교의대병원',
                    latlng: new kakao.maps.LatLng(37.55204593, 127.1570848)
                },

                {
                    // 4
                    title: '강북삼성병원',
                    latlng: new kakao.maps.LatLng(37.56849763, 126.9679381)
                },
                {
                    // 5
                    title: '건국대학교병원',
                    latlng: new kakao.maps.LatLng(37.54084479, 127.0721229)
                },
                {
                    // 6
                    title: '국립경찰병원',
                    latlng: new kakao.maps.LatLng(37.49641321, 127.1234879)
                },
                {
                    // 7
                    title: '경희대학교병원',
                    latlng: new kakao.maps.LatLng(37.59387655, 127.0518322)
                },
                {
                    // 8
                    title: '고려대학교의과대학부속구로병원',
                    latlng: new kakao.maps.LatLng(37.49211115, 126.8847449)
                },
                {
                    // 9
                    title: '구로성심병원',
                    latlng: new kakao.maps.LatLng(37.49964579, 126.8663604)
                },
                {
                    // 10
                    title: '국립중앙의료원',
                    latlng: new kakao.maps.LatLng(37.56733956, 127.0057954)
                },
                {
                    // 11
                    title: '노원을지대학교병원',
                    latlng: new kakao.maps.LatLng(37.63644293, 127.0700028)
                },
                {
                    // 12
                    title: '녹색병원',
                    latlng: new kakao.maps.LatLng(37.58362084, 127.0860555)
                },
                {
                    // 13
                    title: '대림성모병원',
                    latlng: new kakao.maps.LatLng(37.49068925, 126.9071695)
                },
                {
                    // 14
                    title: '명지성모병원',
                    latlng: new kakao.maps.LatLng(37.49385071, 126.8992545)
                },
                {
                    // 15
                    title: '부민병원',
                    latlng: new kakao.maps.LatLng(37.55694089, 126.8509495)
                },
                {
                    // 16
                    title: '삼성서울병원',
                    latlng: new kakao.maps.LatLng(37.48851613, 127.0866825)
                },
                {
                    // 17
                    title: '삼육서울병원',
                    latlng: new kakao.maps.LatLng(37.587992, 127.0653288)
                },
                {
                    // 18
                    title: '서울대학교병원',
                    latlng: new kakao.maps.LatLng(37.57966609, 126.9989631)
                },
                {
                    // 19
                    title: '서울성심병원',
                    latlng: new kakao.maps.LatLng(37.58419129, 127.0498381)
                },
                {
                    // 20
                    title: '서울적십자병원',
                    latlng: new kakao.maps.LatLng(37.56715536, 126.9669986)
                },
                {
                    // 21
                    title: '서울특별시동부병원',
                    latlng: new kakao.maps.LatLng(37.57539886, 127.0314026)
                },
                {
                    // 22
                    title: '서울특별시보라매병원',
                    latlng: new kakao.maps.LatLng(37.4937184, 126.9240488)
                },
                {
                    // 23
                    title: '서울특별시서남병원',
                    latlng: new kakao.maps.LatLng(37.51201936, 126.8331299)
                },
                {
                    // 24
                    title: '서울특별시 서울의료원',
                    latlng: new kakao.maps.LatLng(37.61286932, 127.0980911)
                },
                {
                    // 25
                    title: '성심의료재단강동성심병원',
                    latlng: new kakao.maps.LatLng(37.53598408, 127.1352635)
                },
                {
                    // 26
                    title: '성애의료재단 성애병원',
                    latlng: new kakao.maps.LatLng(37.51205045, 126.9223673)
                },
                {
                    // 27
                    title: '세란병원',
                    latlng: new kakao.maps.LatLng(37.57534017, 126.9577072)
                },
                {
                    // 28
                    title: '순천향대학교 부속 서울병원',
                    latlng: new kakao.maps.LatLng(37.53384172, 127.004418)
                },
                {
                    // 29
                    title: '연세대학교의과대학강남세브란스병원',
                    latlng: new kakao.maps.LatLng(37.49280698, 127.0463125)
                },
                {
                    // 30
                    title: '연세대학교의과대학세브란스병원',
                    latlng: new kakao.maps.LatLng(37.56211711, 126.9408277)
                },
                {
                    // 31
                    title: '의료법인동신의료재단 동신병원',
                    latlng: new kakao.maps.LatLng(37.58110428, 126.9365831)
                },
                {
                    // 32
                    title: '의료법인 서울효천의료재단 에이치플러스양지병원',
                    latlng: new kakao.maps.LatLng(37.48427507, 126.9325392)
                },
                {
                    // 33
                    title: '의료법인 청구성심병원',
                    latlng: new kakao.maps.LatLng(37.62079154, 126.919554)
                },
                {
                    // 34
                    title: '의료법인 풍산의료재단 동부제일병원',
                    latlng: new kakao.maps.LatLng(37.60067565, 127.1090292)
                },
                {
                    // 35
                    title: '의료법인한전의료재단한일병원',
                    latlng: new kakao.maps.LatLng(37.6461157, 127.0290242)
                },
                {
                    // 36
                    title: '이화여자대학교의과대학부속목동병원',
                    latlng: new kakao.maps.LatLng(37.53654283, 126.886216)
                },
                {
                    // 37
                    title: '이화여자대학교의과대학부속서울병원',
                    latlng: new kakao.maps.LatLng(37.55726115, 126.8362659)
                },
                {
                    // 38
                    title: '인제대학교상계백병원',
                    latlng: new kakao.maps.LatLng(37.64858127, 127.0631162)
                },
                {
                    // 39
                    title: '재단법인아산사회복지재단 서울아산병원',
                    latlng: new kakao.maps.LatLng(37.52656397, 127.1082383)
                },
                {
                    // 40
                    title: '중앙대학교병원',
                    latlng: new kakao.maps.LatLng(37.50707428, 126.9607938)
                },
                {
                    // 41
                    title: '고려대학교의과대학부속병원(안암병원)',
                    latlng: new kakao.maps.LatLng(37.58715608, 127.0264709)
                },
                {
                    // 42
                    title: '가톨릭대학교서울성모병원',
                    latlng: new kakao.maps.LatLng(37.5018008, 127.0047273)
                },
                {
                    // 43
                    title: '한국보훈복지의료공단 중앙보훈병원',
                    latlng: new kakao.maps.LatLng(37.5282209, 127.1467189)
                },
                {
                    // 44
                    title: '한국원자력의학원원자력병원',
                    latlng: new kakao.maps.LatLng(37.62881598, 127.0826932)
                },
                {
                    // 45
                    title: '한림대학교강남성심병원',
                    latlng: new kakao.maps.LatLng(37.49324929, 126.9086725)
                },
                {
                    // 46
                    title: '한양대학교병원',
                    latlng: new kakao.maps.LatLng(37.55994453, 127.0448828)
                },
                {
                    // 47
                    title: '혜민병원',
                    latlng: new kakao.maps.LatLng(37.53531566, 127.0836013)
                },
                {
                    // 48
                    title: '홍익병원',
                    latlng: new kakao.maps.LatLng(37.52844141, 126.863664)
                },
                {
                    // 49
                    title: '희명병원',
                    latlng: new kakao.maps.LatLng(37.45567063, 126.9005625)
                },
                {
                	// 50
                    title: '강남고려병원',
                    address: '서울특별시 관악구 관악로 242 (봉천동)',
                    latlng: new kakao.maps.LatLng(37.48561884, 126.9567817)
                },
                {
                	// 51
                    title: '강남힐병원',
                    address: '서울특별시 관악구 남부순환로 1449, 강남힐병원 (신림동)',
                    latlng: new kakao.maps.LatLng(37.48165134, 126.9116481)
                },
                {
                	// 52
                    title: '강북으뜸병원',
                    address: '서울특별시 강북구 도봉로 187, 지하1층, 2층~5층 (미아동)',
                    latlng: new kakao.maps.LatLng(37.62542056, 127.0261845)
                },
                {
                	// 53
                    title: '미즈메디병원',
                    address: '서울특별시 강서구 강서로 295 (내발산동)',
                    latlng: new kakao.maps.LatLng(37.55225379, 126.8360254)
                },
                {
                	// 54
                    title: '사랑의병원',
                    address: '서울특별시 관악구 남부순환로 1860, -1,1,3,4,5층 (봉천동)',
                    latlng: new kakao.maps.LatLng(37.47962478, 126.9562858)
                },
                {
                	// 55
                    title: '서울현대병원',
                    address: '서울특별시 강북구 도봉로 374 (번동, 서울현대병원)',
                    latlng: new kakao.maps.LatLng(37.64025592, 127.0285044)
                },
                {
                	// 56
                    title: '신촌연세병원',
                    address: '서울특별시 마포구 서강로 110, 지2층~6층 (신수동)',
                    latlng: new kakao.maps.LatLng(37.55258927, 126.9337347)
                },
                {
                	// 57
                    title: '씨엠병원',
                    address: '서울특별시 영등포구 영등포로36길 13 (영등포동4가, 충무병원)',
                    latlng: new kakao.maps.LatLng(37.51884833, 126.9036798)
                },
                {
                	// 58
                    title: '우리들병원',
                    address: '서울특별시 강서구 하늘길 70 (과해동)',
                    latlng: new kakao.maps.LatLng(37.56199597, 126.7963653)
                },
                {
                	
                	// 59
                    title: '은평연세병원',
                    address: '서울특별시 은평구 연서로 177 (갈현동)',
                    latlng: new kakao.maps.LatLng(37.61469222, 126.9174722)
                },
                {
                	// 60
                    title: '의료법인 인봉의료재단 영등포병원',
                    address: '서울특별시 영등포구 당산로31길 10 (당산동3가)',
                    latlng: new kakao.maps.LatLng(37.52685674, 126.8956126)
                },
                {
                	// 61
                    title: '의료법인 성화의료재단 대한병원',
                    address: '서울특별시 강북구 도봉로 301 (수유동, 대한병원)',
                    latlng: new kakao.maps.LatLng(37.63547043, 127.0226725)
                },
                {
                	// 62
                    title: '인제대학교서울백병원',
                    address: '서울특별시 중구 마른내로 9 (저동2가)',
                    latlng: new kakao.maps.LatLng(37.56507317, 126.9888453)
                },
                {
                	// 63
                    title: '재단법인베스티안재단베스티안서울병원',
                    address: '서울특별시 성동구 왕십리로 382 (하왕십리동)',
                    latlng: new kakao.maps.LatLng(37.56511028, 127.0280198)
                },
                {
                	// 64
                    title: '차의과학대학교 강남차병원',
                    address: '서울특별시 강남구 논현로 566, 강남차병원(역삼동650-9, 606-4, 605외2필지(지하7층~지하1층, 지상2층~7층)) (역삼동)',
                    latlng: new kakao.maps.LatLng(37.50680003, 127.0346687)
                },
                {
                	// 65
                    title: '한강수병원',
                    address: '서울특별시 영등포구 영등포로 83, 한강수병원 (양평동1가)',
                    latlng: new kakao.maps.LatLng(37.52240478, 126.8909137)
                },
                {
                	// 66
                    title: '한림대학교 한강성심병원',
                    address: '서울특별시 영등포구 버드나루로7길 12 (영등포동7가, 한강성심병원)',
                    latlng: new kakao.maps.LatLng(37.52346675, 126.91033)
                }
            ];
            
            // 마커 이미지 설정
            var imageSrc = "img/emergencyMarker.png";
            var imageSize = new kakao.maps.Size(24, 35);
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
 
            // 지도에 마커와 인포윈도우를 표시하는 함수입니다
            function displayMarker(locPosition, message) {
            
                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({  
                    map: map, 
                    position: locPosition
                }); 
                
                var iwContent = message, // 인포윈도우에 표시할 내용
                    iwRemoveable = true;
            
                // 인포윈도우를 생성합니다
                var infowindow = new kakao.maps.InfoWindow({
                    content : iwContent,
                    removable : iwRemoveable
                });
                
                // 인포윈도우를 마커위에 표시합니다
                infowindow.open(map, marker);
                
                // 지도 중심좌표를 접속위치로 변경합니다
                map.setCenter(locPosition);      
            }   
            
        
            function findMyLocation() {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        var lat = position.coords.latitude,
                            lon = position.coords.longitude;
    
                        locPosition = new kakao.maps.LatLng(lat, lon),
                            message = '<div style="padding:5px;">현재 위치</div>';
                      
     
                        displayMarker(locPosition, message);
                    });
                } else {
                    locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
                        message = 'geolocation을 사용할수 없어요..';
    
                    displayMarker(locPosition, message);
                }
            }

            findMyLocation();

            
            // ----------------------------------------------------------------
            // 수정
          // Haversine 공식(두 지점 간의 직선 거리 계산)
            function distance(lat1, lon1, lat2, lon2) { // 두 지점의 위도와 경도
                const R = 6371; // 지구 반지름 (단위: km)
                const dLat = deg2rad(lat2 - lat1); // 위도 및 경도의 차이를 라디안으로 변환한 값
                const dLon = deg2rad(lon2 - lon1);
                const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                    Math.sin(dLon / 2) * Math.sin(dLon / 2);
                const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                const distance = R * c; // 두 지점 간의 거리 (단위: km)
                return distance;
            }
            // 각도를 라디안으로 변환
            function deg2rad(deg) {
                return deg * (Math.PI / 180);
            }

            
            //========================클릭 위치 표시 로직----------------
            // 지도를 클릭한 위치에 표출할 마커입니다
               var marker = new kakao.maps.Marker({ 
                   // 지도 중심좌표에 마커를 생성합니다 
                   position: map.getCenter() 
               }); 
               // 지도에 마커를 표시합니다
               marker.setMap(map);

               // 지도에 클릭 이벤트를 등록합니다
               // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
               kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
                   
                   // 클릭한 위도, 경도 정보를 가져옵니다 
                   var latlng = mouseEvent.latLng; 
                   
                   // 마커 위치를 클릭한 위치로 옮깁니다
                   marker.setPosition(latlng);
                   
                   var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
                   message += '경도는 ' + latlng.getLng() + ' 입니다';
                   
                   var resultDiv = document.getElementById('clickLatlng'); 
                   resultDiv.innerHTML = message;
                   
                   
               });
               //---------------------------------------------------------------------

            function findMyLocation2() {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        var lat = position.coords.latitude,
                            lon = position.coords.longitude;

                        var locPosition = new kakao.maps.LatLng(lat, lon),
                            message = '<div style="padding:5px; border-radious:20px;">현재 위치</div>';

                        displayMarker(locPosition, message);
                      

                        var positions = generatePositions();
                        displayEmergencyRooms(positions, lat, lon);
                      
                    });
                } else {
                    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
                        message = 'Geolocation cannot be used..';

                    displayMarker(locPosition, message);
                }
            }
            
          

            function generatePositions() {
                var emergencyRooms = [
                    {
                        // 1
                        title: '가톨릭대학교여의도성모병원',
                        latlng: new kakao.maps.LatLng(37.51827234, 126.9367313)
                    },
                    {
                        // 2
                        title: '가톨릭대학교은평성모병원',
                        latlng: new kakao.maps.LatLng(37.63360841, 126.9161505)
                    },
                    {
                        // 3
                        title: '강동경희대학교의대병원',
                        latlng: new kakao.maps.LatLng(37.55204593, 127.1570848)
                    },
                
                    {
                        // 4
                        title: '강북삼성병원',
                        latlng: new kakao.maps.LatLng(37.56849763, 126.9679381)
                    },
                    {
                        // 5
                        title: '건국대학교병원',
                        latlng: new kakao.maps.LatLng(37.54084479, 127.0721229)
                    },
                    {
                        // 6
                        title: '경찰병원',
                        latlng: new kakao.maps.LatLng(37.49641321, 127.1234879)
                    },
                    {
                        // 7
                        title: '경희대학교병원',
                        latlng: new kakao.maps.LatLng(37.59387655, 127.0518322)
                    },
                    {
                        // 8
                        title: '고려대학교의과대학부속구로병원',
                        latlng: new kakao.maps.LatLng(37.49211115, 126.8847449)
                    },
                    {
                        // 9
                        title: '구로성심병원',
                        latlng: new kakao.maps.LatLng(37.49964579, 126.8663604)
                    },
                    {
                        // 10
                        title: '국립중앙의료원',
                        latlng: new kakao.maps.LatLng(37.56733956, 127.0057954)
                    },
                    {
                        // 11
                        title: '노원을지대학교병원',
                        latlng: new kakao.maps.LatLng(37.63644293, 127.0700028)
                    },
                    {
                        // 12
                        title: '녹색병원',
                        latlng: new kakao.maps.LatLng(37.58362084, 127.0860555)
                    },
                    {
                        // 13
                        title: '대림성모병원',
                        latlng: new kakao.maps.LatLng(37.49068925, 126.9071695)
                    },
                    {
                        // 14
                        title: '명지성모병원',
                        latlng: new kakao.maps.LatLng(37.49385071, 126.8992545)
                    },
                    {
                        // 15
                        title: '부민병원',
                        latlng: new kakao.maps.LatLng(37.55694089, 126.8509495)
                    },
                    {
                        // 16
                        title: '삼성서울병원',
                        latlng: new kakao.maps.LatLng(37.48851613, 127.0866825)
                    },
                    {
                        // 17
                        title: '삼육서울병원',
                        latlng: new kakao.maps.LatLng(37.587992, 127.0653288)
                    },
                    {
                        // 18
                        title: '서울대학교병원',
                        latlng: new kakao.maps.LatLng(37.57966609, 126.9989631)
                    },
                    {
                        // 19
                        title: '서울성심병원',
                        latlng: new kakao.maps.LatLng(37.58419129, 127.0498381)
                    },
                    {
                        // 20
                        title: '서울적십자병원',
                        latlng: new kakao.maps.LatLng(37.56715536, 126.9669986)
                    },
                    {
                        // 21
                        title: '서울특별시동부병원',
                        latlng: new kakao.maps.LatLng(37.57539886, 127.0314026)
                    },
                    {
                        // 22
                        title: '서울특별시보라매병원',
                        latlng: new kakao.maps.LatLng(37.4937184, 126.9240488)
                    },
                    {
                        // 23
                        title: '서울특별시서남병원',
                        latlng: new kakao.maps.LatLng(37.51201936, 126.8331299)
                    },
                    {
                        // 24
                        title: '서울특별시서울의료원',
                        latlng: new kakao.maps.LatLng(37.61286932, 127.0980911)
                    },
                    {
                        // 25
                        title: '성심의료재단강동성심병원',
                        latlng: new kakao.maps.LatLng(37.53598408, 127.1352635)
                    },
                    {
                        // 26
                        title: '성애의료재단성애병원',
                        latlng: new kakao.maps.LatLng(37.51205045, 126.9223673)
                    },
                    {
                        // 27
                        title: '세란병원',
                        latlng: new kakao.maps.LatLng(37.57534017, 126.9577072)
                    },
                    {
                        // 28
                        title: '순천향대학교 부속 서울병원',
                        latlng: new kakao.maps.LatLng(37.53384172, 127.004418)
                    },
                    {
                        // 29
                        title: '연세대학교의과대학강남세브란스병원',
                        latlng: new kakao.maps.LatLng(37.49280698, 127.0463125)
                    },
                    {
                        // 30
                        title: '연세대학교의과대학세브란스병원',
                        latlng: new kakao.maps.LatLng(37.56211711, 126.9408277)
                    },
                    {
                        // 31
                        title: '의료법인동신의료재단 동신병원',
                        latlng: new kakao.maps.LatLng(37.58110428, 126.9365831)
                    },
                    {
                        // 32
                        title: '의료법인서울효천의료재단 에이치플러스양지병원',
                        latlng: new kakao.maps.LatLng(37.48427507, 126.9325392)
                    },
                    {
                        // 33
                        title: '의료법인청구성심병원',
                        latlng: new kakao.maps.LatLng(37.62079154, 126.919554)
                    },
                    {
                        // 34
                        title: '의료법인풍산의료재단동부제일병원',
                        latlng: new kakao.maps.LatLng(37.60067565, 127.1090292)
                    },
                    {
                        // 35
                        title: '의료법인한전의료재단한일병원',
                        latlng: new kakao.maps.LatLng(37.6461157, 127.0290242)
                    },
                    {
                        // 36
                        title: '이화여자대학교의과대학부속목동병원',
                        latlng: new kakao.maps.LatLng(37.53654283, 126.886216)
                    },
                    {
                        // 37
                        title: '이화여자대학교의과대학부속서울병원',
                        latlng: new kakao.maps.LatLng(37.55726115, 126.8362659)
                    },
                    {
                        // 38
                        title: '인제대학교상계백병원',
                        latlng: new kakao.maps.LatLng(37.64858127, 127.0631162)
                    },
                    {
                        // 39
                        title: '재단법인아산사회복지재단 서울아산병원',
                        latlng: new kakao.maps.LatLng(37.52656397, 127.1082383)
                    },
                    {
                        // 40
                        title: '중앙대학교병원',
                        latlng: new kakao.maps.LatLng(37.50707428, 126.9607938)
                    },
                    {
                        // 41
                        title: '학교법인 고려중앙학원 고려대학교의과대학부속병원(안암병원)',
                        latlng: new kakao.maps.LatLng(37.58715608, 127.0264709)
                    },
                    {
                        // 42
                        title: '학교법인가톨릭학원가톨릭대학교서울성모병원',
                        latlng: new kakao.maps.LatLng(37.5018008, 127.0047273)
                    },
                    {
                        // 43
                        title: '한국보훈복지의료공단 중앙보훈병원',
                        latlng: new kakao.maps.LatLng(37.5282209, 127.1467189)
                    },
                    {
                        // 44
                        title: '한국원자력의학원원자력병원',
                        latlng: new kakao.maps.LatLng(37.62881598, 127.0826932)
                    },
                    {
                        // 45
                        title: '한림대학교강남성심병원',
                        latlng: new kakao.maps.LatLng(37.49324929, 126.9086725)
                    },
                    {
                        // 46
                        title: '한양대학교병원',
                        latlng: new kakao.maps.LatLng(37.55994453, 127.0448828)
                    },
                    {
                        // 47
                        title: '혜민병원',
                        latlng: new kakao.maps.LatLng(37.53531566, 127.0836013)
                    },
                    {
                        // 48
                        title: '홍익병원',
                        latlng: new kakao.maps.LatLng(37.52844141, 126.863664)
                    },
                    {
                        // 49
                        title: '희명병원',
                        latlng: new kakao.maps.LatLng(37.45567063, 126.9005625)
                    }
                ];

                return emergencyRooms.map(room => ({
                    title: room.title,
                    latlng: room.latlng
                }));
            }
            
         
           
            function displayEmergencyRooms(positions, userLat, userLon) {
            	 var distancesToER = [];
                positions.forEach(function (emergencyRoom) {
                    var distanceToER = distance(userLat, userLon, emergencyRoom.latlng.getLat(), emergencyRoom.latlng.getLng());
                    // 배열에 응급실 이름과 거리를 저장
                    distancesToER.push({
                        title: emergencyRoom.title,
                        distance: distanceToER.toFixed(2)
                    });
                });
                // 거리를 기준으로 배열을 오름차순으로 정렬
                distancesToER.sort((a, b) => parseFloat(a.distance) - parseFloat(b.distance));

                console.log('Distances to Emergency Rooms (Sorted):', distancesToER);
                sendDistancesToServlet(distancesToER);
               
              return distancesToER;
            }
           
    
            // 수정
         // ----------------------------------------------------------------------
            // 데이터 보내기
            function sendDistancesToServlet(distancesToER) {
                // jQuery를 사용하여 서블릿으로 AJAX 요청
                $.ajax({
                    type: 'POST',
                    url: 'distanceToER',
                    contentType: 'application/json',
                    data: JSON.stringify(distancesToER),
                    success: function(response, status, xhr) {
                        // console.log('데이터를 서블릿으로 성공적으로 전송:', response);
                        if (xhr.getResponseHeader('content-type') === 'application/json') {
                            // Parse JSON if the response content type is JSON
                            var jsonResponse = JSON.parse(response);
                            sortedData(jsonResponse);
                            
                            console.log( 'successfully sent to servlet:', jsonResponse);
                        } else {
                        
                        	 sortedData(response);
                            // Handle plain text response
                            console.log('data successfully sent to servlet:', response);
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error('데이터를 서블릿으로 전송 중 오류 발생:', error);
                        console.log('Status:', status);
                        console.log('Response:', xhr.responseText);
                    }
                });
                
             // 데이터 정렬 및 표시 함수
                function sortedData(response) {
                    // 결과를 저장할 객체 생성
                    var resultsObject = {};
                    var output = '';

                    response.forEach(entry => {
                        var hospitalName = entry.hospital;
                        var result = entry.result;
                        var distance = entry.distance;
                        var cong = entry.cong;

                        output += '<ul class="row">';
                        output += '<li class="ajaxresult">' + '▶' + ' ' + hospitalName + '</li>';
                        output += '<p> 거리: ' + distance + 'km, ';
                        output += calcong(cong);
                        output += '</li>';
                        output += '</ul>';

                        // 병원 이름을 키로 사용하여 결과를 객체에 저장
                        resultsObject[hospitalName] = result;
                    });

                    // 콘솔에 결과 객체 출력
                    console.log(resultsObject);

                    // HTML 콘텐츠 설정
                    $('#ajaxresult').html(output);

                    $('.ajaxresult').css({
                        'color': 'blue',
                        'padding': '20px',
                    });
                }

                // 혼잡도 표시 함수
                function calcong(cong) {
                    var output = '';

                    if (cong > 1) {
                    	  output += '혼잡도 : <span style="color:green"> 여유 </span></p>';
                    } else if (cong < 1 && cong > 0.8) {
                    	output += '혼잡도 : <span style="color:green"> 보통</span> </p>';
                    } else if (cong < 0.79 && cong > 0.5) {
                        output += '혼잡도 : <span style="color:orange"> 약간 혼잡 </span></p>';
                    } else if (cong < 0.5) {
                        output += '혼잡도 : <span style="color:red"> 심각</span> </p>';
                    }

                    return output;
                }
// -------------------------------------------------------------------
            }
           
            
            findMyLocation2();
   
    // 내 위치 버튼을 클릭하면 화면에 내 위치 표시
    var myLocationButton = document.querySelector("#myLocation");
    
    	myLocationButton.addEventListener('click', findMyLocation());

    

// ---------------------------------------------------------------

     // 마커들을 담을 배열 생성
        var markerArray = [];

        function createMarkerClickListener(marker, index) {
            kakao.maps.event.addListener(marker, 'click', function() {
                for (var i = 0; i < hospitalData.length; i++) {
                    if (marker.getTitle() === hospitalData[i].name) {
                        changeBoard(i);
                        drawChart(i);
                        break;
                    }
                }
            });
        }

        var hospitalMarker;
        // 67개의 마커 생성 및 클릭 이벤트 리스너 등록
        for (var i = 0; i < 66; i++) {
            hospitalMarker = new kakao.maps.Marker({
                map: map,
                position: positions[i].latlng,
                title: positions[i].title,
                image: markerImage,
                clickable: true
            });

            // 클릭 이벤트 리스너 등록
            createMarkerClickListener(hospitalMarker, i);

            // 생성된 마커를 배열에 추가
            markerArray.push(hospitalMarker);
        }
        console.log(markerArray);
        
    function changeBoard(index) {
        var hospitalName = document.querySelector("#hospital-name");
        var confusedLevel = document.querySelector("#confused-level");
        var confusedStep = document.querySelector("#confused-step");
        var hvgc = document.querySelector("#used-admiss");
        var hvoc = document.querySelector("#used-surgery");
        var emReceive1 = document.querySelector("#em-receive1");
        var emReceive2 = document.querySelector("#em-receive2");
        var emReceive3 = document.querySelector("#em-receive3");
        console.log("changeBoard 함수 호출 " + hospitalData);

        
     // 응급 메시지가 없을 경우: 전달 내용 X, 응급 메세지가 있을 경우: 내용 그대로 출력
    if (hospitalData[index].emReceive3 === null) {
        emReceive3.textContent = "전달 내용 X";
    } else if(hospitalData[index].emReceive3 == null){
        emReceive3.textContent = "전달 내용 X";
    }else if(hospitalData[index].emReceive3 === ""){
       emReceive3.textContent = "전달 내용 X";
    }else if(hospitalData[index].emReceive3 == ""){
       emReceive3.textContent = "전달 내용 X";
    }else {
        emReceive3.textContent = hospitalData[index].emReceive3;
    }

    // 응급실 혼잡율에 따른 confusedLevel 색 변경 및 confusedStep style 설정
    if (hospitalData[index].hvec / hospitalData[index].hperyn == 1) {
        confusedLevel.style.color = "red";
        confusedStep.textContent = "심각";
    }
      else if (hospitalData[index].hvec / hospitalData[index].hperyn > 0.8 && hospitalData[index].hvec / hospitalData[index].hperyn < 1) {
        confusedLevel.style.color = "green";
        confusedStep.textContent = "여유";
    } else if (hospitalData[index].hvec / hospitalData[index].hperyn > 0.5 && hospitalData[index].hvec / hospitalData[index].hperyn < 0.79) {
        confusedLevel.style.color = "orange";
        confusedStep.textContent = "보통";
    } else {
        confusedLevel.style.color = "red";
        confusedStep.textContent = "심각";
    }

    // 병원 이름, 입원실과 수술실의 가용 병상 수 표시
    hospitalName.textContent = hospitalData[index].name;
    hvoc.textContent = hospitalData[index].hvoc;
    hvoc.style.fontWeight = 'bold';

    // confusedStep style 설정
    confusedStep.style.fontWeight = "bold";
    confusedStep.style.color = confusedLevel.style.color;
    }

    var myChart;

    function drawChart(index) {
        var emergencyRoom = hospitalData[index].hperyn - hospitalData[index].hvec;

        if(emergencyRoom ==0) {
            emergencyRoom = hospitalData[index].hvec;
        }

        var data = {
            labels: ['대기 환자', '기존 병상수'],
            datasets: [{
                label: '(응급실)대기 환자/기존 병상수',
                data: [emergencyRoom, hospitalData[index].hperyn],
                backgroundColor: ['red', 'green'],
            }]
        };

        var ctx = document.getElementById('statusChart').getContext('2d');

        // 이전 차트 파괴
        if (myChart) {   
            myChart.destroy();
        }

        // 새로운 차트 생성
        myChart = new Chart(ctx, {
            type: 'bar',
            data: data,
            options: {}
        });
    }
    	   // 마커들을 담을 배열 생성
//        var markerArray = [];
//
//        function createMarkerClickListener(marker, index) {
//            kakao.maps.event.addListener(marker, 'click', function() {
//                for (var i = 0; i < hospitalData.length; i++) {
//                    if (marker.getTitle() === hospitalData[i].name) {
//                        changeBoard(i);
//                        drawChart(i);
//                        break;
//                    }
//                }
//            });
//        }
//
//        var hospitalMarker;
//        // 67개의 마커 생성 및 클릭 이벤트 리스너 등록
//        for (var i = 0; i < 66; i++) {
//            hospitalMarker = new kakao.maps.Marker({
//                map: map,
//                position: positions[i].latlng,
//                title: positions[i].title,
//                image: markerImage,
//                clickable: true
//            });
//
//            // 클릭 이벤트 리스너 등록
//            createMarkerClickListener(hospitalMarker, i);
//
//            // 생성된 마커를 배열에 추가
//            markerArray.push(hospitalMarker);
//        }
//        console.log(markerArray);
//        
//        
//    function changeBoard(index) {
//        var hospitalName = document.querySelector("#hospital-name");
//        var confusedLevel = document.querySelector("#confused-level");
//        var confusedStep = document.querySelector("#confused-step");
//        var hvgc = document.querySelector("#used-admiss");
//        var hvoc = document.querySelector("#used-surgery");
//        var emReceive1 = document.querySelector("#em-receive1");
//        var emReceive2 = document.querySelector("#em-receive2");
//        var emReceive3 = document.querySelector("#em-receive3");
//
//        
//     // 응급 메시지가 없을 경우: 전달 내용 X, 응급 메세지가 있을 경우: 내용 그대로 출력
//    if (hospitalData[index].emReceive3 === null) {
//        emReceive3.textContent = "전달 내용 X";
//    } else if(hospitalData[index].emReceive3 == null){
//        emReceive3.textContent = "전달 내용 X";
//    }else if(hospitalData[index].emReceive3 === ""){
//    	emReceive3.textContent = "전달 내용 X";
//    }else if(hospitalData[index].emReceive3 == ""){
//    	emReceive3.textContent = "전달 내용 X";
//    }else {
//        emReceive3.textContent = hospitalData[index].emReceive3;
//    }
//    
//
//    // 응급실 혼잡율에 따른 confusedLevel 색 변경 및 confusedStep style 설정
//    if (hospitalData[index].hvec / hospitalData[index].hperyn == 1) {
//        confusedLevel.style.color = "red";
//        confusedStep.textContent = "심각";
//    }
//      else if (hospitalData[index].hvec / hospitalData[index].hperyn > 0.8 && hospitalData[index].hvec / hospitalData[index].hperyn < 1) {
//        confusedLevel.style.color = "green";
//        confusedStep.textContent = "여유";
//    } else if (hospitalData[index].hvec / hospitalData[index].hperyn > 0.5 && hospitalData[index].hvec / hospitalData[index].hperyn < 0.79) {
//        confusedLevel.style.color = "orange";
//        confusedStep.textContent = "보통";
//    } else {
//        confusedLevel.style.color = "red";
//        confusedStep.textContent = "심각";
//    }
//    
//
//    // 병원 이름, 입원실과 수술실의 가용 병상 수 표시
//    hospitalName.textContent = hospitalData[index].name;
//    hvgc.textContent = hospitalData[index].hvgc;
//    hvgc.style.fontWeight = 'bold';
//    hvoc.textContent = hospitalData[index].hvoc;
//    hvoc.style.fontWeight = 'bold';
//
//    // confusedStep style 설정
//    confusedStep.style.fontWeight = "bold";
//    confusedStep.style.color = confusedLevel.style.color;
//    }
//
//    var myChart;
//
//    function drawChart(index) {
//        var emergencyRoom = hospitalData[index].hperyn - hospitalData[index].hvec;
//
//        if(emergencyRoom ==0) {
//            emergencyRoom = hospitalData[index].hvec;
//        }
//
//        var data = {
//            labels: ['대기 환자', '기존 병상수'],
//            datasets: [{
//                label: '(응급실)대기 환자/기존 병상수',
//                data: [emergencyRoom, hospitalData[index].hperyn],
//                backgroundColor: ['red', 'green'],
//            }]
//        };
//
//        var ctx = document.getElementById('statusChart').getContext('2d');
//
//        // 이전 차트 파괴
//        if (myChart) {	
//            myChart.destroy();
//        }
//
//        // 새로운 차트 생성
//        myChart = new Chart(ctx, {
//            type: 'bar',
//            data: data,
//            options: {}
//        });
//    }
    
 
    
  
        