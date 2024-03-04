var chartData; // 전역 변수로 선언
var myChart; // 차트 변수 추가
var myChart3; // 새로운 차트 변수 추가
var popData; // 변수 추가

window.onload = function () {
	console.log("emrData.js 파일 onload function 실행");
  getData()
    .then(() => {
      createPopulationChangeChart();
    })
    .catch((error) => {
    	console.log("window.onload 에러다");
      console.error('데이터 검색 중 오류 발생:', error);
    });
};

function getData() {
  return new Promise((resolve, reject) =>
    $.ajax({
      url: 'emrData',
      type: 'post',
      dataType: 'json',
      success: function (data) {
    	  console.log("getData() 메소드 ajax 성공");
         // 차트에 필요한 데이터 저장
         chartData = {
                 actualDataYear: data.actualData.map(item => item.year),
                 actualEmNum: data.actualData.map(item => item.numOfEm),
                 needEmNum: data.needData.map(item => item.numOfEm),
                 congestYear: data.congestData.map(item => item.year),
                 congest: data.congestData.map(item => item.congestion),
               };
         console.log(chartData);

        
        localStorage.setItem('myChartData', JSON.stringify(chartData));
        
        //차트 생성 함수
        createEmNumChart(chartData.actualDataYear, chartData.actualEmNum, chartData.needEmNum);
        createPopulationChangeChart(chartData.populYear, chartData.populChange)
        resolve();
      },
      error: function (xhr, status, error) {
    	  console.log("getData 에러다");
        console.error('데이터 검색 중 오류 발생:', status, error);
        reject(error);
      }
    })
  );
}

//첫 번째 차트 생성
function createEmNumChart(actualDataYear, actualEmNum, needEmNum) {
  const labels = actualDataYear;
  var data = {
    labels: labels,
    datasets: [{
      label: "필요 응급실",
      data: actualEmNum,
      borderColor: 'rgb(255,165,0)',
      backgroundColor: 'rgb(255,165,0,0.5)'
    }, {
      label: "현재 응급실",
      data: needEmNum,
      borderColor: 'rgb(102,144,255)',
      backgroundColor: 'rgb(102,144,255,0.5)',
    }]
  };

  var ctx = document.getElementById('myChart1_emrData');
  if (ctx && myChart) { // 수정: ctx가 null이 아닌지 확인
    myChart.destroy(); // 차트가 이미 존재하면 제거
  }
  if (ctx) { // 수정: ctx가 null이 아닌지 확인
    myChart = new Chart(ctx.getContext('2d'), { // 수정: ctx.getContext('2d')로 수정
      type: 'bar',
      data: data,
      options: {
        indexAxis: 'y',
        elements: {
          bar: {
            borderWidth: 2,
          }
        },
        responsive: true,
        plugins: {
          legend: {
            position: 'right',
          },
          title: {
            display: true,
            text: '서울시 연도별 응급실 수 그래프'
          }
        }
      }
    });
  }
}

// 버튼 클릭 시 서울 응급실 수 차트 표시
function emergencyRoom() {
  if (!chartData) {
    getData().then(emergencyRoom); 
    return;
  }
  
  if(myChart.data.datasets[1] == null){
    myChart.data.datasets.push(popData);
  }

  var actualEmNum = chartData.actualEmNum;
  var needEmNum = chartData.needEmNum;

  myChart.data.datasets[0].label = "필요 응급실";
  myChart.data.datasets[0].data = needEmNum;
  myChart.data.datasets[0].borderColor = 'rgb(255, 165, 0)';
  myChart.data.datasets[0].backgroundColor = 'rgb(255, 165, 0,0.5)';
  myChart.data.datasets[1].label = "현재 응급실";
  myChart.data.datasets[1].data = actualEmNum;
  myChart.options.plugins.title.text = "서울시 연도별 응급실 수 그래프";

  document.querySelector(".chart1Text").innerHTML = '<h4 id="text" class="chart1Text">이 그래프는 서울에 위치한 응급실의 수와 인구수 대비 필요한 응급실의 수를 표현한 그래프입니다.<br>그래프와 병상포화지수를 이용하여 응급실 상태를 예상할 수 있습니다.<p class="text-secondary" style="font-size: 0.7em;">인구수 대비 필요 응급실의 수는 국내 기준이 없어 텐진시의 기준을 사용하였습니다..</p></h4>'
  
  myChart.update();
}

// 버튼 클릭 시 혼잡도 차트 표시
function saturation() {
  if (!chartData) {
    getData().then(saturation);
    return;
  }

  var congest = chartData.congest;

  myChart.data.datasets[0].label = "병상포화지수";
  myChart.data.datasets[0].data = congest;
  myChart.data.datasets[0].borderColor = 'rgb(202, 0, 20)';
  myChart.data.datasets[0].backgroundColor = 'rgb(202, 0, 0, 0.5)';
  myChart.options.plugins.title.text = "병상포화지수 그래프";
  
  if(myChart.data.datasets[1] != null){
    popData = myChart.data.datasets.pop();
  }
  
  document.querySelector(".chart1Text").innerHTML = '<p>이 그래프는서울의 연도별 병상포화지수를 나타낸 그래프입니다</p><p class="text-secondary" style="font-size: 15px; padding-top: 50px;">병상포화지수(%)는(내원 환자수×평균 재실시간)/(기준병상수×월별일자수×24시간)×100</p>'
  
  myChart.update();
}


//인구수 변화율 차트 생성
function createPopulationChangeChart() {
	  const ctx = document.getElementById('myChart3_emrData'); // ctx 변수를 const로 변경

	  if (!ctx) {
	    console.error('myChart3 엘리먼트를 찾을 수 없습니다.');
	    return;
	  }

	  if (myChart3) {
	    myChart3.destroy(); // myChart3가 이미 존재하면 제거
	  }

	  myChart3 = new Chart(ctx.getContext('2d'), {
	    type: 'line',
	    data: {
	      labels: ['2016', '2017', '2018', '2019', '2020', '2021', '2022','2023','2024','2025','2026','2027','2028','2029','2030'],
	      datasets: [{
	        label: '서울시 인구수 변화율',
	        data: [-0.91362349,-0.7370137,-0.93130803,-0.37392392,-0.62330489,-1.64459405,-0.85268792,-0.44904889,0.50748804,0.48916291,0.06743804,0.21047403,0.06254884,0.20541167,-0.05148108],
	        fill: false,
	        borderColor: 'rgb(57, 120, 207)',
	        tension: 0.1
	      }]
	    },
	    options: {}
	  });
	}