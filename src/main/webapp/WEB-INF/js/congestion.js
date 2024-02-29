// 스크롤시 네비게이션 바 투명도 증가
const menu = document.querySelector('#menu');
const menuHeight = menu.getBoundingClientRect().height;
document.addEventListener('scroll', () => {
    if (window.scrollY > menuHeight) {
        document.querySelector("nav").style.background = "rgb(255, 255, 255, 0.8)";
    } else {
        document.querySelector("nav").style.background = "white";
    }
});

//과거 날짜 검색 제한
var now_utc = Date.now() // 지금 날짜를 밀리초로, getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
var timeOff = new Date().getTimezoneOffset() * 60000; // 분단위를 밀리초로 변환
var year = new Date(now_utc - timeOff).toISOString().split("-")[0]; //'yyyy-mm-ddT18:09:38.134Z'에서 년 추출
var month = new Date(now_utc - timeOff).toISOString().split("-")[1]; //월 추출
var thisMonth = year + "-" + month; // 현재시점의 "yyyy-mm"
document.getElementById("month").setAttribute("min", thisMonth); // 월 검색에 현재 월 이후만 표시

//혼잡도에 따른 글씨 색 변화주기
var contentCongestion = document.getElementById("contentCongestion");
var textValue = contentCongestion.innerHTML;
colorChange(textValue);

//혼잡도 글씨 색깔 변경 메소드
function colorChange(textValue) {
	switch (textValue) {
	case "적정":
		// 색깔 green으로 바꾸는 class 추가
		contentCongestion.classList.add("green");
		
		//다른 색깔 class 제거
		contentCongestion.classList.remove("orange");
		contentCongestion.classList.remove("blue");
		contentCongestion.classList.remove("red");
	    break;
	case "보통":
		contentCongestion.classList.add("blue");
		
		contentCongestion.classList.remove("orange");
		contentCongestion.classList.remove("green");
		contentCongestion.classList.remove("red");
	    break;
	case "혼잡":
		contentCongestion.classList.add("orange");
		
		contentCongestion.classList.remove("blue");
		contentCongestion.classList.remove("green");
		contentCongestion.classList.remove("red");
		break;
	case "매우 혼잡":
		contentCongestion.classList.add("red");
		
		contentCongestion.classList.remove("blue");
		contentCongestion.classList.remove("green");
		contentCongestion.classList.remove("orange");
	    break;
	}
}

// 웅급실 차트 그리기
var myChart1;
var myChart2;

window.onload = function() {

	//응급실 차트 변수 선언
	var ctx1 = document.getElementById('erChart1').getContext('2d');
	var ctx2 = document.getElementById('erChart2').getContext('2d');

	// 초기 데이터셋
	var initialData = {
			labels: [""],
			datasets: [{
				label: '전국',
				backgroundColor: 'rgba(75, 192, 192, 0.7)',
				data: [congestionValue]
			}]
	};


	// 차트 그리기(초기)
	myChart1 = new Chart(ctx1, {
		type: 'bar',
		data: initialData,
		options: {
			indexAxis: 'y',
			scales: {
				x: {
					stacked: false
				},
				y: {
					stacked: false
				}
			}
		}
	});

	myChart2 = new Chart(ctx2, {
		type: 'bar',
		data: initialData,
		options: {
			scales: {
				x: {
					stacked: false
				},
				y: {
					stacked: false
				}
			}
		}
	});
};


//지도 생성
const container = document.getElementById('map');
const options = {
    center: new kakao.maps.LatLng(36, 127.5),
    level: 13,
    disableDoubleClickZoom: true
};
const map = new kakao.maps.Map(container, options);

const infowindow = new kakao.maps.InfoWindow({ removable: true });

map.setZoomable(false); // 확대 막기
map.setCursor('default');

//지도 반응형
var ro = new ResizeObserver(entries => {
    for (let entry of entries) {
        map.relayout();
        centerReset();
    }
});

ro.observe(mapWrap);

function centerReset() {
    var moveLatLon = new kakao.maps.LatLng(36, 127.5);
    map.setCenter(moveLatLon);
}


// 시도 좌표
import geojson from './sido.json' assert{ type: "json" };


let data = geojson.features; //geojson에서 필요한 값(features)만 추출
var coordinates = []; //좌표 저장할 배열
var name = ''; // 지역 이름
var eng_name = ''; // 지역 영어 이름

// data에서 좌표 끌고와 polygon&이벤트 만들기
data.forEach((val) => {
    coordinates = val.geometry.coordinates;
    name = val.properties.SIG_KOR_NM;
    eng_name = val.properties.CTP_ENG_NM;
    displayArea(coordinates, name,eng_name);
});

// polygon 만들고 마우스 이벤트 주는 메소드
function displayArea(coordinates, name, eng_name) {
    let path = [];
    let points = [];

    coordinates[0].forEach((coordinate) => {
        path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
        var point = new Object();
        point.x = coordinate[1];
        point.y = coordinate[0];
        points.push(point);
    });
    //polygon 생성
    let polygon = new kakao.maps.Polygon({
        map: map,
        path: path, // 그려질 다각형의 좌표 배열입니다
        strokeWeight: 2, // 선의 두께입니다
        strokeColor: '#004c80', // 선의 색깔입니다
        strokeOpacity: 0.5, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'solid', // 선의 스타일입니다
        fillColor: '#ffffff', // 채우기 색깔입니다
        fillOpacity: 0.8, // 채우기 불투명도 입니다
    });

    //폴리곤 중심좌표 구하는 메소드(centroid 알고리즘)
    function centerMap(points) {
        var i, j, len, p1, p2, f, area, x, y;
        area = x = y = 0;

        for (i = 0, len = points.length, j = len - 1; i < len; j = i++) {
            p1 = points[i];
            p2 = points[j];

            f = p1.y * p2.x - p2.y * p1.x;
            x += (p1.x + p2.x) * f;
            y += (p1.y + p2.y) * f;
            area += f * 3;
        }
        return new kakao.maps.LatLng(x / area, y / area);
    }

    //오버레이 생성 메소드
    function overlaySet(name, points) {
        var content = '<div class="area" style="font-weight:bold; font-size:10px; color:black;">' + name + '</div>';

        // 커스텀 오버레이가 표시될 위치입니다 
        var position = centerMap(points);

        // 커스텀 오버레이를 생성합니다
        var customOverlay = new kakao.maps.CustomOverlay({
            position: position,
            content: content,
            xAnchor: 0.3,
            yAnchor: 0.91
        });

        // 커스텀 오버레이를 지도에 표시합니다
        customOverlay.setMap(map);
        var overlay = document.querySelector(".area");
        kakao.maps.event
    }
    overlaySet(name, points);  // 오버레이 생성

    // 다각형에 mouseover하면 폴리곤의 채움색을 변경합니다
    kakao.maps.event.addListener(polygon, 'mouseover', function (mouseEvent) {
        polygon.setOptions({ fillColor: '#09f' });
    });

    // 다각형에 mouseout 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 원래색으로 변경합니다
    kakao.maps.event.addListener(polygon, 'mouseout', function () {
        polygon.setOptions({ fillColor: '#ffffff' });
    });

    //다각형 클릭 이벤트
    kakao.maps.event.addListener(polygon, 'click', function () {

        var regionTitle = document.querySelector("#regionTitle");
        regionTitle.innerHTML = name;
        var contentRegion = document.querySelector("#contentRegion");
        contentRegion.innerHTML = name;        
        
        //fetch API로 클릭한 지역(오버레이)의 혼잡도 끌어오기
        var url = "regionSearch/"+ year + month + eng_name;
    	fetch(url, {
    	    method: "GET",
    	    headers: {
    	        "Content-Type": "application/json",
    	    }
    	})
    	.then(response => response.json())
    	.then(data => {
    		
    		const congestionValue = data[0];
    		const congestion = data[1];
    		
    	    var contentCongestion = document.querySelector("#contentCongestion");
    	    contentCongestion.innerHTML = congestion; // 혼잡도 표시
    	    colorChange(congestion); // 혼잡도에 따른 글씨 색 변화
    	    
            //응급실 차트에 비교 데이터 추가
            if (myChart1.data.datasets.length > 1) {
                myChart1.data.datasets.pop();
            }

            var addtionalValue = [congestionValue];

            // 새로운 데이터셋 추가
            myChart1.data.datasets.push({
                label: name,
                backgroundColor: 'rgba(255, 99, 132, 0.7)',
                data: addtionalValue
            });

            myChart1.update();
            myChart2.update();
    	    
    	    console.log("혼잡도 수치 : " + congestionValue);
    	    console.log("결과 : " + congestion);
    	    
    	})
    	.catch(error => {
    		console.log("에러다");
    	    console.error('Error:', error);
    	});
    });
};

// 응급실 지표 팝업창 띄우기
var popOpen = document.querySelector("#popOpen");
popOpen.addEventListener("click", function () {
    var pop = document.querySelector("#popWrap");
    pop.classList.remove('hide');
})

var popClose = document.querySelector("#popClose");
popClose.addEventListener("click", function () {
    var pop = document.querySelector("#popWrap");
    pop.classList.add('hide');
})

//병상포화지수 토글
$(document).ready(function () {
    $("#formula").click(function () {
        $("#formulaPop").toggle();
    });
});